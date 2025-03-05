/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   checkers.c                                         :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: djanssen <djanssen@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/04/07 11:03:12 by djanssen          #+#    #+#             */
/*   Updated: 2023/01/10 12:48:41 by djanssen         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "../../so_long.h"

/**
 * @brief This function will check if the line is filled with '1's.
 * 
 * @param tmp Inputted line.
 * @return 0 if full '1's, 0 if not.
 */
int	ft_check_ones(char *tmp)
{
	while (*tmp && *tmp == '1')
		tmp++;
	if (*tmp)
		return (1);
	return (0);
}

/**
 * @brief This function will look for the player coords.
 * 
 * @param fmap Main struct.
 */
void	ft_where_is_player(t_map *fmap)
{
	int	i;
	int	j;
	int	count;

	count = 0;
	i = -1;
	while (++i < (int)fmap->y_axis && !fmap->error)
	{
		j = -1;
		while (++j < (int)fmap->x_axis && !fmap->error)
		{
			if (fmap->matrix[i][j] == 'P' && !fmap->error)
			{
				if (++count == 1)
				{
					fmap->player_x = (size_t)j;
					fmap->player_y = (size_t)i;
				}
			}
		}
	}
	if (!count && !fmap->error)
		fmap->error = 5;
}

/**
 * @brief This function will print the error depending of it's code.
 * Error 1: Empty map.
 * Error 2: Invalid map structure.
 * Error 3: Map can't be symetrical.
 * Error 4: Map has different X sizes.
 * Error 5: Invalid map elements.
 * Error 6: Not enough map elements.
 * 
 * @param error Inputted code.
 * @return 0
 */
int	ft_print_error(int error)
{
	if (error == 1)
		return (write(1, "Error no.1: Empty map\n", 22), 1);
	if (error == 2)
		return (write(1, "Error no.2: Invalid map structure\n", 34), 1);
	if (error == 3)
		return (write(1, "Error no.3: Map can't be symetrical\n", 36), 1);
	if (error == 4)
		return (write(1, "Error no.4: Map has different X sizes\n", 38));
	if (error == 5)
		return (write(1, "Error no.5: Invalid map element\n", 32), 1);
	if (error == 6)
		return (write(1, "Error 6: Not enough/too many map elements\n", 42), 1);
	return (0);
}

/**
 * @brief It will check a line with some conditions looking for errors.
 * 
 * @param tmp Inputted line.
 * @param current Current line.
 * @param fmap Main struct.
 */
void	ft_check_lines(char *tmp, size_t current, t_map *fmap)
{
	if (fmap->x_axis != ft_strlen(tmp))
		fmap->error = 4;
	if ((current == 0 || current == fmap->y_axis - 1) && ft_check_ones(tmp))
		fmap->error = 2;
	else if (tmp[0] != '1' || tmp[ft_strlen(tmp) - 1] != '1')
		fmap->error = 2;
	if (fmap->x_axis == fmap->y_axis)
		fmap->error = 3;
}

/**
 * @brief It will go through the whole matrix checking
 * if elements are correct (EPC10).
 * 
 * @param map Main struct.
 */
void	ft_check_elements(t_map *map)
{
	map->elm.y = get_height(map->path);
	while (map->elm.y--)
	{
		map->elm.x = 0;
		while (map->matrix[map->elm.y][map->elm.x])
		{
			if (map->matrix[map->elm.y][map->elm.x] == 'E')
				map->elm.e++;
			else if (map->matrix[map->elm.y][map->elm.x] == 'P')
				map->elm.p++;
			else if (map->matrix[map->elm.y][map->elm.x] == 'C')
				map->elm.c++;
			else if (map->matrix[map->elm.y][map->elm.x] == 'V')
				map->elm.v++;
			else if (map->matrix[map->elm.y][map->elm.x] != '1' &&
						map->matrix[map->elm.y][map->elm.x] != '0')
				map->error = 5;
			map->elm.x++;
		}
	}
	if (map->elm.c == 0 || map->elm.p == 0 || map->elm.e == 0
		|| map->elm.e > 1 || map->elm.p > 1)
		map->error = 6;
}
