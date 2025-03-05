/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   so_long_assign.c                                   :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/12/01 16:27:53 by omontero          #+#    #+#             */
/*   Updated: 2023/01/09 12:28:07 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "so_long.h"

void	assign_grass(t_sprites *s)
{
	s->grass.corner_b_l = mlx_load_xpm42("pixelart/corner_b_l.xpm42");
	s->grass.corner_b_r = mlx_load_xpm42("pixelart/corner_b_r.xpm42");
	s->grass.corner_t_l = mlx_load_xpm42("pixelart/corner_t_l.xpm42");
	s->grass.corner_t_r = mlx_load_xpm42("pixelart/corner_t_r.xpm42");
	s->grass.grass_b = mlx_load_xpm42("pixelart/grass_b.xpm42");
	s->grass.grass_l = mlx_load_xpm42("pixelart/grass_l.xpm42");
	s->grass.grass_r = mlx_load_xpm42("pixelart/grass_r.xpm42");
	s->grass.grass_to = mlx_load_xpm42("pixelart/grass_t.xpm42");
	s->grass.grass_corner_b_l
		= mlx_load_xpm42("pixelart/grass_corner_b_l.xpm42");
	s->grass.grass_corner_b_r
		= mlx_load_xpm42("pixelart/grass_corner_b_r.xpm42");
	s->grass.grass_corner_t_l
		= mlx_load_xpm42("pixelart/grass_corner_t_l.xpm42");
	s->grass.grass_corner_t_r
		= mlx_load_xpm42("pixelart/grass_corner_t_r.xpm42");
	s->grass.grass_corridor_r_l
		= mlx_load_xpm42("pixelart/grass_corridor_r_l.xpm42");
	s->grass.grass_corridor_t_b
		= mlx_load_xpm42("pixelart/grass_corridor_t_b.xpm42");
	s->grass.grass_end_b = mlx_load_xpm42("pixelart/grass_end_b.xpm42");
	s->grass.grass_end_l = mlx_load_xpm42("pixelart/grass_end_l.xpm42");
	s->grass.grass_end_r = mlx_load_xpm42("pixelart/grass_end_r.xpm42");
	s->grass.grass_end_to = mlx_load_xpm42("pixelart/grass_end_t.xpm42");
	s->grass.grass_island = mlx_load_xpm42("pixelart/grass_island.xpm42");
}

void	assign_sprites(t_sprites *s)
{
	s->player = mlx_load_xpm42("pixelart/duck.xpm42");
	s->player_back = mlx_load_xpm42("pixelart/duck_back.xpm42");
	s->enemy1 = mlx_load_xpm42("pixelart/shark1.xpm42");
	s->enemy2 = mlx_load_xpm42("pixelart/shark2.xpm42");
	s->enemy3 = mlx_load_xpm42("pixelart/shark3.xpm42");
	s->floor_1 = mlx_load_xpm42("pixelart/water1.xpm42");
	s->floor_2 = mlx_load_xpm42("pixelart/water2.xpm42");
	s->floor_3 = mlx_load_xpm42("pixelart/water3.xpm42");
	s->floor_4 = mlx_load_xpm42("pixelart/water4.xpm42");
	s->collect_1 = mlx_load_xpm42("pixelart/flower_mid.xpm42");
	s->collect_2 = mlx_load_xpm42("pixelart/flower_open.xpm42");
	s->exit = mlx_load_xpm42("pixelart/basket.xpm42");
	s->fade1 = mlx_load_xpm42("pixelart/fade1.xpm42");
	s->fade2 = mlx_load_xpm42("pixelart/fade2.xpm42");
	s->fade3 = mlx_load_xpm42("pixelart/fade3.xpm42");
	s->fade4 = mlx_load_xpm42("pixelart/fade4.xpm42");
	s->fade5 = mlx_load_xpm42("pixelart/fade5.xpm42");
	s->grass.grass = mlx_load_xpm42("pixelart/grass.xpm42");
	assign_grass(s);
}

/**
 * @brief Assigns default values to map
 * @param map map pointer
 * @param p path to .ber file
 */
void	assign_to_map(t_map *map, char *path)
{
	map->clock = clock();
	map->move = 1;
	time(&map->time);
	map->time *= 100;
	map->n_extra = 0;
	map->img_assigned = 0;
	map->anim.frame_flower = 0;
	map->anim.frame_enemy = 0;
	map->anim.frame_player = 0;
	map->anim.frame_fade = 0;
	map->error = 0;
	map->n_chars = 0;
	map->path = ft_strdup(path);
	map->n_lines = count_lines(map->path);
	map->coins.n_coins = 0;
	map->n_enemies = 0;
	map->coins.c_count = 0;
	map->game_over = 0;
	map->map_finished = 0;
	map->mv_count = 0;
	map->total_frames = 0;
	map->anim.frame_water = 0;
	map->anim.frame_enemy = 0;
	assign_sprites(&map->sprites);
	map->p_look = 1;
}

/**
 * @brief Assigns the values to the matrix, 0 if its an object
 * 
 * @param map Pointer to the map
 */
void	assign_mtrx(t_map *map)
{
	size_t	i;
	size_t	j;
	size_t	count;
	size_t	count_rev;

	i = -1;
	count = 2;
	count_rev = map->n_extra - 1;
	while (++i < map->n_lines)
	{
		j = -1;
		while (++j < map->n_chars)
		{
			if (map->str[i][j] == ('C'))
				found_c(map, i, j, &count);
			else if (map->str[i][j] == ('V'))
				found_v(map, i, j, &count_rev);
			else if (map->str[i][j] == ('E'))
				found_e(map, i, j);
			else
				map->mtrx[i * map->n_chars + j].c = map->str[i][j];
			map->mtrx[i * map->n_chars + j].x = j;
			map->mtrx[i * map->n_chars + j].y = i;
		}
	}
}
