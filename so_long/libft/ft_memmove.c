/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_memmove.c                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/04/18 18:58:48 by omontero          #+#    #+#             */
/*   Updated: 2022/05/19 10:47:23 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

void	*ft_memmove(void *dest, const void *src, size_t len)
{
	unsigned char		*c;
	const unsigned char	*s;

	if (!len || dest == src)
		return (dest);
	s = (const unsigned char *)src;
	c = (unsigned char *)dest;
	if (s < c)
		while (len--)
			*(c + len) = *(s + len);
	else
		while (len--)
			*c++ = *s++;
	return (dest);
}
