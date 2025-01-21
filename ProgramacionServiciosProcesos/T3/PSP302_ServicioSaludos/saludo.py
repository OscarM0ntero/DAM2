import socket

def cliente_saludo():
    HOST = "127.0.0.1"
    PUERTO = 5000

    try:
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as cliente:
            cliente.connect((HOST, PUERTO))

            # Recibir la pregunta del servidor
            pregunta = cliente.recv(1024).decode().strip()
            print("Servidor:", pregunta)

            # Enviar el nombre
            nombre = input("Introduce tu nombre: ")

            cliente.sendall((nombre + "\n").encode())

            # Recibir el saludo
            saludo = cliente.recv(1024).decode().strip()
            print("Servidor:", saludo)

    except ConnectionRefusedError:
        print("No se pudo conectar al servidor. Asegúrate de que está en ejecución.")
    except Exception as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    cliente_saludo()
