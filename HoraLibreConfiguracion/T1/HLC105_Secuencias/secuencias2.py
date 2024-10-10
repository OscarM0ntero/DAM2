def leer_textos():
    textos = []
    while True:
        texto = input("Introduce un texto (presiona Enter para finalizar): ")
        if texto == "":
            break
        textos.append(texto)
    
    return textos

def main():
    textos = leer_textos()

    print("a. En el orden introducido:")
    print(textos)

    print("b. En orden alfabético:")
    print(sorted(textos))

    print("c. En orden alfabético inverso:")
    print(sorted(textos, reverse=True))

main()
