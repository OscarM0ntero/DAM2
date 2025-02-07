/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   so_long_fade.c                                     :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/12/01 16:27:53 by omontero          #+#    #+#             */
/*   Updated: 2023/01/09 12:17:51 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "so_long.h"

mlx_texture_t	*fade_selector(t_map *map)
{
	if (!map->anim.frame_fade)
		return (&map->sprites.fade1->texture);
	else if (map->anim.frame_fade == 1)
		return (&map->sprites.fade2->texture);
	else if (map->anim.frame_fade == 2)
		return (&map->sprites.fade3->texture);
	else if (map->anim.frame_fade == 3)
		return (&map->sprites.fade4->texture);
	return (&map->sprites.fade5->texture);
	map->anim.frame_enemy++;
}

void	fade_black(t_map *map)
{
	mlx_image_t		*b_screen;
	size_t			i;
	size_t			j;
	mlx_texture_t	*tx;

	tx = fade_selector(map);
	b_screen = mlx_new_image(map->mlx, map->n_chars * IMG_W,
			map->n_lines * IMG_H);
	memset(b_screen->pixels, 255, b_screen->width * b_screen->height
		* sizeof(int));
	i = -1;
	while (++i < map->n_lines)
	{
		j = -1;
		while (++j < map->n_chars)
		{
			mlx_draw_texture(b_screen, tx, j * IMG_W, i * IMG_H);
		}
	}
	mlx_image_to_window(map->mlx, b_screen, 0, 0);
	map->black[map->anim.frame_fade] = b_screen;
}

void	end_string(t_map *map)
{
	if (map->anim.frame_fade == 5 && map->map_finished)
		map->end = mlx_put_string(map->mlx, "YOU WON!", map->n_chars
				* (IMG_W / 2) - (IMG_W / 2), map->n_lines
				* (IMG_H / 2) - (IMG_H / 2));
	else if (map->anim.frame_fade == 5 && map->game_over)
	{
		map->end = mlx_put_string(map->mlx, "YOU DIED...", map->n_chars
				* (IMG_W / 2) - (IMG_W / 2), map->n_lines
				* (IMG_H / 2) - (IMG_H / 2));
	}
}
