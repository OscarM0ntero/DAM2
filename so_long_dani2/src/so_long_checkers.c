/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   so_long_checkers.c                                 :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/12/01 16:27:53 by omontero          #+#    #+#             */
/*   Updated: 2023/01/09 12:29:59 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "so_long.h"

/*
*	Error code
*	1 = Empty file
*	2 = Different number of chars per line
*	3 = Invalid char found !(0, 1, P, E, C, V)
*	4 = Not surrounded by 1s (not closed)
*	5 = No player (P) in map
*	6 = More than one player (P) in map
*	7 = No exit (E) in map
*	8 = No coin (C) in map
*/

int	error_handler(int error)
{
	if (error == 1)
		return (write(1, "Error. Empty file.\n", 19), 1);
	else if (error == 2)
		return (write(1, "Error. Different lines lenghts.\n", 32), 1);
	else if (error == 3)
		return (write(1, "Error. Invalid char in map (Allowed: 01PECV).\n",	46), 1);
	else if (error == 4)
		return (write(1, "Error. Map not surrounded by 1's.\n", 34), 1);
	else if (error == 5)
		return (write(1, "Error. Player (P) not found in map.\n", 36), 1);
	else if (error == 6)
		return (write(1, "Error. More than 1 player in map.\n", 34), 1);
	else if (error == 7)
		return (write(1, "Error. Exit (E) not found in map.\n", 34), 1);
	else if (error == 8)
		return (write(1, "Error. Coin (C) not found in map.\n", 34), 1);
	return (0);
}

void	check_end(t_map *map)
{
	if (map->game_over || map->map_finished)
		map->move = 0;
}

void	check_player_coords(t_map *map)
{
	int	i;
	int	j;
	int	count;

	count = 0;
	i = -1;
	while (++i < (int)map->n_lines && !map->error)
	{
		j = -1;
		while (++j < (int)map->n_chars && !map->error)
		{
			if (map->str[i][j] == 'P' && !map->error)
			{
				if (++count == 1)
				{
					map->p_x = (size_t)j;
					map->p_y = (size_t)i;
				}
				else if (count == 2)
					map->error = 6;
			}
		}
	}
	if (!count && !map->error)
		map->error = 5;
}

void	check_exit_and_coin(t_map *map)
{
	int		i;
	int		j;
	size_t	c_coin;
	size_t	c_exit;

	c_coin = 0;
	c_exit = 0;
	i = -1;
	while (++i < (int)map->n_lines && !map->error)
	{
		j = -1;
		while (++j < (int)map->n_chars && !map->error)
		{
			if (map->str[i][j] == 'E' && !map->error)
				c_exit++;
			if (map->str[i][j] == 'C' && !map->error)
				c_coin++;
		}
	}
	if (!c_exit && !map->error)
		map->error = 7;
	else if (!c_coin && !map->error)
		map->error = 8;
	map->coins.n_coins = c_coin;
}

void	check_enemies(t_map *map)
{
	int		i;
	int		j;

	i = -1;
	while (++i < (int)map->n_lines)
	{
		j = -1;
		while (++j < (int)map->n_chars)
		{
			if (map->str[i][j] == 'V')
				map->n_enemies++;
		}
	}
}
