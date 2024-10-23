

def menu():
    print("\nMENÚ DE OPCIONES")
    print("\ta)Reemplazar vocales de una frase")
    print("\tb) Mensaje cuando el numero introducido no sea mayor que el primero ")
    print("\tc) Encontrar la primera palabra más larga")
    print("\td) Mostrar rectángulo con números impares entre 0 y 100")
    print("\te) Contar la aparición de cada carácter en una palabra")
    print("\tf) Salir")

        
def ejercicio_a():
    vocales = "aeiouAEIOU"
    str = input("Introduce el texto: ")
    for vocal in vocales:
        str = str.replace(vocal, "*", -1)
    print("Resultado: " + str)

def ejercicio_b():
    try:
          cantNum = int(input("¿Cuantos numeros se van a introducir?: "))
          num = 0
          for i in range(cantNum):
               num1 = num
               num = int(input("Introduce numero: "))
               if(num < num1 and i > 0):
                    print("Numero menor que el anterior.")
    except ValueError:
        print("Por favor, introduzca un número válido.")

def ejercicio_c():
    str = input("Dime una frase: ")
    palabras = str.split(" ")
    palabraLarga = ""
    for p in palabras:
        if(len(p) > len(palabraLarga)):
            palabraLarga = p
    print("La palabra mas larga es: " + palabraLarga)

def ejercicio_d():
    try:
        filas = int(input("\nNumero de filas: "))
        columnas = int(input("Numero de columnas: "))

        if filas <= 0 or columnas <= 0:
            print("El numero de filas y columnas debe ser mayor a cero.")
            return

        numeros_impares = [num for num in range(1, 100, 2)]
        indice = 0

        for i in range(filas):
            for j in range(columnas):
                if indice < len(numeros_impares):
                    print(f"{numeros_impares[indice]:2}", end=" ")
                    indice += 1
            print()

    except ValueError:
        print("Por favor, introduzca un número válido.")

def ejercicio_e():
    string = input("Dime una palabra: ")
    letrasContadas = ""
    for letra in string:
        if letrasContadas.find(letra) == -1:
           print(letra + ": " + str(sum(string.count(l) for l in letra)))
           letrasContadas = letrasContadas+letra

def main():
    while True:
        menu()
        opcion = input("\nSeleccione una opción: ").strip().lower()
        if opcion == 'a':
            ejercicio_a()
        elif opcion == 'b':
            ejercicio_b()
        elif opcion == 'c':
            ejercicio_c()
        elif opcion == 'd':
            ejercicio_d()
        elif opcion == 'e':
            ejercicio_e()
        elif opcion == 'f':
            print("\nSaliendo del programa. ¡Hasta luego!")
            break
        else:
            print("\nOpción no válida.")

if __name__ == "__main__":
    main()