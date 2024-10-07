import re

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
    val = input("Dame un valor y te diré el tipo: ")
    print(val, "es de tipo", type(val).__name__)

def ejercicio2():
    nums = input("Dame dos numeros separados por una coma (x,y): ").split(",")
    n1 = int(nums[0])
    n2 = int(nums[1])

    print("Suma\n" + str(n1), "+", str(n2), "=", (n1+n2))
    print("Resta\n" + str(n1), "-", str(n2), "=", (n1-n2))

def ejercicio3():
    nom = input("Dame un nombre de usuario: ")
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