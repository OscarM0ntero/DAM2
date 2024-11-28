'''
NOMBRE Y APELLIDOS: Oscar Montero Hinojosa  2ºDAM
Desarrolla un programa en Python que gestione un sistema de biblioteca.No hace falta que comentes cada cosa que realices pero sí lo que consideres debería saber otro compañero tuyo, para cuando te vayas de vacaciones y tu compañero debe manipular tu código. Este programa debe cumplir los siguientes requisitos:

1.Define una clase base Material que tenga atributos comunes a todos los materiales de la biblioteca, como:
id (único para cada material)
título
autor
año de publicación 
'''

#Clase base Material
class Material:
    def __init__(self, id=0, titulo="", autor="", anio_publicacion=0):
        self.id = id
        self.titulo = titulo
        self.autor = autor
        self.anio_publicacion=anio_publicacion
    def mostrar(self):
        return f"ID: {self.id} | Titulo: {self.titulo} | Autor: {self.autor} | Año de publicación: {self.anio_publicacion}"

'''
2.Crea dos clases que hereden de Material:

Libro: Incluye atributos adicionales como género (debe seleccionarse entre una lista predefinida: "Ficción", "No Ficción", "Terror", "Ciencia") y número de páginas (debe ser mayor a 0).

Revista: Incluye atributos adicionales como número de edición y mes de publicación (debe seleccionarse entre los meses válidos: "Enero", "Febrero", ..., "Diciembre")
'''

#Clase Libro que hereda de Material
class Libro(Material):
    def __init__(self, id=0, titulo="", autor="", anio_publicacion=0, genero="", num_pag=0):
        generos = {"Ficción", "No Ficción", "Terror", "Ciencia"}
        # Si se introduce un género erroneo o numero de paginas erroneo, enviamos un ValueError
        if genero not in generos:
            raise ValueError("Error. El género no es correcto. (Ficción, No Ficción, Terror, Ciencia)")
        elif num_pag <=0:
           raise ValueError("Error. El número de páginas no puede ser igual o inferior a 0.")
        else:
            super().__init__(id, titulo, autor, anio_publicacion)
            self.genero = genero
            self.num_pag = num_pag
    
    def mostrar(self):
        return f"{super().mostrar()} | Género: {self.genero} | Número de páginas: {self.num_pag}\n"

# Clase Revista que hereda de Material
class Revista(Material):
    def __init__(self, id=0, titulo="", autor="", anio_publicacion=0, num_edicion=0, mes_publicacion=""):
        meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}
        # Si se introduce un mes incorrecto, enviamos un ValueError
        if mes_publicacion not in meses:
            raise ValueError("El mes de publicación es incorrecto.")
        else:
            super().__init__(id, titulo, autor, anio_publicacion)
            self.num_edicion = num_edicion
            self.mes_publicacion = mes_publicacion
    
    def mostrar(self):
        return f"{super().mostrar()} | Número edición: {self.num_edicion} | Mes de publicación: {self.mes_publicacion}\n"
   
'''
3.Utiliza un diccionario para almacenar los materiales, donde la clave sea el id y el valor sea un objeto de tipo Libro o Revista.
'''

# Diccionario que almacena los materiales creados que no han sido eliminados
dic_materiales = {}

'''
4.Mantén una lista de todos los id existentes para verificar que no se repitan al agregar nuevos materiales.
'''
# Lista de las ids de los materiales que hay en el diccionario
ids_materiales = []

'''
5. Generar Estadísticas:debe devolver todos estos valores
Total de materiales registrados.
Número de libros y revistas.
Promedio de páginas para los libros.
Ayuda: se puede usar la siguiente función: Ej: isinstance(m, Libro)--> devuelve true si el objeto m es de tipo Libro
'''

# Permite generar estadisticas de los materiales guardados
def generar_estadisticas():
    print("Total de materiales registrados: "+ str(len(ids_materiales)))
    cant_libros = 0
    cant_revistas = 0
    prom_pag_lib=0
    for m in dic_materiales.values():
        if isinstance(m, Libro):
            cant_libros+=1
            prom_pag_lib+=m.num_pag
        else:
            cant_revistas+=1

    # Si hay libros realizamos el promedio, si no hay pues simplemente lo ponemos a 0
    if cant_libros > 0:
        prom_pag_lib/=cant_libros
    else:
        prom_pag_lib = 0
    
    print("Número de libros: "+str(cant_libros))
    print("Número de revistas: "+str(cant_revistas))
    print("Promedio de páginas para los libros: "+str(prom_pag_lib))


