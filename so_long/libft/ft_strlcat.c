/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_strlcat.c                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/04/18 18:58:48 by omontero          #+#    #+#             */
/*   Updated: 2022/05/19 10:48:31 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

size_t	ft_strlcat(char *dest, char *src, size_t nb)
{
	size_t	a;
	size_t	b;

	a = 0;
	b = 0;
	while (dest[a])
		a++;
	if (nb < a)
	{
		while (src[b])
			b++;
		return (nb + b);
	}
	while (src[b] && nb > 0 && a < nb - 1)
		dest[a++] = src[b++];
	dest[a] = '\0';
	while (src[b++])
		a++;
	return (a);
}

/* int	main(void)
{
	char	a[] = "hola ";
	char	b[] = "soy oscar";

	printf("Tam = %ld\n", ft_strlcat(a, b, 0));
	printf("%s$", a);
	return (0);
} */
