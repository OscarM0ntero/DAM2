def main():
    print("CALCULAR PROMEDIO")
    numeros = []
    
    while True:
        entrada = input("Ingrese un número (o 'fin' para terminar): ")
        
        if entrada.lower() == 'fin':
            break
        
        try:
            numero = float(entrada)
            numeros.append(numero)
        except ValueError:
            print("Por favor, ingrese un número válido.")
    
    if numeros:
        promedio = sum(numeros) / len(numeros)
        print(f"El promedio es: {promedio:.2f}")
    else:
        print("No se ingresaron números válidos.")

if __name__ == "__main__":
    main()
