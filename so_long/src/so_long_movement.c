/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   so_long_movement.c                                 :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/12/01 16:27:53 by omontero          #+#    #+#             */
/*   Updated: 2023/01/09 12:30:59 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "so_long.h"

void	move_up(t_map *map)
{
	map->mv_count++;
	if (map->str[map->p_y - 1][map->p_x] == '0'
		|| (map->str[map->p_y - 1][map->p_x] == 'E'
		&& map->coins.n_coins == map->coins.c_count))
	{
		if (map->str[map->p_y - 1][map->p_x] == 'E'
			&& map->coins.n_coins == map->coins.c_count)
			map->map_finished = 1;
		map->str[map->p_y][map->p_x] = '0';
		map->p_y--;
		map->str[map->p_y][map->p_x] = 'P';
		refresh_player(map, 1);
	}
	else if (map->str[map->p_y - 1][map->p_x] == 'C')
	{
		coin_taken(map, map->p_x, map->p_y - 1);
		refresh_player(map, 1);
	}
	else if (map->str[map->p_y - 1][map->p_x] == 'V')
	{
		map->str[map->p_y][map->p_x] = '0';
		map->p_y--;
		map->game_over = 1;
		map->mx_add[0].img->enabled = 0;
	}
}

void	move_right(t_map *map)
{
	map->mv_count++;
	if (map->str[map->p_y][map->p_x + 1] == '0'
		|| (map->str[map->p_y][map->p_x + 1] == 'E'
		&& map->coins.n_coins == map->coins.c_count))
	{
		if ((map->str[map->p_y][map->p_x + 1] == 'E'
			&& map->coins.n_coins == map->coins.c_count))
			map->map_finished = 1;
		map->str[map->p_y][map->p_x] = '0';
		map->p_x++;
		map->str[map->p_y][map->p_x] = 'P';
		refresh_player(map, 2);
	}
	else if (map->str[map->p_y][map->p_x + 1] == 'C')
	{
		coin_taken(map, map->p_x + 1, map->p_y);
		refresh_player(map, 2);
	}
	else if (map->str[map->p_y][map->p_x + 1] == 'V')
	{
		map->str[map->p_y][map->p_x] = '0';
		map->p_x++;
		map->game_over = 1;
		map->mx_add[0].img->enabled = 0;
	}
}

void	move_down(t_map *map)
{
	map->mv_count++;
	if (map->str[map->p_y + 1][map->p_x] == '0'
		|| (map->str[map->p_y + 1][map->p_x] == 'E'
		&& map->coins.n_coins == map->coins.c_count))
	{
		if (map->str[map->p_y + 1][map->p_x] == 'E'
			&& map->coins.n_coins == map->coins.c_count)
			map->map_finished = 1;
		map->str[map->p_y][map->p_x] = '0';
		map->p_y++;
		map->str[map->p_y][map->p_x] = 'P';
		refresh_player(map, 3);
	}
	else if (map->str[map->p_y + 1][map->p_x] == 'C')
	{
		coin_taken(map, map->p_x, map->p_y + 1);
		refresh_player(map, 3);
	}
	else if (map->str[map->p_y + 1][map->p_x] == 'V')
	{
		map->str[map->p_y][map->p_x] = '0';
		map->p_y++;
		map->game_over = 1;
		map->mx_add[0].img->enabled = 0;
	}
}

void	move_left(t_map *map)
{
	map->mv_count++;
	if (map->str[map->p_y][map->p_x - 1] == '0'
		|| (map->str[map->p_y][map->p_x - 1] == 'E'
		&& map->coins.n_coins == map->coins.c_count))
	{
		if (map->str[map->p_y][map->p_x - 1] == 'E'
			&& map->coins.n_coins == map->coins.c_count)
			map->map_finished = 1;
		map->str[map->p_y][map->p_x] = '0';
		map->p_x--;
		map->str[map->p_y][map->p_x] = 'P';
		refresh_player(map, 4);
	}
	else if (map->str[map->p_y][map->p_x - 1] == 'C')
	{
		coin_taken(map, map->p_x - 1, map->p_y);
		refresh_player(map, 4);
	}
	else if (map->str[map->p_y][map->p_x - 1] == 'V')
	{
		map->str[map->p_y][map->p_x] = '0';
		map->p_x--;
		map->game_over = 1;
		map->mx_add[0].img->enabled = 0;
	}
}

void	move(int dir, t_map *map)
{
	char	**s;
	size_t	x;
	size_t	y;

	s = map->str;
	x = map->p_x;
	y = map->p_y;
	if (dir == 2)
		map->p_look = 1;
	else if (dir == 4)
		map->p_look = 0;
	if (dir == 1 && ((((s[y - 1][x] != '1') && s[y - 1][x] != 'E'))
		|| ((s[y - 1][x] == 'E' && map->coins.c_count == map->coins.n_coins))))
		move_up(map);
	else if (dir == 2 && ((((s[y][x + 1] != '1') && s[y][x + 1] != 'E'))
		|| ((s[y][x + 1] == 'E' && map->coins.c_count == map->coins.n_coins))))
		move_right(map);
	else if (dir == 3 && ((((s[y + 1][x] != '1') && s[y + 1][x] != 'E'))
		|| ((s[y + 1][x] == 'E' && map->coins.c_count == map->coins.n_coins))))
		move_down(map);
	else if (dir == 4 && ((((s[y][x - 1] != '1') && s[y][x - 1] != 'E'))
		|| ((s[y][x - 1] == 'E' && map->coins.c_count == map->coins.n_coins))))
		move_left(map);
	check_end(map);
	refresh_player(map, 0);
}
