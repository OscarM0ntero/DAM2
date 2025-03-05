/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   get_next_line_utils.c                              :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: djanssen <djanssen@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/09/26 11:12:30 by djanssen          #+#    #+#             */
/*   Updated: 2023/01/09 15:11:11 by djanssen         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "get_next_line.h"

/**
 * @brief Get length from string
 * 
 * @param s string to measure
 * @return size_t i (length)
 */
size_t	ft_strlen(char *s)
{
	size_t	i;

	i = 0;
	if (!s)
		return (0);
	while (s[i] != '\0')
		i++;
	return (i);
}

/**
 * @brief joins two strings into one
 * 
 * @param left_str - first string
 * @param buff - second string
 * @return returns the result of the two strings into one
 */
char	*ft_strjoin(char *left_str, char *buff)
{
	size_t	i;
	size_t	j;
	char	*str;

	if (!left_str)
	{
		left_str = malloc(sizeof(char));
		left_str[0] = 0;
	}
	if (!left_str || !buff)
		return (NULL);
	str = malloc(sizeof(char) * ((ft_strlen(left_str) + ft_strlen(buff)) + 1));
	if (!str)
		return (NULL);
	i = -1;
	j = 0;
	if (left_str)
		while (left_str[++i] != '\0')
			str[i] = left_str[i];
	while (buff[j] != '\0')
		str[i++] = buff[j++];
	str[ft_strlen(left_str) + ft_strlen(buff)] = '\0';
	return (free(left_str), str);
}

/**
 * @brief searchs for a char 'c' in a string 's' 
 * 
 * @param s string where the char is being searched
 * @param c character  to be found in the string
 * @return a pointer to the located character,
 * 		or NULL if the character does not appear in the string.
 */
char	*ft_strchr(char *s, int c)
{
	int	i;

	if (!s)
		return (NULL);
	if (c == '\0')
		return (&s[ft_strlen(s)]);
	i = -1;
	while (s[++i] != '\0')
		if (s[i] == (char)c)
			return (&s[i]);
	return (NULL);
}

/**
 * @brief duplicates a string
 * 
 * @param src string to be dupped
 * @return a different string with 'src' content
 */
char	*ft_strdup(const char *src)
{
	char	*str;
	int		i;
	int		size;

	size = 0;
	while (src[size])
		size++;
	str = malloc(sizeof(char) * (size + 1));
	if (!str)
		return (NULL);
	i = -1;
	while (src[++i])
		str[i] = src[i];
	str[i] = '\0';
	return (str);
}

char	*ft_strnjoin(char *s1, char *s2, int n)
{
	int		i;
	int		j;
	char	*str;
	int		k;

	k = 0;
	j = ft_strlen(s1) + ft_strlen(s2) - (ft_strlen(s2) - n);
	str = (char *)malloc(sizeof(char) * (j + 1));
	if (!str)
		return (NULL);
	i = -1;
	while (++i < (int)ft_strlen(s1))
		str[i] = s1[i];
	while (i <= j && s2[k])
	{
		str[i++] = s2[k++];
	}
	str[i] = '\0';
	return (str);
}
