def leer_numeros():
    numeros = []
    while True:
        try:
            numero = int(input("Introduce un número (0 para finalizar): "))
            if numero == 0:
                break
            numeros.append(numero)
        except ValueError:
            print("Por favor, introduce un número válido.")
    
    return numeros

# Función principal
def main():
    numeros = leer_numeros()

    # a. Mostrar en el orden que se introdujeron
    print("a. En el orden introducido:")
    print(numeros)

    # b. Mostrar en orden creciente
    print("b. En orden creciente:")
    print(sorted(numeros))

    # c. Mostrar en orden decreciente
    print("c. En orden decreciente:")
    print(sorted(numeros, reverse=True))

# Llamada a la función principal
main()
