/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_printf_utils.c                                  :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: oscar <oscar@student.42.fr>                +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/06/02 19:05:48 by omontero          #+#    #+#             */
/*   Updated: 2022/06/09 00:24:43 by oscar            ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "ft_printf.h"

void	ft_putstr(char *str)
{
	while (*str)
	{
		write(1, str, 1);
		str++;
	}
}

int	ft_printchar(char c)
{
	write(1, &c, 1);
	return (1);
}

int	ft_printstring(char *str)
{
	int	size;

	if (str == NULL)
	{
		ft_putstr("(null)");
		return (6);
	}
	size = ft_strlen(str);
	ft_putstr(str);
	return (size);
}

int	ft_printnumber(int n)
{
	char	*s;
	int		size;

	s = ft_itoa(n);
	size = ft_strlen(s);
	ft_putstr(s);
	free (s);
	return (size);
}

int	ft_printpercent(void)
{
	write(1, "%", 1);
	return (1);
}
