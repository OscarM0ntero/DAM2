# **************************************************************************** #
#                                                                              #
#                                                         :::      ::::::::    #
#    Makefile                                           :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: omontero <omontero@student.42malaga.com    +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2022/04/07 11:03:12 by omontero          #+#    #+#              #
#    Updated: 2023/01/09 15:19:47 by omontero         ###   ########.fr        #
#                                                                              #
# **************************************************************************** #

SRC = src/*.c get_next_line/*.c

OBJ = $(SRC:.c=.o)

RM = rm -f

CC = gcc

FLAGS = -Wall -Werror -Wextra

INCLUDE = -Iinclude -ldl -lglfw -pthread -lm

MLX = inc/libmlx42.a

GLFW = inc/libglfw3.a

LIBFT = inc/libft.a

all:			$(NAME)
				$(CC) $(FLAGS) $(SRC) $(NAME) $(LIBFT) $(GLFW) $(MLX) $(INCLUDE) -o so_long

$(NAME):		$(OBJ)
				cd MLX42 && make all
				$(CC) $(FLAGS) -c $(SRC)
				ar rcs $(NAME) $(OBJ)

clean:
				$(RM) $(OBJS)

fclean:			clean
				$(RM) $(NAME) so_long

.PHONY: all clean fclean re
