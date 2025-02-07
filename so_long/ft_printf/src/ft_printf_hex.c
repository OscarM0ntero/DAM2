/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_printf_hex.c                                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: oscar <oscar@student.42.fr>                +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/06/02 19:05:48 by omontero          #+#    #+#             */
/*   Updated: 2022/06/08 20:33:05 by oscar            ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "ft_printf.h"

static void	ft_write_in_hex(unsigned int n, const char type)
{
	if (n >= 16)
	{
		ft_write_in_hex(n / 16, type);
		ft_write_in_hex(n % 16, type);
	}
	else
	{
		if (n <= 9)
			ft_putchar_fd((n + '0'), 1);
		else
		{
			if (type == 'x')
				ft_putchar_fd((n - 10 + 'a'), 1);
			else
				ft_putchar_fd((n - 10 + 'A'), 1);
		}
	}
}

static int	ft_hex_length(unsigned int n)
{
	int	len;

	len = 0;
	while (n != 0)
	{
		n /= 16;
		len++;
	}
	return (len);
}

int	ft_printhex(unsigned int n, const char type)
{
	if (n == 0)
		return (write(1, "0", 1));
	ft_write_in_hex(n, type);
	return (ft_hex_length(n));
}
