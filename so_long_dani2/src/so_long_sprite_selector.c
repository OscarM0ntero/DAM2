/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   so_long_sprite_selector.c                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/12/01 16:27:53 by omontero          #+#    #+#             */
/*   Updated: 2023/01/09 12:31:23 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "so_long.h"

/*xpm_t	*floor_select(t_map *map, size_t x, size_t y, size_t frame)
{
	if (x % 2 == 0 && y % 2 == 0)
		frame += 1;
	else if (x % 2 == 1 && y % 2 == 0)
		frame += 2;
	else if (x % 2 == 0 && y % 2 == 1)
		frame += 3;
	else
		frame += 4;
	if (frame % 4 == 0)
		return (map->sprites.floor_1);
	else if (frame % 4 == 1)
		return (map->sprites.floor_2);
	else if (frame % 4 == 2)
		return (map->sprites.floor_3);
	return (map->sprites.floor_4);
}*/

void	coin_taken(t_map *map, size_t x, size_t y)
{
	size_t	i;

	i = 0;
	map->str[map->p_y][map->p_x] = '0';
	map->p_x = x;
	map->p_y = y;
	map->str[map->p_y][map->p_x] = 'P';
	while (i < map->n_extra)
	{
		if (map->mx_add[i].c == 'C' && map->mx_add[i].x == map->p_x
			&& map->mx_add[i].y == map->p_y)
			mlx_draw_texture(map->mx_add[i].img,
				&map->sprites.collect_2->texture, 0, 0);
		i++;
	}
	map->coins.c_count++;
}

xpm_t	*floor_select(t_map *map, size_t x, size_t y, size_t frame)
{
	int	a;

	a = rand();
	(void)frame;
	(void)x;
	(void)y;
	if (a % 4 == 0)
		return (map->sprites.floor_1);
	else if (a % 4 == 1)
		return (map->sprites.floor_2);
	else if (a % 4 == 2)
		return (map->sprites.floor_3);
	return (map->sprites.floor_4);
}

xpm_t	*flower_select(t_map *map, size_t x, size_t y, size_t frame)
{
	if (x % 2 == 0 && y % 2 == 0)
		frame += 1;
	else if (x % 2 == 1 && y % 2 == 0)
		frame += 2;
	else if (x % 2 == 0 && y % 2 == 1)
		frame += 3;
	else
		frame += 4;
	if (frame % 4 == 0)
		return (map->sprites.floor_1);
	else if (frame % 4 == 1)
		return (map->sprites.floor_2);
	else if (frame % 4 == 2)
		return (map->sprites.floor_3);
	return (map->sprites.floor_4);
}

xpm_t	*enemy_selector(t_map *map)
{
	if (map->anim.frame_enemy == 0)
		return (map->sprites.enemy1);
	else if (map->anim.frame_enemy == 2)
		return (map->sprites.enemy3);
	return (map->sprites.enemy2);
}

xpm_t	*extra_selector(t_map *map, char c)
{
	if (c == 'P')
		return (map->sprites.player);
	else if (c == 'V')
		return (enemy_selector(map));
	else if (c == 'E')
		return (map->sprites.exit);
	else if (c == 'C')
		return (map->sprites.collect_1);
	return (map->sprites.collect_1);
}
