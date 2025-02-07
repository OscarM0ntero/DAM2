/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_printf.h                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/06/03 13:08:08 by omontero          #+#    #+#             */
/*   Updated: 2022/10/11 11:26:11 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#ifndef FT_PRINTF_H
# define FT_PRINTF_H

# include <stdarg.h>
# include <unistd.h>
# include "../libft/libft.h"

//		ft_printf.c
int		ft_printf(char const *string, ...);

//		ft_printf_utils.c
void	ft_putstr(char *str);
int		ft_printchar(char c);
int		ft_printstring(char *str);
int		ft_printnumber(int n);
int		ft_printpercent(void);

//		ft_print_hex.c
int		ft_printhex(unsigned int n, const char type);

//		ft_print_pointer.c
int		ft_printpointer(unsigned long long p);

//		ft_print_unsigned.c
int		ft_printunsigned(unsigned int n);
#endif