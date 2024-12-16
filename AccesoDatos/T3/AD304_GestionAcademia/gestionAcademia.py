import mysql.connector
from datetime import date

# Conectar a la base de datos
def connect():
    return mysql.connector.connect(
        host="localhost",
        user="oscar",
        password="Examen2024",
        database="School"
    )

# Matricular a un estudiante en un curso
def enroll_student(student_id, course_id, start_date, end_date):
    conn = connect()
    cursor = conn.cursor()
    try:
        query = """
        INSERT INTO Enrollments (student_id, course_id, start_date, end_date)
        VALUES (%s, %s, %s, %s)
        """
        cursor.execute(query, (student_id, course_id, start_date, end_date))
        conn.commit()
        print("Estudiante matriculado con éxito.")
    except mysql.connector.Error as err:
        print("Error:", err)
    finally:
        cursor.close()
        conn.close()

# Listado de estudiantes en un curso
def list_students_in_course(course_id):
    conn = connect()
    cursor = conn.cursor()
    try:
        query = """
        SELECT Students.id, Students.name, Students.email
        FROM Students
        INNER JOIN Enrollments ON Students.id = Enrollments.student_id
        WHERE Enrollments.course_id = %s
        """
        cursor.execute(query, (course_id,))
        results = cursor.fetchall()
        print("Estudiantes en el curso:")
        for row in results:
            print(row)
    except mysql.connector.Error as err:
        print("Error:", err)
    finally:
        cursor.close()
        conn.close()

# Baja de un estudiante en un curso
def remove_student_from_course(student_id, course_id):
    conn = connect()
    cursor = conn.cursor()
    try:
        query = "DELETE FROM Enrollments WHERE student_id = %s AND course_id = %s"
        cursor.execute(query, (student_id, course_id))
        conn.commit()
        print("Estudiante dado de baja del curso.")
    except mysql.connector.Error as err:
        print("Error:", err)
    finally:
        cursor.close()
        conn.close()

# Actualizar el precio de un curso
def update_course_price(course_id, new_price):
    conn = connect()
    cursor = conn.cursor()
    try:
        query = "UPDATE Courses SET price = %s WHERE id = %s"
        cursor.execute(query, (new_price, course_id))
        conn.commit()
        print("Precio del curso actualizado.")
    except mysql.connector.Error as err:
        print("Error:", err)
    finally:
        cursor.close()
        conn.close()

# Emitir recibos por estudiante
def generate_receipts():
    conn = connect()
    cursor = conn.cursor()
    try:
        query = """
        SELECT Students.name, Students.email, Courses.name AS course_name, Courses.price
        FROM Students
        INNER JOIN Enrollments ON Students.id = Enrollments.student_id
        INNER JOIN Courses ON Enrollments.course_id = Courses.id
        """
        cursor.execute(query)
        results = cursor.fetchall()
        print("Recibos:")
        for row in results:
            student_name, email, course_name, price = row
            print(f"Estudiante: {student_name} | Email: {email} | Curso: {course_name} | Precio: ${price}")
    except mysql.connector.Error as err:
        print("Error:", err)
    finally:
        cursor.close()
        conn.close()

# Ejemplo de uso
if __name__ == "__main__":
    # Matricular estudiante
    enroll_student(1, 2, date(2024, 1, 10), date(2024, 6, 10))
    # Listar estudiantes
    list_students_in_course(2)
    # Baja de estudiante
    remove_student_from_course(1, 2)
    # Actualizar precio de un curso
    update_course_price(2, 150.00)
    # Generar recibos
    generate_receipts()
