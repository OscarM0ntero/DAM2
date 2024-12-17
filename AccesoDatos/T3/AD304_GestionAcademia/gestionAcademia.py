import mysql.connector
from datetime import date

def conectar():
    return mysql.connector.connect(
        host="79.72.63.217",
        port="3306",
        user="user_ad",
        password="1234",
        database="Centro"
    )

def matricular_estudiante(id_estudiante, id_curso, fecha_inicio, fecha_fin):
    conn = conectar()
    cursor = conn.cursor()
    try:
        query = """
        INSERT INTO Matriculas (id_estudiante, id_curso, fecha_inicio, fecha_fin)
        VALUES (%s, %s, %s, %s)
        """
        cursor.execute(query, (id_estudiante, id_curso, fecha_inicio, fecha_fin))
        conn.commit()
        print("Estudiante matriculado con éxito.")
    except mysql.connector.Error as err:
        print("Error:", err)
    finally:
        cursor.close()
        conn.close()

def listar_estudiantes_en_curso(id_curso):
    conn = conectar()
    cursor = conn.cursor()
    try:
        query = """
        SELECT Estudiantes.id, Estudiantes.nombre, Estudiantes.email
        FROM Estudiantes
        INNER JOIN Matriculas ON Estudiantes.id = Matriculas.id_estudiante
        WHERE Matriculas.id_curso = %s
        """
        cursor.execute(query, (id_curso,))
        resultados = cursor.fetchall()
        print("Estudiantes en el curso:")
        for fila in resultados:
            print(fila)
    except mysql.connector.Error as err:
        print("Error:", err)
    finally:
        cursor.close()
        conn.close()

def dar_baja_estudiante(id_estudiante, id_curso):
    conn = conectar()
    cursor = conn.cursor()
    try:
        query = "DELETE FROM Matriculas WHERE id_estudiante = %s AND id_curso = %s"
        cursor.execute(query, (id_estudiante, id_curso))
        conn.commit()
        print("Estudiante dado de baja del curso.")
    except mysql.connector.Error as err:
        print("Error:", err)
    finally:
        cursor.close()
        conn.close()

def actualizar_precio_curso(id_curso, nuevo_precio):
    conn = conectar()
    cursor = conn.cursor()
    try:
        query = "UPDATE Cursos SET precio = %s WHERE id = %s"
        cursor.execute(query, (nuevo_precio, id_curso))
        conn.commit()
        print("Precio del curso actualizado.")
    except mysql.connector.Error as err:
        print("Error:", err)
    finally:
        cursor.close()
        conn.close()

def emitir_recibos():
    conn = conectar()
    cursor = conn.cursor()
    try:
        query = """
        SELECT Estudiantes.nombre, Estudiantes.email, Cursos.nombre AS nombre_curso, Cursos.precio
        FROM Estudiantes
        INNER JOIN Matriculas ON Estudiantes.id = Matriculas.id_estudiante
        INNER JOIN Cursos ON Matriculas.id_curso = Cursos.id
        ORDER BY Estudiantes.id
        """
        cursor.execute(query)
        resultados = cursor.fetchall()
        
        facturas = {}

        for fila in resultados:
            nombre_estudiante, email, nombre_curso, precio = fila
            if nombre_estudiante not in facturas:
                facturas[nombre_estudiante] = {
                    "email": email,
                    "cursos": [],
                    "total": 0
                }
            facturas[nombre_estudiante]["cursos"].append({"nombre": nombre_curso, "precio": precio})
            facturas[nombre_estudiante]["total"] += precio
        
        print("Facturas:")
        for estudiante, detalles in facturas.items():
            print(f"\nFactura para {estudiante} ({detalles['email']}):")
            for curso in detalles["cursos"]:
                print(f"  - Curso: {curso['nombre']} | Precio: €{curso['precio']}")
            print(f"  Total: €{detalles['total']}")
    
    except mysql.connector.Error as err:
        print("Error:", err)
    finally:
        cursor.close()
        conn.close()

def mostrar_menu():
    while True:
        print("\n----- Menú -----")
        print("1. Matricular a un estudiante en un curso")
        print("2. Listado de estudiantes en un curso")
        print("3. Baja de un estudiante en un curso")
        print("4. Actualizar el precio de un curso")
        print("5. Emitir recibos")
        print("6. Salir")
        
        opcion = input("Selecciona una opción (1-6): ")
        
        if opcion == "1":
            try:
                id_estudiante = int(input("Ingresa el ID del estudiante: "))
                id_curso = int(input("Ingresa el ID del curso: "))
                fecha_inicio = input("Ingresa la fecha de inicio (YYYY-MM-DD): ")
                fecha_fin = input("Ingresa la fecha de fin (YYYY-MM-DD): ")
                matricular_estudiante(id_estudiante, id_curso, fecha_inicio, fecha_fin)
            except ValueError:
                print("Error: Debes ingresar un número válido.")
        elif opcion == "2":
            try:
                id_curso = int(input("Ingresa el ID del curso: "))
                listar_estudiantes_en_curso(id_curso)
            except ValueError:
                print("Error: Debes ingresar un número válido.")
        elif opcion == "3":
            try:
                id_estudiante = int(input("Ingresa el ID del estudiante: "))
                id_curso = int(input("Ingresa el ID del curso: "))
                dar_baja_estudiante(id_estudiante, id_curso)
            except ValueError:
                print("Error: Debes ingresar un número válido.")
        elif opcion == "4":
            try:
                id_curso = int(input("Ingresa el ID del curso: "))
                nuevo_precio = float(input("Ingresa el nuevo precio: "))
                actualizar_precio_curso(id_curso, nuevo_precio)
            except ValueError:
                print("Error: Debes ingresar un número válido.")
        elif opcion == "5":
            emitir_recibos()
        elif opcion == "6":
            print("¡Hasta luego!")
            break
        else:
            print("Opción no válida, intenta de nuevo.")

if __name__ == "__main__":
    mostrar_menu()
