# 1. Crea una clase Persona para guardar nombre, apellidos, DNI y edad de una
# persona.
# a. Define un constructor, donde se puedan indicar los datos iniciales (pueden
# estar vacíos).
# b. Para cada atributo define sus correspondientes setter (para poder validar el
# valor de entrada: nombre, apellidos y DNI no pueden ser cadenas vacías y
# se guardará en mayúsculas. Edad debe ser un valor entero positivo).
# c. Para cada atributo define sus correspondientes getter.
# d. Añade el método mostrar(), que muestra los datos de la persona (nombre,
# apellidos, DNI edad).
# e. Añade el método mayorDeEdad(), que indica si la persona es mayor de
# edad o no.
class Persona:
    def __init__(self, nombre, apellidos, dni, edad):
        self._nombre = nombre.upper()
        self._apellidos = apellidos.upper()
        self._dni = dni
        self._edad = edad

    # Getters and Setters
    @property
    def get_nombre(self):
        return self._nombre

    def setNombre(self, nuevo_nombre):
        if len(nuevo_nombre) != 0:
            self.__nombre = nuevo_nombre.upper()

    @property
    def get_apellidos(self):
        return self._apellidos

    def set_apellidos(self, apellidos_nuevo):
        if apellidos_nuevo:
            self._apellidos = apellidos_nuevo.upper()

    @property
    def get_dni(self):
        return self._dni

    def set_dni(self, dni_nuevo):
        if dni_nuevo:
            self._dni = dni_nuevo.upper()

    @property
    def get_edad(self):
        return self._edad

    def setEdad(self, nueva_edad):
        try:
            if int(nueva_edad) >= 0:
                self._edad = nueva_edad
            else:
                self._edad = ""
        except ValueError:
            self._edad = "La edad debe ser un numero entero positivo"

    # Método para mostrar los datos de casa persona
    def mostrar(self):
        print(f"Nombre: {self.get_nombre}")
        print(f"Apellidos: {self.get_apellidos}")
        print(f"DNI: {self.get_dni}")
        print(f"Edad: {self.get_edad} años")

    # Método para saber si es mayor de edad
    def mayorDeEdad(self):
        if self._edad >= 18:
            return "Es mayor de edad."
        else:
            return "Es menor de edad."


# 2. Crea una clase Cuenta, que tendrá los siguientes atributos: titular (que es una
# persona) y cantidad (puede tener decimales).
# a. Define un constructor, teniendo en cuenta que el titular es obligatorio y la
# cantidad es opcional.
# b. Define los getter y setter, teniendo en cuenta que la cantidad no se puede
# modificar directamente sino realizando ingresos o retiradas de dinero.
# c. Define ingresar(cantidad), que ingresa la cantidad indicada (hay que
# comprobar que la cantidad es positiva).
# d. Define retirar(cantidad), que retira la cantidad indicada (hay que comprobar
# que la cantidad es positiva). La cuenta se puede quedar en números rojos.
# e. Define mostrar, que muestra todos los datos de la cuenta.


class Cuenta:
    def __init__(self, titular, cantidad=0.0):
        self._titular = titular
        self._cantidad = cantidad

    # Getters and Setters

    @property
    def get_titular(self):
        return self._titular

    @property
    def get_cantidad(self):
        return self._cantidad

    def ingresar(self, cantidadIngresar):
        if cantidadIngresar > 0:
            self._cantidad += cantidadIngresar

    def retirar(self, cantidadRetirar):
        if cantidadRetirar > 0:
            self._cantidad -= cantidadRetirar

    def mostrar(self):
        print("Información de la cuenta:")
        print(
            f"Titular: {self.get_titular.get_nombre +"  "+  self.get_titular.get_apellidos}"
        )
        print(f"Cantidad: {self.get_cantidad}")


# 3. Crea una clase CuentaJoven, para clientes menores de 25 años. Hereda de la
# clase Cuenta, a la que se añade:
# a. Un atributo bonificacion, que guarda el porcentaje de bonificación que se le
# da al cliente a final de año. Al tratarse de un porcentaje, debe ser un valor
# entre 0 y 100.
# b. Adapta mostrar para que se vea toda la información de la cuenta joven.


class CuentaJoven(Cuenta):
    def __init__(self, titular, cantidad=0.0, bonificacion=0):
        super().__init__(titular, cantidad)
        self.set_bonificacion(bonificacion)

    def set_bonificacion(self, bonificacion):
        if 0 <= bonificacion <= 100:
            self._bonificacion = bonificacion
        else:
            print("Error: La bonificación debe estar entre 0 y 100.")

    def get_bonificacion(self):
        return self._bonificacion

    def mostrar(self):
        super().mostrar()
        print(f"Bonificación: {self.get_bonificacion()}%")


# 4. Crea un programa que haga uso de las tres clases para crear una cuenta normal
# y una cuenta joven

# Crear una persona mayor
persona1 = Persona("Manolo", "Galvez", "12345678A", 56)
persona2 = Persona("Alicia", "Ramirez", "87654321B", 30)
# Crear dos personas jovenes
persona3 = Persona("Esteban", "Rondo", "56789012C", 23)
persona4 = Persona("Maria", "Fernandez", "98765432C", 28)


# Crear una cuenta normal
cuenta_normal = Cuenta(persona1, 5000.0)
cuenta_normal.ingresar(500.0)
cuenta_normal.retirar(200.0)

cuenta_normal_2 = Cuenta(persona2, 1500.0)
cuenta_normal_2.ingresar(200.0)
cuenta_normal_2.retirar(100.0)

# Crear una cuenta joven
cuenta_joven1 = CuentaJoven(persona3, 1500.0, 12)
cuenta_joven1.ingresar(300.0)
cuenta_joven1.retirar(100.0)

cuenta_joven2 = CuentaJoven(persona4, 2000.0, 8)
cuenta_joven2.ingresar(500.0)
cuenta_joven2.retirar(200.0)

# Mostrar información
print("CUENTA NORMAL:")
persona1.mostrar()
cuenta_normal.mostrar()
persona2.mostrar()
cuenta_normal_2.mostrar()

print("\nCUENTA JOVEN:")
persona3.mostrar()
cuenta_joven1.mostrar()
persona4.mostrar()
cuenta_joven2.mostrar()