'''
6.Implementa un menú que permita al usuario realizar las siguientes acciones:
Agregar Material: Permite al usuario agregar un nuevo Libro o Revista.
Listar Materiales: Muestra una lista de todos los materiales registrados con sus detalles. Elije la forma de presentación más adecuada para que el usuario lo vea claro.
Buscar Material por ID: Permite al usuario buscar un material específico por su id.
Eliminar Material: Elimina un material específico usando su id.
Generar Estadísticas
Salir: Termina la ejecución del programa.
'''

# Permite agregar nuevos materiales y guardarlos en el diccionario
def agregar_material():
    print("\ta) Agregar Libro")
    print("\tb) Listar Revista")
    opcion = input("\nSeleccione una opción: ").strip().lower()
    # Para cualquier caso, registramos los datos del Material
    if opcion == 'a' or opcion == 'b':
        id = int(input("\nID: "))
        while id in ids_materiales:
            print("Esta ID ya existe, prueba otra vez.")
            id = input("\nID: ")            
        titulo = input("\nTítulo: ")
        autor = input("\nAutor: ")
        anio_publicacion = int(input("\nAño de publicación: "))

    # Dependiendo si es Libro o Revista, pediremos los datos especificos. Controlamos que los datos sean correctos con el try-except
    if opcion == 'a':
        genero = input("\nGénero: ")
        num_pag = int(input("\nNúmero de páginas: "))
        try:
            libro = Libro(id, titulo, autor, anio_publicacion, genero, num_pag)
            ids_materiales.append(id)
            dic_materiales[id] = libro
        except ValueError as error:
            print(error)
        
    elif opcion == 'b':
        num_edicion = int(input("\nNúmero edicion: "))
        mes_publicacion = input("\nMes de publicación: ")
        try:
            revista = Revista(id, titulo, autor, anio_publicacion, num_edicion, mes_publicacion)
            ids_materiales.append(id)
            dic_materiales[id] = revista
        except ValueError as error:
            print(error)
    else:
        print("\nOpción no válida.")

# Devuelve una lista de los materiales registrados en el diccionario
def listar_materiales():
    print("\nLibros:")
    for m in dic_materiales.values():
        if isinstance(m, Libro):
            print(m.mostrar())
    print("\nRevistas:")
    for m in dic_materiales.values():
        if isinstance(m, Revista):
            print(m.mostrar())

# Permite buscar material en base a una ID
def buscar_material_id():
    id = int(input("Dame una ID: "))
    if id in ids_materiales:
        print(dic_materiales[id].mostrar())
    else:
        print("Material no encontrado.")

# Permite eliminar material en base a una ID
def eliminar_material_id():
    id = int(input("Dame una ID: "))
    if id in ids_materiales:
       dic_materiales.pop(id)
       ids_materiales.pop(ids_materiales.index(id))
       print("Material eliminado.")
    else:
        print("Material no encontrado.")
        
# Imprime el menu
def menu():
    print("\nMENÚ DE OPCIONES")
    print("\ta) Agregar Material")
    print("\tb) Listar Materiales")
    print("\tc) Buscar Material por ID")
    print("\td) Eliminar Material")
    print("\te) Generar Estadísticas")
    print("\tf) Salir")
    
# En el main tenemos un selector del menu, para permitir al usuario realizar varias opciones o terminar el programa
def main():
    while True:
        menu()
        opcion = input("\nSeleccione una opción: ").strip().lower()
        if opcion == 'a':
            agregar_material()
        elif opcion == 'b':
            listar_materiales()
        elif opcion == 'c':
            buscar_material_id()
        elif opcion == 'd':
            eliminar_material_id()
        elif opcion == 'e':
            generar_estadisticas()
        elif opcion == 'f':
            print("\nSaliendo del programa.")
            break
        else:
            print("\nOpción no válida.")

if __name__ == "__main__":
    main()