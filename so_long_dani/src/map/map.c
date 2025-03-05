/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   map.c                                              :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: djanssen <djanssen@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/04/07 11:03:12 by djanssen          #+#    #+#             */
/*   Updated: 2022/12/29 17:46:05 by djanssen         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "../../so_long.h"

/**
 * @brief This function will print the map in TERMINAL.
 * 
 * @param fmap Main struct.
 */
void	ft_print_map(t_map *fmap)
{
	size_t	i;
	size_t	j;

	i = -1;
	write(1, "\n", 1);
	while (++i < fmap->y_axis)
	{
		j = -1;
		while (++j < fmap->x_axis)
		{
			write(1, &fmap->matrix[i][j], 1);
		}
		write(1, "\n", 1);
	}
	write(1, "\n", 1);
}

/**
 * @brief This function will read through the *.ber file and will 
 * check every line looking for errors.
 * 
 * @param input Map path.
 * @return fmap - It will return the main struct.
 */
t_map	read_map(char *input)
{
	t_map	fmap;
	int		fd;
	size_t	current;
	char	*tmp;

	current = 0;
	init_vars(&fmap, input);
	if (fmap.error)
		return (fmap);
	fmap.matrix = (char **)malloc(fmap.y_axis * sizeof(char *));
	fd = open(fmap.path, O_RDONLY);
	tmp = get_next_line(fd);
	fmap.x_axis = ft_strlen(tmp);
	while (!fmap.error && current < fmap.y_axis)
	{
		ft_check_lines(tmp, current, &fmap);
		fmap.matrix[current] = tmp;
		tmp = get_next_line(fd);
		current++;
	}
	return (close(fd), fmap);
}
