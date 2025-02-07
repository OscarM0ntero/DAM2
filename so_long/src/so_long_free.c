/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   so_long_free.c                                     :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/12/01 16:27:53 by omontero          #+#    #+#             */
/*   Updated: 2023/01/09 12:18:41 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "so_long.h"

void	show_leaks(void)
{
	system("leaks -q so_long");
}

void	delete_map(t_map *map)
{
	size_t	i;

	i = 0;
	while (i < map->n_images)
	{
		mlx_delete_image(map->mlx, map->mtrx[i].img);
		i++;
	}
	i = 0;
	while (i < map->n_extra)
	{
		mlx_delete_image(map->mlx, map->mx_add[i].img);
		i++;
	}
	mlx_delete_image(map->mlx, map->black[0]);
	mlx_delete_image(map->mlx, map->black[1]);
	mlx_delete_image(map->mlx, map->black[2]);
	mlx_delete_image(map->mlx, map->black[3]);
	mlx_delete_image(map->mlx, map->black[4]);
	mlx_delete_image(map->mlx, map->end);
	free(map->mtrx);
	free(map->mx_add);
	free(map->path);
}

void	delete_grass_tx(t_sprites *s)
{
	mlx_delete_texture(&s->grass.corner_b_l->texture);
	mlx_delete_texture(&s->grass.corner_b_r->texture);
	mlx_delete_texture(&s->grass.corner_t_l->texture);
	mlx_delete_texture(&s->grass.corner_t_r->texture);
	mlx_delete_texture(&s->grass.grass_b->texture);
	mlx_delete_texture(&s->grass.grass_l->texture);
	mlx_delete_texture(&s->grass.grass_r->texture);
	mlx_delete_texture(&s->grass.grass_to->texture);
	mlx_delete_texture(&s->grass.grass_corner_b_l->texture);
	mlx_delete_texture(&s->grass.grass_corner_b_r->texture);
	mlx_delete_texture(&s->grass.grass_corner_t_l->texture);
	mlx_delete_texture(&s->grass.grass_corner_t_r->texture);
	mlx_delete_texture(&s->grass.grass_corridor_r_l->texture);
	mlx_delete_texture(&s->grass.grass_corridor_t_b->texture);
	mlx_delete_texture(&s->grass.grass_end_b->texture);
	mlx_delete_texture(&s->grass.grass_end_l->texture);
	mlx_delete_texture(&s->grass.grass_end_r->texture);
	mlx_delete_texture(&s->grass.grass_end_to->texture);
	mlx_delete_texture(&s->grass.grass_island->texture);
}

void	delete_tx(t_map *map)
{
	t_sprites	*s;

	s = &map->sprites;
	mlx_delete_texture(&s->player->texture);
	mlx_delete_texture(&s->player_back->texture);
	mlx_delete_texture(&s->enemy1->texture);
	mlx_delete_texture(&s->enemy2->texture);
	mlx_delete_texture(&s->enemy3->texture);
	mlx_delete_texture(&s->floor_1->texture);
	mlx_delete_texture(&s->floor_2->texture);
	mlx_delete_texture(&s->floor_3->texture);
	mlx_delete_texture(&s->floor_4->texture);
	mlx_delete_texture(&s->collect_1->texture);
	mlx_delete_texture(&s->collect_2->texture);
	mlx_delete_texture(&s->exit->texture);
	mlx_delete_texture(&s->fade1->texture);
	mlx_delete_texture(&s->fade2->texture);
	mlx_delete_texture(&s->fade3->texture);
	mlx_delete_texture(&s->fade4->texture);
	mlx_delete_texture(&s->fade5->texture);
	mlx_delete_texture(&s->grass.grass->texture);
	delete_grass_tx(s);
}
