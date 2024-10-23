import random

def mostrar_menu():
    print("\nMENÚ DE OPCIONES")
    print("a) Visualizar valor de un dado")
    print("b) Mensaje cuando el número introducido no sea mayor que el primero")
    print("c) Ingrese dos años para ver los años en ese rango que sean bisiestos y múltiplos de 10")
    print("d) Mostrar rectángulo con números impares entre 0 y 100")
    print("e) Salir")

def opcion_a():
    # Genera un número aleatorio entre 1 y 6 para simular el lanzamiento de un dado
    valor_dado = random.randint(1, 6)
    print(f"\nEl valor del dado es: {valor_dado}")

def opcion_b():
    try:
        cantidad = int(input("\n¿Cuántos números va a introducir? "))
        if()
        if cantidad <= 0:
            print("Debe introducir al menos un número.")
            return

        numeros = []
        for i in range(cantidad):
            num = int(input(f"Introduce el número {i + 1}: "))
            numeros.append(num)

            if i > 0 and num <= numeros[i - 1]:
                print("El número introducido no es mayor que el anterior.")

    except ValueError:
        print("Por favor, introduzca un número válido.")

def es_bisiesto(anio):
    # Verifica si un año es bisiesto
    return (anio % 4 == 0 and anio % 100 != 0) or (anio % 400 == 0)

def opcion_c():
    try:
        anio_inicio = int(input("\nIngrese el año de inicio: "))
        anio_fin = int(input("Ingrese el año de fin: "))

        if anio_inicio > anio_fin:
            print("El año de inicio debe ser menor o igual que el año de fin.")
            return

        print("\nAños bisiestos y múltiplos de 10 en el rango dado:")
        for anio in range(anio_inicio, anio_fin + 1):
            if es_bisiesto(anio) and anio % 10 == 0:
                print(anio, end=" ")
        print()

    except ValueError:
        print("Por favor, introduzca un año válido.")

def opcion_d():
    try:
        filas = int(input("\nIngrese el número de filas: "))
        columnas = int(input("Ingrese el número de columnas: "))

        if filas <= 0 or columnas <= 0:
            print("El número de filas y columnas debe ser mayor que cero.")
            return

        # Generar números impares entre 0 y 100
        numeros_impares = [num for num in range(1, 100, 2)]
        indice = 0

        # Mostrar los números impares en formato rectangular
        print("\nRectángulo de números impares:")
        for i in range(filas):
            for j in range(columnas):
                if indice < len(numeros_impares):
                    print(f"{numeros_impares[indice]:2}", end=" ")
                    indice += 1
            print()

    except ValueError:
        print("Por favor, introduzca un número válido.")

def main():
    while True:
        mostrar_menu()
        opcion = input("\nSeleccione una opción (a, b, c, d, e): ").strip().lower()

        if opcion == 'a':
            opcion_a()
        elif opcion == 'b':
            opcion_b()
        elif opcion == 'c':
            opcion_c()
        elif opcion == 'd':
            opcion_d()
        elif opcion == 'e':
            print("\nSaliendo del programa. ¡Hasta luego!")
            break
        else:
            print("\nOpción no válida. Por favor, seleccione una opción correcta.")

        input("\nPresione Enter para continuar...")

if __name__ == "__main__":
    main()
