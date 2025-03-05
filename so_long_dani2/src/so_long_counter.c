/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   so_long_counter.c                                  :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/12/01 16:27:53 by omontero          #+#    #+#             */
/*   Updated: 2023/01/09 12:18:00 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "so_long.h"

char	*counter_moves(t_map *map)
{
	char	*c;
	char	*tmp;

	c = new_itoa(map->mv_count);
	tmp = c;
	c = ft_strjoin(" Moves: ", c);
	free (tmp);
	return (c);
}

char	*counter_coins(t_map *map)
{
	char	*c;
	char	*d;
	char	*tmp;

	c = new_itoa(map->coins.c_count);
	tmp = c;
	c = ft_strjoin(" Flowers: ", c);
	free (tmp);
	tmp = c;
	c = ft_strjoin(c, "/");
	free (tmp);
	d = new_itoa(map->coins.n_coins);
	tmp = c;
	c = ft_strjoin(c, d);
	free (tmp);
	free (d);
	return (c);
}

void	print_counters(t_map *map)
{
	char	*str;
	char	*moves;
	char	*coins;

	moves = counter_moves(map);
	coins = counter_coins(map);
	str = ft_strjoin(moves, coins);
	free (moves);
	free (coins);
	mlx_delete_image(map->mlx, map->mtrx[0].img);
	map->mtrx[0].img = mlx_put_string(map->mlx, str, 0, 0);
	free(str);
}
