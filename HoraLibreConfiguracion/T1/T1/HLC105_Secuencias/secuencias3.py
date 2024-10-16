def es_palindromo(s):
    return s == s[::-1]

# Función principal
def main():
    texto = input("Introduce un texto para verificar si es palíndromo: ")
    if es_palindromo(texto):
        print("El texto es un palíndromo.")
    else:
        print("El texto no es un palíndromo.")

main()
