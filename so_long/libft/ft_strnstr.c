/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_strnstr.c                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/04/18 18:58:48 by omontero          #+#    #+#             */
/*   Updated: 2022/05/20 11:37:18 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

static int	ft_check_little(char *p, const char *little, const size_t len)
{
	size_t	x;
	size_t	i;
	int		tmp;

	x = (size_t)len;
	i = 0;
	tmp = 0;
	while (little[i] && x && p[i])
	{
		if (p[i] != little[i])
			tmp = 1;
		i++;
		x--;
	}
	if (!little[i])
		return (tmp);
	return (1);
}

char	*ft_strnstr(const char *big, const char *little, size_t len)
{
	char	*p;

	p = (char *)big;
	if (*little == '\0')
		return (p);
	while (*p && len)
	{
		if (*p == *little)
		{
			if (!ft_check_little(p, little, len))
				return (p);
		}
		len--;
		p++;
	}
	return (NULL);
}
