/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   so_long.c                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/12/01 16:27:53 by omontero          #+#    #+#             */
/*   Updated: 2023/01/10 13:49:44 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "so_long.h"

/**
 * @brief Generate the background images
 * 
 * @param map Map pointer
 * @param x X position of the chunk we are generating the image of
 * @param y Y pos
 */
void	generate_image(t_map *map, int32_t x, int32_t y)
{
	xpm_t			*tx;

	tx = texture(map, x, y);
	map->mtrx[(y * map->n_chars) + x].img
		= mlx_texture_to_image(map->mlx, &tx->texture);
	if (!map->img_assigned)
		map->img_assigned = 1;
}

void	create_extra_images(t_map *map)
{
	size_t		i;
	xpm_t		*tx;

	i = -1;
	while (++i < map->n_extra)
	{
		tx = extra_selector(map, map->mx_add[i].c);
		map->mx_add[i].img = mlx_texture_to_image(map->mlx, &tx->texture);
	}
}

void	map_to_window(t_map *map)
{
	size_t			i;

	i = -1;
	while (++i < map->n_images)
	{
		mlx_image_to_window(map->mlx, map->mtrx[i].img, map->mtrx[i].x * IMG_W,
			map->mtrx[i].y * IMG_H);
	}
	i = map->n_extra + 1;
	while (--i)
	{
		mlx_image_to_window(map->mlx, map->mx_add[i - 1].img,
			map->mx_add[i - 1].x * IMG_W, map->mx_add[i - 1].y * IMG_H);
	}
}

void	generate_map(t_map *map)
{
	size_t	i;
	size_t	j;

	i = -1;
	while (++i < map->n_lines)
	{
		j = -1;
		while (++j < map->n_chars)
		{
			generate_image(map, j, i);
		}
	}
	create_extra_images(map);
	map_to_window(map);
	map->mtrx[0].img = mlx_put_string(map->mlx, "Moves:", 0, 0);
	map->total_frames++;
}

int	main(int argc, char **argv)
{
	t_map	m;
	if (argc != 2)
		return (printf("Error: No map introduced.\n"), 1);
	start(&m, argv[1]);
	if (error_handler(m.error))
		exit(EXIT_FAILURE);
	m.mlx = mlx_init(m.n_chars * IMG_W, m.n_lines * IMG_H, "So_Long", true);
	if (!m.mlx)
		exit(EXIT_FAILURE);
	if (!m.error)
		generate_map(&m);
	mlx_key_hook(m.mlx, &keyhook, &m);
	mlx_loop_hook(m.mlx, &animhook, &m);
	mlx_loop_hook(m.mlx, &timer, &m);
	mlx_loop(m.mlx);
	mlx_terminate(m.mlx);
	delete_tx(&m);
	delete_map(&m);
	return (EXIT_SUCCESS);
}

