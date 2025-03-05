/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   movedown.c                                         :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: djanssen <djanssen@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/04/07 11:03:12 by djanssen          #+#    #+#             */
/*   Updated: 2023/01/10 12:56:49 by djanssen         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "../../so_long.h"

void	movedownp(t_map *map)
{
	map->matrix[map->player_y + 1][map->player_x] = 'P';
	map->matrix[map->player_y][map->player_x] = '0';
	map->img[playerright]->instances[0].y += W;
	map->img[playerleft]->instances[0].y += W;
	map->img[playerup]->instances[0].y += W;
	map->img[playerdown]->instances[0].y += W;
	map->player_y++;
	ft_print_strings(map);
}

void	movedownc(t_map *map)
{
	int	i;

	i = -1;
	map->matrix[map->player_y + 1][map->player_x] = 'P';
	map->matrix[map->player_y][map->player_x] = '0';
	map->img[playerright]->instances[0].y += W;
	map->img[playerleft]->instances[0].y += W;
	map->img[playerup]->instances[0].y += W;
	map->img[playerdown]->instances[0].y += W;
	map->player_y++;
	map->cc++;
	while (++i < map->elm.c)
	{
		if (map->img[coin0]->instances[i].x == (int32_t)map->player_x * W
			&& (int32_t)map->player_y * H == map->img[coin0]->instances[i].y)
			map->img[coin0]->instances[i].enabled = 0;
	}
	ft_print_strings(map);
}

void	movedowne(t_map *map)
{
	map->matrix[map->player_y + 1][map->player_x] = 'P';
	map->matrix[map->player_y][map->player_x] = '0';
	map->img[playerright]->instances[0].y += W;
	map->img[playerleft]->instances[0].y += W;
	map->img[playerup]->instances[0].y += W;
	map->img[playerdown]->instances[0].y += W;
	map->player_y++;
	map->game_finished++;
	map->won = 1;
	print_gg(map);
	ft_print_strings(map);
}

void	movedownenemy(t_map *map)
{
	map->matrix[map->player_y][map->player_x] = '0';
	map->img[playerleft]->instances[0].enabled = 0;
	map->img[playerup]->instances[0].enabled = 0;
	map->img[playerdown]->instances[0].enabled = 0;
	map->img[playerright]->instances[0].enabled = 0;
	map->game_finished++;
	map->sharked = 1;
	print_gg(map);
}

void	movedown(t_map *map)
{
	map->moves++;
	if (map->matrix[map->player_y + 1][map->player_x] == '0')
		movedownp(map);
	else if (map->matrix[map->player_y + 1][map->player_x] == 'C')
		movedownc(map);
	else if (map->matrix[map->player_y + 1][map->player_x] == 'E' &&
				map->cc == map->elm.c)
		movedowne(map);
	else if (map->matrix[map->player_y + 1][map->player_x] == 'V')
		movedownenemy(map);
	else if (map->matrix[map->player_y + 1][map->player_x] == 'E' &&
				map->cc != map->elm.c)
	{
		write(1, "You need to take more coins in order to win.\n", 45);
		map->moves--;
	}
}
