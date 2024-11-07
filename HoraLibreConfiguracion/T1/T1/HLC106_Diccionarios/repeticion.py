import re
import math
import array

def main():
    print("CONCEPTOS BÁSICOS")

    fin = False
    while fin == False:
        ej = int(input("\nElije el número del ejercicio (1-10) o 0 para cerrar el programa: "))
        if ej == 0:
            fin = True
        elif ej == 1:
            ejercicio1()
        elif ej == 2:
            ejercicio2()
        elif ej == 3:
            ejercicio3()
        elif ej == 4:
            ejercicio4()
        elif ej == 5:
            ejercicio5()
        elif ej == 6:
            ejercicio6()
        else:
            print("Opción erronea.")
    print("Fin del programa.")
        
def ejercicio1():
    for letra in (input("Dame un texto: ")):
        print (letra)

def ejercicio2():
    num = input("Dame un número: ")
    print("Su factorial es " + str(math.factorial(int(num))))

def ejercicio3():
    array = []
    num = 1
    while num != 0:
        num = input("Dame un numero (o para salir): ")
        array.append(num)
    print("Hola " + nom + ".")

def ejercicio4():
    nums = input("Dame tres números separados con coma (x,y,z): ")
    # Aquí lo que hago es usar regex para comprobar el formato del input
    if re.compile("^[0-9]+,[0-9]+,[0-9]+$").match(nums):
        media = (int(nums.split(",")[0]) + int(nums.split(",")[1]) + int(nums.split(",")[2])) / 3
        print("Media aritmética: " + str(media))
    else:
        print("Formato incorrecto.")

def ejercicio5():
    num = input("Dame un número: ")
    print("Su valor absoluto es " + str(abs(float(num))))

def ejercicio6():
    nums = input("Dame las tres notas separadas con coma (x,y,z): ")
    # Aquí lo que hago es usar regex para comprobar el formato del input
    if re.compile("^[0-9]+,[0-9]+,[0-9]+$").match(nums):
        media = (int(nums.split(",")[0]) * 0.2) + (int(nums.split(",")[1]) * 0.35) + (int(nums.split(",")[2]) * 0.45)
        print("Nota final: " + str(media))
    else:
        print("Formato incorrecto.")

if __name__ == "__main__":
    main()