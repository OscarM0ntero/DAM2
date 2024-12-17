import mysql.connector
from mysql.connector import Error

connection = None  # Inicializar la variable antes del bloque try

try:
    connection = mysql.connector.connect(
        host='79.72.63.217',  # Reemplaza con la IP pública de tu instancia
        user='user_ad',  # El usuario que creaste
        password='1234',  # La contraseña
        database='dolibarr_db'  # La base de datos a la que quieres conectarte
    )
    if connection.is_connected():
        print("Conexión exitosa a la base de datos")
except Error as e:
    print(f"Error al conectar a MySQL: {e}")
finally:
    # Verificar si connection no es None antes de intentar cerrar
    if connection and connection.is_connected():
        connection.close()
        print("Conexión cerrada")
