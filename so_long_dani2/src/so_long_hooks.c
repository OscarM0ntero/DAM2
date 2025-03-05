/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   so_long_hooks.c                                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/12/01 16:27:53 by omontero          #+#    #+#             */
/*   Updated: 2023/01/09 15:29:32 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "so_long.h"

void	keyhook(mlx_key_data_t keydata, void *param)
{
	t_map	*map;

	map = param;
	if (keydata.key == MLX_KEY_W && keydata.action == MLX_PRESS
		&& map->move)
		move(1, map);
	if (keydata.key == MLX_KEY_D && keydata.action == MLX_PRESS
		&& map->move)
		move(2, map);
	if (keydata.key == MLX_KEY_S && keydata.action == MLX_PRESS
		&& map->move)
		move(3, map);
	if (keydata.key == MLX_KEY_A && keydata.action == MLX_PRESS
		&& map->move)
		move(4, map);
	if ((keydata.key == MLX_KEY_ESCAPE && keydata.action == MLX_PRESS)
		|| ((keydata.key == MLX_KEY_ENTER && keydata.action == MLX_PRESS)
			&& (map->map_finished || map->game_over)))
		mlx_close_window(map->mlx);
}

void	animhook(void *param)
{
	t_map	*map;

	map = param;
	if (map->time % 30 == 0)
	{
		map->anim.frame_water++;
		regenerate_water(map);
		if (map->time % 100 == 0)
			map->anim.frame_water = 0;
	}
	if (map->time % 15 == 0)
	{
		regenerate_enemies(map);
		map->anim.frame_enemy++;
		if (map->anim.frame_enemy == 4)
			map->anim.frame_enemy = 0;
	}
}
