def main():
    print("CONCEPTOS BÁSICOS")

    fin = False
    while fin == False:
        ej = int(input("Elije el número del ejercicio (1-10) o 0 para cerrar el programa: "))
        if ej == 0:
            fin = True
        elif ej == 1:
            ejercicio1()
        elif ej == 2:
            ejercicio2()
        elif ej == 3:
            ejercicio3()
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
    print("a")



if __name__ == "__main__":
    main()