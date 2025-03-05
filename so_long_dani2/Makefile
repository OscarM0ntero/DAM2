# **************************************************************************** #
#                                                                              #
#                                                         :::      ::::::::    #
#    Makefile                                           :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: djanssen <djanssen@student.42malaga.com    +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2022/04/07 11:03:12 by djanssen          #+#    #+#              #
#    Updated: 2023/01/16 12:32:49 by djanssen         ###   ########.fr        #
#                                                                              #
# **************************************************************************** #

# Definición de archivos fuente y objetos
SRC = $(wildcard gnl/*.c) $(wildcard src/*.c)
OBJ = $(SRC:.c=.o)

# Variables del compilador
CC = gcc
FLAGS = -Wall -Werror -Wextra
RM = rm -f

# Librerías necesarias
INCLUDE = -lGL -lX11 -lXrandr -lXi -ldl -lpthread -lm
MLX = inc/libmlx42.a
GLFW = inc/libglfw3.a
LIBFT = inc/libft.a

# Colores para la salida en terminal
COLOUR_GREEN=\033[0;32m
COLOUR_RED=\033[0;31m
COLOUR_BLUE=\033[0;34m
COLOUR_END=\033[0m

# Nombre del ejecutable
NAME = so_long

# Compilación del proyecto
all: $(NAME)

$(NAME): $(OBJ)
	@echo "$(COLOUR_BLUE)Compiling...$(COLOUR_END)"
	$(CC) $(FLAGS) $(OBJ) $(LIBFT) $(MLX) $(INCLUDE) -lglfw -o $(NAME)
	@echo "$(COLOUR_GREEN)Success!$(COLOUR_END)"

# Reglas de limpieza
clean:
	@echo "$(COLOUR_BLUE)Cleaning object files...$(COLOUR_END)"
	@$(RM) $(OBJ)
	@echo "$(COLOUR_GREEN)Object files removed!$(COLOUR_END)"

fclean: clean
	@echo "$(COLOUR_BLUE)Cleaning executable...$(COLOUR_END)"
	@$(RM) $(NAME)
	@echo "$(COLOUR_GREEN)Cleaned all!$(COLOUR_END)"

re: fclean all
