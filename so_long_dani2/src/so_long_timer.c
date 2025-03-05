/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   so_long_timer.c                                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/12/01 16:27:53 by omontero          #+#    #+#             */
/*   Updated: 2023/01/09 15:29:45 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "so_long.h"

void	timer(void *param)
{
	t_map	*map;

	map = param;
	if (map->time && map->anim.frame_fade != 6)
	{
		print_counters(map);
		usleep(10000);
		map->time += 1;
		map->total_frames++;
	}
	if ((map->map_finished || map->game_over) && map->anim.frame_fade < 5
		&& map->time % 5 == 0)
	{
		map->move = 0;
		fade_black(map);
		map->anim.frame_fade++;
	}
	if (map->anim.frame_fade == 5)
		end_string(map);
	if (map->anim.frame_fade == 5)
		map->anim.frame_fade++;
}
//print_map(map);
