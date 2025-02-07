/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_split.c                                         :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/04/18 18:58:48 by omontero          #+#    #+#             */
/*   Updated: 2022/05/20 10:57:08 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

static size_t	ft_count_words(char const *s, char c)
{
	size_t	i;
	size_t	n_words;

	n_words = 0;
	i = 0;
	if (!*s)
		return (0);
	while (s[i] == c)
		i++;
	while (s[i])
	{
		while (s[i] != c && s[i])
			i++;
		if ((s[i] == c || s[i] == '\0') && s[i - 1] != c)
			n_words++;
		while (s[i] == c && s[i])
			i++;
	}
	return (n_words);
}

static void	ft_assign_coords(char const *str, size_t *strt, size_t *end, char c)
{
	size_t	s;
	size_t	e;

	e = *end;
	s = *end;
	while (str[s] == c && str[s])
		s++;
	e = s;
	while (str[e] != c && str[e])
		e++;
	*strt = s;
	*end = e;
}

static int	ft_assign_words(char const *s, char c, char **list, size_t n_words)
{
	size_t	start;
	size_t	end;
	size_t	i;
	char	*word;

	i = 0;
	start = 0;
	end = 0;
	ft_assign_coords(s, &start, &end, c);
	while (i < n_words)
	{
		word = ft_substr(s, start, end - start);
		if (!word)
			return (1);
		list[i] = word;
		ft_assign_coords(s, &start, &end, c);
		i++;
	}
	list[n_words] = NULL;
	return (0);
}

char	**ft_split(char const *s, char c)
{
	size_t	n_words;
	char	**list;
	size_t	i;

	if (!s)
		return (NULL);
	n_words = ft_count_words(s, c);
	list = (char **)ft_calloc(sizeof(char *), (n_words + 1));
	if (!list)
		return (NULL);
	if (ft_assign_words(s, c, list, n_words))
	{
		i = 0;
		while (list[i])
		{
			free(list[i]);
			i++;
		}
		free(list);
		return (NULL);
	}
	return (list);
}

/* int	main(void)
{
	//char	str[] = "NrQg9t T7V EeHjY0PS8Cn Zx7wDMsT2gzvVi85l vWto 
	//mxqLzQIe5sra3X2h lX847k Lm8yr O84SR bnYcxT0u6ADSfQ8I EKX l7fMHCbw1r 
	//GcFB znfT6ICyJog4vwYeN IjTfwHL ZO9gDaoR";
	char	str[] = "  tripouille  42";
	size_t	i;
	char	**list;
	char	c;

	c = ' ';
	list = ft_split(str, c);
	i = 0;
	while (i < ft_count_words(str, c))
	{
		printf("Word %ld = %s$\n", i, list[i]);
		i++;
	}
} */