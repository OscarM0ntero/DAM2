/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   so_long.h                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: djanssen <djanssen@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/09/26 09:48:59 by djanssen          #+#    #+#             */
/*   Updated: 2023/01/10 12:38:50 by djanssen         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#ifndef SO_LONG_H
# define SO_LONG_H

/* All includes */
# include "gnl/get_next_line.h"
# include "inc/MLX42_ubuntults/include/MLX42/MLX42.h"
# include <stdint.h>
# include <stdio.h>
# include <stdlib.h>
# include <string.h>
# include <unistd.h>
# define W 64
# define H 64

/* All structs */
typedef struct s_elements
{
	int			c;
	int			p;
	int			e;
	int			v;
	size_t		x;
	size_t		y;
}				t_elements;

typedef enum mlx_images
{
	wall,
	topwall,
	botwall,
	blwall,
	brwall,
	ulwall,
	urwall,
	leftwall,
	rightwall,
	shark,
	tile,
	playerright,
	playerleft,
	playerup,
	playerdown,
	ext,
	coin0,
	coin1,
	enemy,
	IMG_COUNT,
}				t_images;

typedef struct s_mapdata
{
	mlx_t		*mlx;
	int			f_counter;
	char		*path;
	char		**matrix;
	size_t		x_axis;
	size_t		y_axis;
	int			error;
	int			cc;
	t_elements	elm;
	size_t		player_x;
	size_t		player_y;
	int			game_finished;
	int			sharked;
	int			won;
	int			moves;
	mlx_image_t	*img[IMG_COUNT];
	xpm_t		*xpm[IMG_COUNT];
	mlx_image_t	*mvcounter;
	mlx_image_t	*ccounter;
}				t_map;

/* All function prototypes */
int32_t			main(int argc, char **argv);
void			ft_check_elements(t_map *map);
void			ft_check_lines(char *tmp, size_t current, t_map *fmap);
int				ft_print_error(int error);
void			ft_print_strings(t_map *m);
void			ft_print_objects(t_map *m);
void			ft_where_is_player(t_map *fmap);
int				ft_check_ones(char *tmp);
void			keyhook1(mlx_key_data_t keydata, void *param);
void			init_vars(t_map *fmap, char *input);
size_t			get_height(char *input);
void			ft_print_map(t_map *fmap);
t_map			read_map(char *input);
void			ft_move(t_map *map, int k);
void			moveup(t_map *map);
void			movedown(t_map *map);
void			moveright(t_map *map);
void			moveleft(t_map *map);
void			ft_loadimg(t_map *map, xpm_t **xpm);
void			ft_texturetoimg(t_map *m, xpm_t **xpm, mlx_image_t **img);
void			ft_init_graphics(t_map *map);
void			walls(t_map *m, int i, int j);
void			ft_print_images(t_map *m);
void			ft_directions_enable(t_map *m, int k);
char			*ft_itoa(int n);
void			print_gg(t_map *m);
#endif