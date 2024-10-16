def es_palindromo_de(texto1, texto2, ignorar_caso=True):
    if ignorar_caso:
        texto1 = texto1.lower()
        texto2 = texto2.lower()
    
    texto1 = texto1.replace(" ", "")
    texto2 = texto2.replace(" ", "")
    
    return texto1 == texto2[::-1]

def main():
    texto1 = input("Introduce el primer texto: ")
    texto2 = input("Introduce el segundo texto: ")
    
    ignorar_caso = input("¿Deseas ignorar mayúsculas y minúsculas? (s/n): ").lower() == 's'
    
    if es_palindromo_de(texto1, texto2, ignorar_caso):
        print("Los textos son palíndromos uno del otro.")
    else:
        print("Los textos no son palíndromos uno del otro.")

main()
