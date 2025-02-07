/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_printf.c                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/06/02 19:05:48 by omontero          #+#    #+#             */
/*   Updated: 2022/11/03 12:45:22 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "ft_printf.h"

static int	ft_write_var(va_list args, char const type)
{
	int		length;

	length = 0;
	if (type == 'c')
		length += ft_printchar(va_arg(args, int));
	else if (type == 's')
		length += ft_printstring(va_arg(args, char *));
	else if (type == 'd' || type == 'i')
		length += ft_printnumber(va_arg(args, int));
	else if (type == '%')
		length += ft_printpercent();
	else if (type == 'x' || type == 'X')
		length += ft_printhex(va_arg(args, unsigned int), type);
	else if (type == 'p')
		length += ft_printpointer(va_arg(args, unsigned long long));
	else if (type == 'u')
		length += ft_printunsigned(va_arg(args, unsigned int));
	return (length);
}

int	ft_printf(char const *string, ...)
{
	int		i;
	va_list	args;
	int		length;

	length = 0;
	i = 0;
	va_start(args, string);
	while (string[i])
	{
		if (string[i] == '%' && ft_strchr("cspdiuxX%", string[i + 1]))
		{
			i++;
			length += ft_write_var(args, string[i]);
		}
		else
			length += ft_printchar(string[i]);
		i++;
	}
	va_end(args);
	return (length);
}

/*int	main(void)
{
	printf("%p %p", NULL, (void *)0);
	ft_printf("%p %p", NULL, (void *)0);
} */