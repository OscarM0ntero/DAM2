/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   graphics_init.c                                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: djanssen <djanssen@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/04/07 11:03:12 by djanssen          #+#    #+#             */
/*   Updated: 2023/01/10 12:50:50 by djanssen         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "../../so_long.h"

/**
 * @brief This function will load every '*.xpm42' into their correspondent 
 * 'xpm[IMG_COUNT]' as textures. 
 * 
 * @param map Main struct.
 * @param xpm Struct containing data regarding an XPM image.
 * @return 0 if failure, 1 if success.
 */
void	ft_loadimg(t_map *m, xpm_t **xpm)
{
	m->mlx = mlx_init(m->x_axis * W, m->y_axis * H, "so_long", true);
	xpm[tile] = mlx_load_xpm42("p/solid/water.xpm42");
	xpm[wall] = mlx_load_xpm42("p/solid/grass.xpm42");
	xpm[topwall] = mlx_load_xpm42("p/solid/grass_top.xpm42");
	xpm[botwall] = mlx_load_xpm42("p/solid/grass_bot.xpm42");
	xpm[blwall] = mlx_load_xpm42("p/solid/corner_bot_left.xpm42");
	xpm[brwall] = mlx_load_xpm42("p/solid/corner_bot_right.xpm42");
	xpm[ulwall] = mlx_load_xpm42("p/solid/corner_top_left.xpm42");
	xpm[urwall] = mlx_load_xpm42("p/solid/corner_top_right.xpm42");
	xpm[leftwall] = mlx_load_xpm42("p/solid/grass_left.xpm42");
	xpm[rightwall] = mlx_load_xpm42("p/solid/grass_right.xpm42");
	xpm[playerright] = mlx_load_xpm42("p/inter/duck.xpm42");
	xpm[playerleft] = mlx_load_xpm42("p/inter/dleft.xpm42");
	xpm[playerup] = mlx_load_xpm42("p/inter/dup.xpm42");
	xpm[playerdown] = mlx_load_xpm42("p/inter/ddown.xpm42");
	xpm[coin0] = mlx_load_xpm42("p/inter/flower_mid.xpm42");
	xpm[coin1] = mlx_load_xpm42("p/inter/flower_open.xpm42");
	xpm[ext] = mlx_load_xpm42("p/inter/exit_basket.xpm42");
	xpm[enemy] = mlx_load_xpm42("p/inter/shark.xpm42");
}

/**
 * @brief This function will convert textures from 'ft_loadimg' to images
 * into their correspondent 'img[IMG_COUNT]'.
 * 
 * @param m Main struct.
 * @param xpm Struct containing data regarding an XPM image.
 * @param img An image that can be rendered.
 * @return 1 if success, 0 if failure.
 */
void	ft_texturetoimg(t_map *m, xpm_t **xpm, mlx_image_t **img)
{
	img[tile] = mlx_texture_to_image(m->mlx, &xpm[tile]->texture);
	img[wall] = mlx_texture_to_image(m->mlx, &xpm[wall]->texture);
	img[topwall] = mlx_texture_to_image(m->mlx, &xpm[topwall]->texture);
	img[botwall] = mlx_texture_to_image(m->mlx, &xpm[botwall]->texture);
	img[blwall] = mlx_texture_to_image(m->mlx, &xpm[blwall]->texture);
	img[brwall] = mlx_texture_to_image(m->mlx, &xpm[brwall]->texture);
	img[ulwall] = mlx_texture_to_image(m->mlx, &xpm[ulwall]->texture);
	img[urwall] = mlx_texture_to_image(m->mlx, &xpm[urwall]->texture);
	img[leftwall] = mlx_texture_to_image(m->mlx, &xpm[leftwall]->texture);
	img[rightwall] = mlx_texture_to_image(m->mlx, &xpm[rightwall]->texture);
	img[playerright] = mlx_texture_to_image(m->mlx, &xpm[playerright]->texture);
	img[playerleft] = mlx_texture_to_image(m->mlx, &xpm[playerleft]->texture);
	img[playerup] = mlx_texture_to_image(m->mlx, &xpm[playerup]->texture);
	img[playerdown] = mlx_texture_to_image(m->mlx, &xpm[playerdown]->texture);
	img[ext] = mlx_texture_to_image(m->mlx, &xpm[ext]->texture);
	img[coin0] = mlx_texture_to_image(m->mlx, &xpm[coin0]->texture);
	img[coin1] = mlx_texture_to_image(m->mlx, &xpm[coin1]->texture);
	img[enemy] = mlx_texture_to_image(m->mlx, &xpm[enemy]->texture);
}

void	ft_init_graphics(t_map *map)
{
	ft_loadimg(map, map->xpm);
	ft_texturetoimg(map, map->xpm, map->img);
	ft_print_images(map);
	ft_print_objects(map);
	ft_print_strings(map);
}
