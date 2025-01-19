import socket

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

server_address = ("127.0.0.1", 2017)

server_socket.bind(server_address)

server_socket.listen(5)

print(f"Servidor escuchando en {server_address}")

while True:
    client_socket, client_address = server_socket.accept()
    print(f"Conexión aceptada de {client_address}")
    data = client_socket.recv(1024).decode("utf-8")
    print(f"Datos recibidos: {data}")
    response = "Mensaje recibido"
    client_socket.sendall(response.encode("utf-8"))
    client_socket.close()
