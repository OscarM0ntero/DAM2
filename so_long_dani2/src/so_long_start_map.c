/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   so_long_start_map.c                                :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/12/01 16:27:53 by omontero          #+#    #+#             */
/*   Updated: 2023/01/09 12:28:28 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "so_long.h"

/**
 * @brief Reads a .ber file and saves the map on a strings array
 * 
* @param p path to .ber file
 * @return New map created
 */
t_map	read_map(char *p)
{
	t_map	new_map;
	int		fd;
	char	*line;
	size_t	n_lin;

	n_lin = 0;
	assign_to_map(&new_map, p);
	//printf("%ld", new_map.n_lines);
	if (!new_map.n_lines)
		new_map.error = 1;
	if (new_map.error)
		return (new_map);
	fd = open(new_map.path, O_RDONLY);
	line = get_next_line(fd);
	new_map.n_chars = ft_strlen(line);
	new_map.n_images = new_map.n_lines * new_map.n_chars;
	new_map.str = (char **)malloc(new_map.n_lines * sizeof(char *));
	while (n_lin < new_map.n_lines && !new_map.error)
	{
		check_line(line, n_lin, &new_map);
		new_map.str[n_lin] = line;
		line = get_next_line(fd);
		//if(line == NULL)
		//	new_map.error = 1;
		n_lin++;
	}
	close(fd);
	return (new_map);
}

void	found_c(t_map *map, size_t i, size_t j, size_t *c)
{
	map->mtrx[i * map->n_chars + j].c = '0';
	map->mx_add[*c].c = map->str[i][j];
	map->mx_add[*c].x = j;
	map->mx_add[*c].y = i;
	(*c)++;
}

void	found_v(t_map *map, size_t i, size_t j, size_t *c)
{
	map->mtrx[i * map->n_chars + j].c = '0';
	map->mx_add[*c].c = map->str[i][j];
	map->mx_add[*c].x = j;
	map->mx_add[*c].y = i;
	(*c)--;
}

void	found_e(t_map *map, size_t i, size_t j)
{
	map->mtrx[i * map->n_chars + j].c = '0';
	map->mx_add[1].c = map->str[i][j];
	map->mx_add[1].x = j;
	map->mx_add[1].y = i;
}

/**
 * @brief Starts the assignation of values, reading the map and 
 * checking the coords of the props, assigning the size of the arrays
 * 
 * @param map mapa
 * @param p path of the .ber file
 */
void	start(t_map *map, char *p)
{
	*map = read_map(p);
	if (map->error)
		return ;
	check_player_coords(map);
	if (map->error)
		return ;
	check_exit_and_coin(map);
	check_enemies(map);
	map->n_extra = map->coins.n_coins + map->n_enemies + 2;
	map->n_total = map->n_images + map->n_extra;
	map->mtrx = (t_matrix_sq *)malloc(map->n_images * sizeof(t_matrix_sq));
	map->mx_add = (t_matrix_sq *)malloc((map->n_extra * sizeof(t_matrix_sq)));
	assign_mtrx(map);
	map->mx_add[0].c = 'P';
	map->mtrx[map->p_y * map->n_chars + map->p_x].c = '0';
	map->mx_add[0].x = map->p_x;
	map->mx_add[0].y = map->p_y;
	map->black = (mlx_image_t **)malloc(5 * sizeof(mlx_image_t *));
}
