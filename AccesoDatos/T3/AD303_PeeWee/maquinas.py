from peewee import *

# Configuración de la base de datos
db = MySQLDatabase(
    'oficina',  # Nombre BD
    user='oscar',  # Usuario MySQL
    password='Examen2024',  # Contraseña MySQL
    host='localhost',
    port=3306
)

# Modelos
class BaseModel(Model):
    class Meta:
        database = db

class Ubicacion(BaseModel):
    codigo = AutoField()  # Campo autoincremental
    lugar = CharField(max_length=100)

class Maquina(BaseModel):
    codigo = AutoField()  # Campo autoincremental
    modelo = CharField(max_length=100)
    descripcion = TextField(null=True)
    ram = CharField(max_length=50, null=True)
    hd = CharField(max_length=50, null=True)
    cpu = CharField(max_length=50, null=True)
    cod_ubicacion = ForeignKeyField(Ubicacion, backref='maquinas')

# Crear tablas
def crear_tablas():
    with db:
        db.create_tables([Ubicacion, Maquina])
    print("Tablas creadas correctamente.")

# Alta de maquinas
def alta_maquina(modelo, descripcion, ram, hd, cpu, cod_ubicacion):
    try:
        ubicacion = Ubicacion.get(Ubicacion.codigo == cod_ubicacion)
        nueva_maquina = Maquina.create(
            modelo=modelo,
            descripcion=descripcion,
            ram=ram,
            hd=hd,
            cpu=cpu,
            cod_ubicacion=ubicacion
        )
        print(f"Máquina '{nueva_maquina.modelo}' registrada con éxito.")
    except Ubicacion.DoesNotExist:
        print("Ubicación no encontrada.")

# Consulta de máquina por modelo
def consulta_por_modelo(modelo):
    maquinas = Maquina.select().where(Maquina.modelo.contains(modelo))
    if maquinas:
        for maquina in maquinas:
            print(f"Código: {maquina.codigo}, Modelo: {maquina.modelo}, RAM: {maquina.ram}, HD: {maquina.hd}, CPU: {maquina.cpu}, Ubicación: {maquina.cod_ubicacion.lugar}")
    else:
        print("No se encontraron máquinas con ese modelo.")

# Actualización de máquina
def actualizar_maquina(codigo, ram=None, hd=None, cod_ubicacion=None):
    try:
        maquina = Maquina.get_by_id(codigo)
        if ram:
            maquina.ram = ram
        if hd:
            maquina.hd = hd
        if cod_ubicacion:
            ubicacion = Ubicacion.get(Ubicacion.codigo == cod_ubicacion)
            maquina.cod_ubicacion = ubicacion
        maquina.save()
        print(f"Máquina '{maquina.modelo}' actualizada correctamente.")
    except Maquina.DoesNotExist:
        print("Máquina no encontrada.")
    except Ubicacion.DoesNotExist:
        print("Ubicación no encontrada.")

# Listado de máquinas por ubicación
def listar_maquinas_por_ubicacion(cod_ubicacion):
    try:
        ubicacion = Ubicacion.get(Ubicacion.codigo == cod_ubicacion)
        maquinas = Maquina.select().where(Maquina.cod_ubicacion == ubicacion)
        print(f"Máquinas en '{ubicacion.lugar}':")
        for maquina in maquinas:
            print(f"- Modelo: {maquina.modelo}, RAM: {maquina.ram}, HD: {maquina.hd}, CPU: {maquina.cpu}")
    except Ubicacion.DoesNotExist:
        print("Ubicación no encontrada.")

# Menú principal
def menu():
    while True:
        print("\n--- Menú Principal ---")
        print("1. Alta de máquinas")
        print("2. Consulta de máquina por modelo")
        print("3. Actualización de máquina")
        print("4. Listado de máquinas por ubicación")
        print("5. Salir")
        opcion = input("Selecciona una opción: ")

        if opcion == "1":
            alta_maquina()
        elif opcion == "2":
            consulta_por_modelo()
        elif opcion == "3":
            actualizar_maquina()
        elif opcion == "4":
            listar_maquinas_por_ubicacion()
        elif opcion == "5":
            print("Programa finalizado.")
            break
        else:
            print("Opción no válida. Intentalo de nuevo.")

# Ejecutar el programa
if __name__ == "__main__":
    # Crear tablas si no existen
    crear_tablas()

    # Agregar ubicaciones iniciales para pruebas
    if not Ubicacion.select().exists():
        Ubicacion.create(lugar="Almacén Central")
        Ubicacion.create(lugar="Oficina Principal")

    # Ejecutar el menú
    menu()