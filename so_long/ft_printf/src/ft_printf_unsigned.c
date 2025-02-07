/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_printf_unsigned.c                               :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: oscar <oscar@student.42.fr>                +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/06/02 19:05:48 by omontero          #+#    #+#             */
/*   Updated: 2022/06/09 00:23:02 by oscar            ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "ft_printf.h"

static	int	ft_int_length(uintptr_t n)
{
	int	i;

	i = 0;
	while (n != 0)
	{
		i++;
		n /= 10;
	}
	return (i);
}

char	*ft_unsigned_itoa(uintptr_t n)
{
	int		length;
	char	*s;

	length = ft_int_length(n);
	s = ft_calloc(length + 1, sizeof(char));
	if (!s)
		return (NULL);
	while (n != 0)
	{
		s[length - 1] = (n % 10) + 48;
		n /= 10;
		length--;
	}
	return (s);
}

int	ft_printunsigned(unsigned int n)
{
	int		length;
	char	*num;

	length = 0;
	if (n == 0)
		length += ft_printchar('0');
	else
	{
		num = ft_unsigned_itoa(n);
		length += ft_printstring(num);
		free (num);
	}
	return (length);
}
