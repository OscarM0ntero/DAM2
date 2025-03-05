/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   so_long_utils.c                                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/12/01 16:27:53 by omontero          #+#    #+#             */
/*   Updated: 2023/01/09 12:49:25 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "so_long.h"

char	*ft_strnjoin(char *s1, char *s2, int n)
{
	size_t	i;
	size_t	j;
	char	*str;
	int		k;

	k = 0;
	j = ft_strlen(s1) + ft_strlen(s2) - (ft_strlen(s2) - n);
	str = (char *)malloc(sizeof(char) * (j + 1));
	if (!str)
		return (NULL);
	i = -1;
	while (++i < ft_strlen(s1))
		str[i] = s1[i];
	while (i <= j && s2[k])
	{
		str[i++] = s2[k++];
	}
	str[i] = '\0';
	return (str);
}

void	print_map(t_map *map)
{
	size_t	i;
	size_t	j;

	system("clear");
	i = -1;
	write(1, "\n\n\n\n", 4);
	while (++i < map->n_lines)
	{
		j = -1;
		while (++j < map->n_chars)
			write(1, &map->str[i][j], 1);
		write(1, "\n", 1);
	}
	printf("\n\nfinish=%i\nmove=%i\nFrames=%ld\n",
		map->map_finished, map->move, map->total_frames);
}

size_t	count_lines(char *p)
{
	int		fd;
	char	*line;
	int		count;

	fd = open(p, O_RDONLY);
	line = get_next_line(fd);
	count = 0;
	if (line != NULL)
	{
		while (line != NULL)
		{
			count++;
			free (line);
			line = get_next_line(fd);
		}
	}
	free (line);
	close(fd);
	return (count);
}

int	all_ones(char *line)
{
	while (*line && *line == '1')
		line++;
	if (*line)
		return (1);
	return (0);
}

void	check_line(char *line, size_t n_lin, t_map *new_map)
{
	int	i;

	i = 0;
	if (ft_strlen(line) != new_map->n_chars && !new_map->error)
		new_map->error = 2;
	while (line[i] && !new_map->error)
	{
		if (!ft_strchr("01PECV", line[i]))
			new_map->error = 3;
			
		i++;
	}
	if (n_lin == 0 && all_ones(line) && !new_map->error)
		new_map->error = 4;
	else if (n_lin == new_map->n_lines - 1 && all_ones(line) && !new_map->error)
		new_map->error = 4;
	else if ((line[0] != '1' || line[new_map->n_chars - 1] != '1')
		&& !new_map->error)
		new_map->error = 4;
}
