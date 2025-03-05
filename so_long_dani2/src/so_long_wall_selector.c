/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   so_long_wall_selector.c                            :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/12/01 16:27:53 by omontero          #+#    #+#             */
/*   Updated: 2023/01/09 12:44:58 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "so_long.h"

xpm_t	*inner_walls_select_3(t_map *map, size_t x, size_t y)
{
	char	**s;

	s = map->str;
	if (s[y - 1][x] == '1' && s[y + 1][x] != '1'
		&& s[y][x + 1] == '1' && s[y][x - 1] == '1')
		return (map->sprites.grass.grass_to);
	else if (s[y - 1][x] != '1' && s[y + 1][x] == '1'
		&& s[y][x + 1] == '1' && s[y][x - 1] == '1')
		return (map->sprites.grass.grass_b);
	else if (s[y - 1][x] == '1' && s[y + 1][x] == '1'
		&& s[y][x + 1] != '1' && s[y][x - 1] == '1')
		return (map->sprites.grass.grass_r);
	else if (s[y - 1][x] == '1' && s[y + 1][x] == '1'
		&& s[y][x + 1] == '1' && s[y][x - 1] != '1')
		return (map->sprites.grass.grass_l);
	else if (s[y - 1][x] != '1' && s[y + 1][x] != '1'
		&& s[y][x + 1] != '1' && s[y][x - 1] != '1')
		return (map->sprites.grass.grass);
	return (map->sprites.grass.grass);
}

xpm_t	*inner_walls_select_2(t_map *map, size_t x, size_t y)
{
	char	**s;

	s = map->str;
	if (s[y - 1][x] == '1' && s[y + 1][x] == '1'
		&& s[y][x + 1] != '1' && s[y][x - 1] != '1')
		return (map->sprites.grass.grass_corridor_t_b);
	else if (s[y - 1][x] != '1' && s[y + 1][x] != '1'
		&& s[y][x + 1] == '1' && s[y][x - 1] == '1')
		return (map->sprites.grass.grass_corridor_r_l);
	else if (s[y - 1][x] == '1' && s[y + 1][x] != '1'
		&& s[y][x + 1] != '1' && s[y][x - 1] == '1')
		return (map->sprites.grass.grass_corner_b_r);
	else if (s[y - 1][x] == '1' && s[y + 1][x] != '1'
		&& s[y][x + 1] == '1' && s[y][x - 1] != '1')
		return (map->sprites.grass.grass_corner_b_l);
	else if (s[y - 1][x] != '1' && s[y + 1][x] == '1'
		&& s[y][x + 1] != '1' && s[y][x - 1] == '1')
		return (map->sprites.grass.grass_corner_t_l);
	else if (s[y - 1][x] != '1' && s[y + 1][x] == '1'
		&& s[y][x + 1] == '1' && s[y][x - 1] != '1')
		return (map->sprites.grass.grass_corner_t_r);
	return (inner_walls_select_3(map, x, y));
}

xpm_t	*inner_walls_select(t_map *map, size_t x, size_t y)
{
	char	**s;

	s = map->str;
	if (s[y - 1][x] != '1' && s[y + 1][x] != '1'
		&& s[y][x + 1] != '1' && s[y][x - 1] != '1')
		return (map->sprites.grass.grass_island);
	else if (s[y - 1][x] == '1' && s[y + 1][x] != '1'
		&& s[y][x + 1] != '1' && s[y][x - 1] != '1')
		return (map->sprites.grass.grass_end_b);
	else if (s[y - 1][x] != '1' && s[y + 1][x] == '1'
		&& s[y][x + 1] != '1' && s[y][x - 1] != '1')
		return (map->sprites.grass.grass_end_to);
	else if (s[y - 1][x] != '1' && s[y + 1][x] != '1'
		&& s[y][x + 1] == '1' && s[y][x - 1] != '1')
		return (map->sprites.grass.grass_end_l);
	else if (s[y - 1][x] != '1' && s[y + 1][x] != '1'
		&& s[y][x + 1] != '1' && s[y][x - 1] == '1')
		return (map->sprites.grass.grass_end_r);
	return (inner_walls_select_2(map, x, y));
}

xpm_t	*walls_select(t_map *map, size_t x, size_t y)
{
	if (x == 0 && y == 0 && map->str[y + 1][x + 1] != '1')
		return (map->sprites.grass.corner_t_l);
	else if (x == 0 && y == map->n_lines - 1 && map->str[y - 1][x + 1] != '1')
		return (map->sprites.grass.corner_b_l);
	else if (x == map->n_chars - 1 && y == 0 && map->str[y + 1][x - 1] != '1')
		return (map->sprites.grass.corner_t_r);
	else if (x == map->n_chars - 1 && y == map->n_lines - 1
		&& map->str[y - 1][x + 1] != '1')
		return (map->sprites.grass.corner_b_r);
	else if (x == 0 && map->str[y][x + 1] != '1')
		return (map->sprites.grass.grass_r);
	else if (y == 0 && map->str[y + 1][x] != '1')
		return (map->sprites.grass.grass_to);
	else if (x == map->n_chars - 1 && map->str[y][x - 1] != '1')
		return (map->sprites.grass.grass_l);
	else if (y == map->n_lines - 1 && map->str[y - 1][x] != '1')
		return (map->sprites.grass.grass_b);
	else if (x == 0 || y == 0 || x == map->n_chars - 1
		|| y == map->n_lines - 1)
		return (map->sprites.grass.grass);
	return (inner_walls_select(map, x, y));
}
