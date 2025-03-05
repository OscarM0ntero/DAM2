/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   moveleft.c                                         :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: djanssen <djanssen@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/04/07 11:03:12 by djanssen          #+#    #+#             */
/*   Updated: 2023/01/10 12:56:43 by djanssen         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "../../so_long.h"

void	moveleftp(t_map *map)
{
	map->matrix[map->player_y][map->player_x + 1] = 'P';
	map->matrix[map->player_y][map->player_x] = '0';
	map->img[playerright]->instances[0].x += W;
	map->img[playerleft]->instances[0].x += W;
	map->img[playerup]->instances[0].x += W;
	map->img[playerdown]->instances[0].x += W;
	map->player_x++;
	ft_print_strings(map);
}

void	moveleftc(t_map *map)
{
	int	i;

	i = -1;
	map->matrix[map->player_y][map->player_x + 1] = 'P';
	map->matrix[map->player_y][map->player_x] = '0';
	map->img[playerright]->instances[0].x += W;
	map->img[playerleft]->instances[0].x += W;
	map->img[playerup]->instances[0].x += W;
	map->img[playerdown]->instances[0].x += W;
	map->player_x++;
	map->cc++;
	while (++i < map->elm.c)
	{
		if (map->img[coin0]->instances[i].x == (int32_t)map->player_x * W
			&& (int32_t)map->player_y * H == map->img[coin0]->instances[i].y)
			map->img[coin0]->instances[i].enabled = 0;
	}
	ft_print_strings(map);
}

void	movelefte(t_map *map)
{
	map->matrix[map->player_y][map->player_x + 1] = 'P';
	map->matrix[map->player_y][map->player_x] = '0';
	map->img[playerright]->instances[0].x += W;
	map->img[playerleft]->instances[0].x += W;
	map->img[playerup]->instances[0].x += W;
	map->img[playerdown]->instances[0].x += W;
	map->player_x++;
	map->game_finished++;
	map->won = 1;
	print_gg(map);
	ft_print_strings(map);
}

void	moveleftenemy(t_map *map)
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

/**
 * @brief This function will make the player move WEST.
 * 
 * @param map Main struct.
 */
void	moveleft(t_map *map)
{
	map->moves++;
	if (map->matrix[map->player_y][map->player_x + 1] == '0')
		moveleftp(map);
	else if (map->matrix[map->player_y][map->player_x + 1] == 'C')
		moveleftc(map);
	else if (map->matrix[map->player_y][map->player_x + 1] == 'E' &&
				map->cc == map->elm.c)
		movelefte(map);
	else if (map->matrix[map->player_y][map->player_x + 1] == 'V')
		moveleftenemy(map);
	else if (map->matrix[map->player_y][map->player_x + 1] == 'E' &&
				map->cc != map->elm.c)
	{
		write(1, "You need to take more coins in order to win.\n", 45);
		map->moves--;
	}
}
