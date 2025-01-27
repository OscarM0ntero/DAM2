import socket

def cliente_registro():
    HOST = "79.72.63.217"
    PUERTO = 5000

    try:
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as cliente:
            cliente.connect((HOST, PUERTO))

            # Introducir el nombre
            nombre = input("¿Quién eres? > ").strip()

            # Enviar el mensaje al servidor
            cliente.sendall((nombre + "\n").encode())

            # Recibir la respuesta
            respuesta = cliente.recv(1024).decode().strip()
            print("Servidor:", respuesta)

    except ConnectionRefusedError:
        print("No se pudo conectar al servidor. Asegúrate de que está en ejecución.")
    except Exception as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    cliente_registro()
