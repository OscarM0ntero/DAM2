/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   get_next_line.c                                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: omontero <omontero@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/05/18 16:53:32 by omontero          #+#    #+#             */
/*   Updated: 2022/12/20 11:27:16 by omontero         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "get_next_line.h"

size_t	ft_strlen(const char *s)
{
	size_t	i;

	i = 0;
	while (*s)
	{
		i++;
		s++;
	}
	return (i);
}

static char	*ft_save_buffers(int fd, char *s)
{
	char	*buff;
	int		read_output;
	char	*c;

	buff = (char *)ft_calloc((BUFFER_SIZE + 1), sizeof(char));
	if (!buff)
		return (NULL);
	read_output = 1;
	while (!ft_strchr(buff, '\n') && read_output != 0)
	{
		read_output = read(fd, buff, BUFFER_SIZE);
		if (!read_output)
			continue ;
		if (read_output == -1)
		{
			free (buff);
			return (NULL);
		}
		buff[read_output] = 0;
		c = s;
		s = ft_strjoin(s, buff);
		free (c);
	}
	free (buff);
	return (s);
}

static char	*ft_set_next(char *s)
{
	size_t	i;
	char	*c;

	if (!*s)
		return (NULL);
	i = 0;
	while (s[i] != '\n' && s[i])
		i++;
	if (s[i] == '\n' && s[i + 1] != 0)
	{
		c = s;
		s = ft_strdup(&s[i + 1]);
		free (c);
	}
	else
	{
		free (s);
		s = NULL;
		return (s);
	}
	s[ft_strlen(s)] = 0;
	return (s);
}

/*	ORIGINAL IS:
if (s[i] == '\n')
	{
		line[i] = s[i];
		i++;
	}

	NEW ONE WON'T SAVE \n !!!
*/

static char	*ft_get_line(char *s)
{
	char	*line;
	size_t	i;

	if (!*s)
		return (NULL);
	i = 0;
	while (s[i] != '\n' && s[i])
		i++;
	line = ft_calloc(i + 2, sizeof(char));
	i = 0;
	while (s[i] != '\n' && s[i])
	{
		line[i] = s[i];
		i++;
	}
	if (s[i] == '\n')
	{
		line[i] = 0;
		i++;
	}
	line[i] = 0;
	return (line);
}

char	*get_next_line(int fd)
{
	static char	*s;
	char		*line;

	if (fd < 0 || BUFFER_SIZE <= 0)
		return (NULL);
	s = ft_save_buffers(fd, s);
	if (!s)
		return (NULL);
	line = ft_get_line(s);
	if (!line)
	{
		free (s);
		return (NULL);
	}
	s = ft_set_next(s);
	if (s == NULL && line == NULL)
		return (NULL);
	return (line);
}

/* int	main(void)
{
	int		fd;
	int		i;
	char	*c;

	i = 0;
	fd = open("lines_around_10.txt", O_RDONLY);
	while (i < 10)
	{
		c = get_next_line(fd);
		printf(">%s<\n", c);
		free (c);
		i++;
	}
	close(fd);
} */