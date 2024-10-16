def contarVocales(str):
    vocales = "aeiouAEIOUáéíóúÁÉÍÓÚü"
    
    return sum(str.count(vocal) for vocal in vocales)


# Función principal
def main():

    frase1 = "Hola me llamo Oscar"
    print(frase1)
    print(contarVocales(frase1))

    frase2= "Hay agua en el rio"
    print(frase2)
    print(contarVocales(frase2))

    frase3 = "Aquí!"
    print(frase3)
    print(contarVocales(frase3))

# Llamada a la función principal
main()
