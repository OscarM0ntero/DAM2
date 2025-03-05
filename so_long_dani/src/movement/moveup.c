/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   moveup.c                                           :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: djanssen <djanssen@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/04/07 11:03:12 by djanssen          #+#    #+#             */
/*   Updated: 2023/01/10 12:56:54 by djanssen         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "../../so_long.h"

void	moveupp(t_map *map)
{
	map->matrix[map->player_y - 1][map->player_x] = 'P';
	map->matrix[map->player_y][map->player_x] = '0';
	map->img[playerright]->instances[0].y -= W;
	map->img[playerleft]->instances[0].y -= W;
	map->img[playerup]->instances[0].y -= W;
	map->img[playerdown]->instances[0].y -= W;
	map->player_y--;
	ft_print_strings(map);
}

void	moveupc(t_map *map)
{
	int	i;

	i = -1;
	map->matrix[map->player_y - 1][map->player_x] = 'P';
	map->matrix[map->player_y][map->player_x] = '0';
	map->player_y--;
	map->img[playerright]->instances[0].y -= W;
	map->img[playerleft]->instances[0].y -= W;
	map->img[playerup]->instances[0].y -= W;
	map->img[playerdown]->instances[0].y -= W;
	map->cc++;
	while (++i < map->elm.c)
	{
		if (map->img[coin0]->instances[i].x == (int32_t)map->player_x * W
			&& (int32_t)map->player_y * H == map->img[coin0]->instances[i].y)
			map->img[coin0]->instances[i].enabled = 0;
	}
	ft_print_strings(map);
}

void	moveupe(t_map *map)
{
	map->matrix[map->player_y - 1][map->player_x] = 'P';
	map->matrix[map->player_y][map->player_x] = '0';
	map->img[playerright]->instances[0].y -= W;
	map->img[playerleft]->instances[0].y -= W;
	map->img[playerup]->instances[0].y -= W;
	map->img[playerdown]->instances[0].y -= W;
	map->player_y++;
	map->game_finished++;
	map->won = 1;
	ft_print_strings(map);
	print_gg(map);
}

void	moveupenemy(t_map *map)
{
	map->matrix[map->player_y][map->player_x] = '0';
	map->img[playerleft]->instances[0].enabled = 0;
	map->img[playerup]->instances[0].enabled = 0;
	map->img[playerdown]->instances[0].enabled = 0;
	map->img[playerright]->instances[0].enabled = 0;
	map->game_finished++;
	map->sharked++;
	print_gg(map);
}

/**
 * @brief This function will make the player move NORTH.
 * 
 * @param map Main struct.
 */
void	moveup(t_map *map)
{
	map->moves++;
	if (map->matrix[map->player_y - 1][map->player_x] == '0')
		moveupp(map);
	else if (map->matrix[map->player_y - 1][map->player_x] == 'C')
		moveupc(map);
	else if (map->matrix[map->player_y - 1][map->player_x] == 'E' &&
				map->cc == map->elm.c)
		moveupe(map);
	else if (map->matrix[map->player_y - 1][map->player_x] == 'V')
		moveupenemy(map);
	else if (map->matrix[map->player_y - 1][map->player_x] == 'E' &&
				map->cc != map->elm.c)
	{
		write(1, "You need to take more coins in order to win.\n", 45);
		map->moves--;
	}
}
