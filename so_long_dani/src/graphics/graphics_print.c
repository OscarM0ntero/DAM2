/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   graphics_print.c                                   :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: djanssen <djanssen@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/04/07 11:03:12 by djanssen          #+#    #+#             */
/*   Updated: 2023/01/10 13:06:03 by djanssen         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "../../so_long.h"

/**
 * @brief This function will print the map's walls.
 * 
 * @param m Main struct.
*/
void	walls(t_map *m, int i, int j)
{
	if (i == 0 && j != 0 && j != (int)m->x_axis - 1
		&& m->matrix[i + 1][j] != '1')
		mlx_image_to_window(m->mlx, m->img[topwall], j * W, i * H);
	else if (i == (int)m->y_axis - 1 && j != 0 && j != (int)m->x_axis - 1)
		mlx_image_to_window(m->mlx, m->img[botwall], j * W, i * H);
	else if (j == 0 && i != 0 && i != (int)m->y_axis - 1)
		mlx_image_to_window(m->mlx, m->img[leftwall], j * W, i * H);
	else if (j == (int)m->x_axis - 1 && i != 0 && i != (int)m->y_axis - 1)
		mlx_image_to_window(m->mlx, m->img[rightwall], j * W, i * H);
	else if (j == 0 && i == 0)
		mlx_image_to_window(m->mlx, m->img[ulwall], j * W, i * H);
	else if (j == (int)m->x_axis - 1 && i == 0)
		mlx_image_to_window(m->mlx, m->img[urwall], j * W, i * H);
	else if (j == 0 && i == (int)m->y_axis - 1)
		mlx_image_to_window(m->mlx, m->img[blwall], j * W, i * H);
	else if (j == (int)m->x_axis - 1 && i == (int)m->y_axis - 1)
		mlx_image_to_window(m->mlx, m->img[brwall], j * W, i * H);
	else if (m->matrix [i + 1][j] != '1')
		mlx_image_to_window(m->mlx, m->img[topwall], j * W, i * H);
	else
		mlx_image_to_window(m->mlx, m->img[wall], j * W, i * H);
}

void	ft_print_conditioners(t_map *m, int i, int j)
{
	if (m->matrix[i][j] == '1')
		walls(m, i, j);
	if (m->matrix[i][j] == 'C')
	{
		mlx_image_to_window(m->mlx, m->img[tile], j * W, i * H);
		mlx_image_to_window(m->mlx, m->img[coin1], j * W, i * H);
		mlx_image_to_window(m->mlx, m->img[coin0], j * W, i * H);
	}
	if (m->matrix[i][j] == '0' || m->matrix[i][j] == 'P')
		mlx_image_to_window(m->mlx, m->img[tile], j * W, i * H);
	if (m->matrix[i][j] == 'E')
		mlx_image_to_window(m->mlx, m->img[ext], j * W, i * H);
	if (m->matrix[i][j] == 'V')
	{
		mlx_image_to_window(m->mlx, m->img[tile], j * W, i * H);
		mlx_image_to_window(m->mlx, m->img[enemy], j * W, i * H);
	}
}

/**
 * @brief This function will print every image and the move counter
 *  into the MLX instance.
 * 
 * @param m Main struct.
 */
void	ft_print_images(t_map *m)
{
	int		i;
	int		j;

	i = -1;
	while (m->matrix[++i] && i < (int)m->y_axis)
	{
		j = -1;
		while (++j >= 0 && j < (int)m->x_axis)
			ft_print_conditioners(m, i, j);
	}
}

void	ft_print_objects(t_map *m)
{
	mlx_image_to_window(m->mlx, m->img[playerright],
		m->player_x * W, m->player_y * H);
	mlx_image_to_window(m->mlx, m->img[playerleft],
		m->player_x * W, m->player_y * H);
	mlx_image_to_window(m->mlx, m->img[playerup],
		m->player_x * W, m->player_y * H);
	mlx_image_to_window(m->mlx, m->img[playerdown],
		m->player_x * W, m->player_y * H);
	m->img[playerleft]->instances[0].enabled = 0;
	m->img[playerdown]->instances[0].enabled = 0;
	m->img[playerup]->instances[0].enabled = 0;
}

void	ft_print_strings(t_map *m)
{
	char	*itoad;
	char	*itoad2;
	char	*tmp;

	tmp = ft_itoa(m->moves);
	itoad = ft_strnjoin("Moves: ", tmp, 2);
	free (tmp);
	tmp = ft_itoa(m->cc);
	itoad2 = ft_strnjoin("Coins: ", tmp, 2);
	free (tmp);
	tmp = itoad2;
	itoad2 = ft_strnjoin(itoad2, "/", 1);
	free (tmp);
	tmp = ft_itoa(m->elm.c);
	itoad2 = ft_strjoin(itoad2, tmp);
	free (tmp);
	if (m->moves > 0)
	{
		mlx_delete_image(m->mlx, m->mvcounter);
		mlx_delete_image(m->mlx, m->ccounter);
	}
	m->mvcounter = mlx_put_string(m->mlx, itoad, 0, 0);
	m->ccounter = mlx_put_string(m->mlx, itoad2, 128, 0);
	free (itoad);
	free (itoad2);
}
