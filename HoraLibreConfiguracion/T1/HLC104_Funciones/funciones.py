import random
import math

def mostrar_menu():
    print("\nMENÚ DE OPCIONES")
    print("a) Mostrar un rombo.")
    print("b) Adivinar un número.")
    print("c) Resolver una ecuación de segundo grado.")
    print("d) Tabla de números.")
    print("e) Cálculo del número factorial de un número.")
    print("f) Cálculo de un número de la sucesión de Fibonacci.")
    print("g) Tabla de multiplicar.")
    print("h) Salir")

def mostrar_rombo():
    while True:
        try:
            n = int(input("Introduce un número impar para el tamaño del rombo: "))
            if n % 2 == 0:
                print("El número debe ser impar. Inténtalo de nuevo.")
            else:
                break
        except ValueError:
            print("Por favor, introduce un número válido.")
    
    for i in range(n//2 + 1):
        print(" " * (n//2 - i) + "*" * (2 * i + 1))
    for i in range(n//2 - 1, -1, -1):
        print(" " * (n//2 - i) + "*" * (2 * i + 1))

def adivinar_numero():
    numero_a_adivinar = random.randint(1, 100)
    while True:
        try:
            intento = int(input("Adivina el número entre 1 y 100: "))
            if intento < numero_a_adivinar:
                print("El número es mayor.")
            elif intento > numero_a_adivinar:
                print("El número es menor.")
            else:
                print("¡Correcto! Has adivinado el número.")
                break
        except ValueError:
            print("Introduce un número válido.")

def resolver_ecuacion_segundo_grado():
    try:
        a = float(input("Introduce el coeficiente a: "))
        b = float(input("Introduce el coeficiente b: "))
        c = float(input("Introduce el coeficiente c: "))
        
        discriminante = b**2 - 4*a*c
        
        if discriminante > 0:
            x1 = (-b + math.sqrt(discriminante)) / (2*a)
            x2 = (-b - math.sqrt(discriminante)) / (2*a)
            print(f"Las soluciones son x1 = {x1} y x2 = {x2}")
        elif discriminante == 0:
            x = -b / (2*a)
            print(f"Hay una solución doble: x = {x}")
        else:
            print("La ecuación no tiene solución real.")
    except ValueError:
        print("Entrada inválida. Introduce números válidos.")

def tabla_numeros():
    try:
        filas = int(input("Introduce el número de filas: "))
        columnas = int(input("Introduce el número de columnas: "))
        
        for i in range(filas):
            fila = [str(random.randint(1, 100)) for _ in range(columnas)]
            print(" ".join(fila))
    except ValueError:
        print("Por favor, introduce valores numéricos válidos.")

def calcular_factorial():
    def factorial(n):
        if n == 0 or n == 1:
            return 1
        else:
            return n * factorial(n - 1)
    
    try:
        n = int(input("Introduce un número para calcular su factorial: "))
        print(f"El factorial de {n} es {factorial(n)}")
    except ValueError:
        print("Por favor, introduce un número válido.")

def calcular_fibonacci():
    def fibonacci(n):
        if n == 1 or n == 2:
            return 1
        else:
            return fibonacci(n - 1) + fibonacci(n - 2)
    
    try:
        n = int(input("Introduce la posición en la sucesión de Fibonacci: "))
        print(f"El número en la posición {n} es {fibonacci(n)}")
    except ValueError:
        print("Por favor, introduce un número válido.")

def tabla_multiplicar():
    try:
        n = int(input("Introduce un número para ver su tabla de multiplicar: "))
        for i in range(1, 11):
            print(f"{n} x {i} = {n * i}")
    except ValueError:
        print("Por favor, introduce un número válido.")

def main():
    while True:
        mostrar_menu()
        opcion = input("Introduce una opción: ").lower()

        if opcion == 'a':
            mostrar_rombo()
        elif opcion == 'b':
            adivinar_numero()
        elif opcion == 'c':
            resolver_ecuacion_segundo_grado()
        elif opcion == 'd':
            tabla_numeros()
        elif opcion == 'e':
            calcular_factorial()
        elif opcion == 'f':
            calcular_fibonacci()
        elif opcion == 'g':
            tabla_multiplicar()
        elif opcion == 'h':
            print("Saliendo del programa.")
            break
        else:
            print("Opción no válida. Inténtalo de nuevo.")

        input("\nPresiona enter para continuar...")

if __name__ == "__main__":
    main()
