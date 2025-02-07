/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   so_long_textures.c                                 :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/12/01 16:27:53 by omontero          #+#    #+#             */
/*   Updated: 2023/01/09 12:30:46 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "so_long.h"

void	refresh_player(t_map *map, int dir)
{
	if (!map->p_look)
		mlx_draw_texture(map->mx_add[0].img,
			&map->sprites.player_back->texture, 0, 0);
	else
		mlx_draw_texture(map->mx_add[0].img,
			&map->sprites.player->texture, 0, 0);
	map->mx_add[0].x = map->p_x;
	map->mx_add[0].y = map->p_y;
	if (dir == 1)
		map->mx_add[0].img->instances[0].y -= IMG_H;
	else if (dir == 2)
		map->mx_add[0].img->instances[0].x += IMG_W;
	else if (dir == 3)
		map->mx_add[0].img->instances[0].y += IMG_H;
	else if (dir == 4)
		map->mx_add[0].img->instances[0].x -= IMG_W;
}

void	regenerate_water(t_map *map)
{
	size_t	i;
	xpm_t	*tx;

	i = 0;
	while (i < map->n_images)
	{
		if (map->mtrx[i].c == '0')
		{
			tx = texture(map, map->mtrx[i].x, map->mtrx[i].y);
			mlx_draw_texture(map->mtrx[i].img, &tx->texture, 0, 0);
		}
		i++;
	}
}

void	regenerate_enemies(t_map *map)
{
	size_t	i;
	xpm_t	*tx;

	i = 0;
	while (i < map->n_extra)
	{
		if (map->mx_add[i].c == 'V')
		{
			tx = enemy_selector(map);
			mlx_draw_texture(map->mx_add[i].img, &tx->texture, 0, 0);
		}
		i++;
	}
}

void	change_flowers(t_map *map)
{
	size_t	i;
	xpm_t	*tx;

	i = 0;
	while (i < map->n_images)
	{
		if (map->mtrx[i].c == 'C')
		{
			tx = texture(map, map->mtrx[i].x, map->mtrx[i].y);
			mlx_draw_texture(map->mtrx[i].img, &tx->texture, 0, 0);
		}
		i++;
	}
}

xpm_t	*texture(t_map *map, size_t x, size_t y)
{
	if (map->mtrx[y * map->n_chars + x].c == '1')
		return (walls_select(map, x, y));
	else
		return (floor_select(map, x, y, map->anim.frame_water));
	return (0);
}
