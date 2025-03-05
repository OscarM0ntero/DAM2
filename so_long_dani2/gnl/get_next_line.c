/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   get_next_line.c                                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: djanssen <djanssen@student.42malaga.com    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/09/20 16:18:41 by djanssen          #+#    #+#             */
/*   Updated: 2022/12/20 11:54:56 by djanssen         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "get_next_line.h"

char	*get_linex(char *str)
{
	char	*linea;
	int		i;

	if (!str)
		return (NULL);
	i = 0;
	while (str[i] != '\n' && str[i])
		i++;
	if (str[i])
		i++;
	linea = malloc((i + 1) * sizeof(char));
	if (!linea)
		return (NULL);
	i = -1;
	while (str[++i] != '\n' && str[i])
		linea[i] = str[i];
	if (str[i] == '\n')
		linea[i++] = '\0';
	linea[i] = '\0';
	return (linea);
}

char	*get_next(char *str)
{
	char	*c;
	int		i;

	if (!str)
		return (NULL);
	i = 0;
	while (str[i] && str[i] != '\n')
		i++;
	if (!str[i] || !str[i + 1])
		return (free(str), NULL);
	if (str[i])
		i++;
	c = ft_strdup(&str[i]);
	return (free(str), c);
}

/**
 * @brief It will receive the file descriptor and the static string.
 * It will allocate the buffer size + 1 for the \0 at the end of the string.
 * Then it will use strchr to find a '\n' and read_val:
 * read_val = 0 -> end of file -> leave from the loop.
 * read_val = -1 -> error reading the file -> clear everything and return null.
 * 
 * 
 * @param fd file descriptor
 * @param s static char used in main function
 * @return 
 */
char	*readjoin(int fd, char *s)
{
	int		read_val;
	char	*buffer;

	buffer = malloc(sizeof(char) * BUFFER_SIZE + 1);
	if (!buffer)
		return (NULL);
	read_val = 1;
	while ((ft_strchr(s, '\n') == NULL) && read_val)
	{
		read_val = read(fd, buffer, BUFFER_SIZE);
		if (read_val == 0)
			break ;
		if (read_val == -1)
			return (free(buffer), free(s), NULL);
		buffer[read_val] = '\0';
		s = ft_strjoin(s, buffer);
	}
	return (free(buffer), s);
}

char	*get_next_line(int fd)
{
	char		*line;
	static char	*s = NULL;

	if (fd < 0 || BUFFER_SIZE <= 0)
		return (NULL);
	s = readjoin(fd, s);
	line = get_linex(s);
	s = get_next(s);
	return (line);
}

// int	main(void)
// {
// 	int		fd;
// 	int		i;
// 	char	*c;
// 	ssize_t	read_val;

// 	i = 0;
// 	read_val = 0;
// 	fd = open("poya.txt", O_RDONLY);
// 	read_val = read(fd, c, BUFFER_SIZE);
// 	printf("%zd", read_val);
// 	while (i < 1000000)
// 	{
// 		c = get_next_line(fd);
// 		printf(">%s", c);
// 		free(c);
// 		i++;
// 	}
// 	close(fd);
// }
