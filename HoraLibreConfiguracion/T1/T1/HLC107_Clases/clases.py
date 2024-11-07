class Persona:
    def __init__(self, nombre, direccion, telefono):
        self.nombre = nombre
        self.direccion = direccion
        self.telefono = telefono

    def __str__(self):
        return f"Nombre: {self.nombre} Direccion: {self.direccion} Telefono: {self.telefono}"

contactos = {}

def mostrar_menu():
    print("\nMENU DE OPCIONES")
    print("a) Listado de contactos por orden alfabetico.")
    print("b) Anadir un nuevo contacto.")
    print("c) Modificar un contacto.")
    print("d) Buscar un numero de telefono.")
    print("e) Eliminar un contacto.")
    print("f) Salir")
    return input("Seleccione una opcion: ").lower()

def listar_contactos():
    if not contactos:
        print("No hay contactos en la lista.")
    else:
        for nombre in sorted(contactos):
            print(contactos[nombre])

def agregar_contacto():
    nombre = input("Ingrese el nombre del contacto: ").upper()
    direccion = input("Ingrese la direccion del contacto: ")
    telefono = input("Ingrese el telefono del contacto: ")
    
    if nombre in contactos:
        print("El contacto ya existe.")
        actualizar = input("¿Desea actualizar su telefono? (s/n): ").lower()
        if actualizar == 's':
            contactos[nombre].direccion = direccion
            contactos[nombre].telefono = telefono
            print("Contacto actualizado.")
        else:
            print("No se ha realizado ningun cambio.")
    else:
        contactos[nombre] = Persona(nombre, direccion, telefono)
        print("Contacto anadido exitosamente.")

def modificar_contacto():
    nombre = input("Ingrese el nombre del contacto a modificar: ").upper()
    if nombre not in contactos:
        insertar = input("El contacto no existe. ¿Desea insertarlo? (s/n): ").lower()
        if insertar == 's':
            direccion = input("Ingrese la direccion del contacto: ")
            telefono = input("Ingrese el telefono del contacto: ")
            contactos[nombre] = Persona(nombre, direccion, telefono)
            print("Contacto anadido.")
        else:
            print("No se ha realizado ningun cambio.")
    else:
        direccion = input("Ingrese la nueva direccion del contacto: ")
        telefono = input("Ingrese el nuevo telefono del contacto: ")
        contactos[nombre].direccion = direccion
        contactos[nombre].telefono = telefono
        print("Contacto modificado.")

def buscar_telefono():
    telefono = input("Ingrese el numero de telefono a buscar: ")
    encontrado = False
    for persona in contactos.values():
        if persona.telefono == telefono:
            print(f"El numero de telefono pertenece a: {persona.nombre}")
            encontrado = True
            break
    if not encontrado:
        print("No se ha encontrado el numero de telefono.")

def eliminar_contacto():
    nombre = input("Ingrese el nombre del contacto a eliminar: ").upper()
    if nombre in contactos:
        del contactos[nombre]
        print("Contacto eliminado.")
    else:
        print("El contacto no existe.")

def main():
    while True:
        opcion = mostrar_menu()
        if opcion == 'a':
            listar_contactos()
        elif opcion == 'b':
            agregar_contacto()
        elif opcion == 'c':
            modificar_contacto()
        elif opcion == 'd':
            buscar_telefono()
        elif opcion == 'e':
            eliminar_contacto()
        elif opcion == 'f':
            print("Saliendo del programa.")
            break
        else:
            print("Opcion no valida. Por favor, seleccione una opcion valida.")
        
        input("\nPresione Enter para continuar...")

if __name__ == "__main__":
    main()
