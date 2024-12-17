import socket;
import os;

def main():
    os.system("cls") if os.name=="nt" else os.system("clear");
    
    #!Crear el socket
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM);

    #!Almacenamos una dirección y un puerto.
    server_address = ('localhost' ,5000);

    #!Nos conectamos a la dirección y puerto a el socket anterior mente creado.
    server_socket.connect(server_address)
    
    #!Recibimos la respuesta del servidor.
    pregunta = server_socket.recv(1024).decode('utf-8'); 
    print(pregunta);
    
    print("hola");
    #!Mansamos el nombre desde el socket cliente. server_socket. send(input("*) . encode('utf-8')); #Imprimimos la respuesta.
    respuesta = server_socket.recv(1024). decode('utf-8');
    print(respuesta);

if __name__ ==" _main_":
    main();