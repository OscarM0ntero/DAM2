import socket
import sys


def verificar_puerto(host, puerto):
    """Comprueba si un puerto está abierto."""
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.settimeout(3)  # 3 segundos de espera
        try:
            s.connect((host, puerto))
            return True
        except (socket.timeout, ConnectionRefusedError):
            return False


def cliente_saludo():
    host = "damplaya.hopto.org"
    puerto = 5000

    if not verificar_puerto(host, puerto):
        print(f"El puerto {puerto} en {host} no está disponible.")
        sys.exit(1)

    try:
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as cliente:
            cliente.connect((host, puerto))
            print(f"Conectado a {host}:{puerto}")

            # Recibir la pregunta del servidor
            mensaje_servidor = cliente.recv(1024).decode("utf-8")
            print(f"Servidor: {mensaje_servidor}")

            # Enviar el nombre al servidor
            nombre = input("Introduce tu nombre: ")
            cliente.sendall(nombre.encode("utf-8"))

            # Recibir el saludo personalizado
            respuesta_servidor = cliente.recv(1024).decode("utf-8")
            print(f"Servidor: {respuesta_servidor}")

    except Exception as e:
        print(f"Error: {e}")
        sys.exit(1)


if __name__ == "__main__":
    cliente_saludo()
