class Persona:
    def __init__(self, nombre="", apellidos="", dni="", edad=0):
        self.__nombre = ""
        self.__apellidos = ""
        self.__dni = ""
        self.__edad = 0
        # Usamos los setters para validar los datos
        self.set_nombre(nombre)
        self.set_apellidos(apellidos)
        self.set_dni(dni)
        self.set_edad(edad)
    
    def set_nombre(self, nombre):
        if nombre.strip():  # Verifica que no sea cadena vacía
            self.__nombre = nombre.upper()
        
    def set_apellidos(self, apellidos):
        if apellidos.strip():  # Verifica que no sea cadena vacía
            self.__apellidos = apellidos.upper()
            
    def set_dni(self, dni):
        if dni.strip():  # Verifica que no sea cadena vacía
            self.__dni = dni.upper()
            
    def set_edad(self, edad):
        if isinstance(edad, int) and edad >= 0:
            self.__edad = edad
            
    def get_nombre(self):
        return self.__nombre
        
    def get_apellidos(self):
        return self.__apellidos
        
    def get_dni(self):
        return self.__dni
        
    def get_edad(self):
        return self.__edad
        
    def mostrar(self):
        return f"Nombre: {self.__nombre}\nApellidos: {self.__apellidos}\nDNI: {self.__dni}\nEdad: {self.__edad}"
        
    def mayor_de_edad(self):
        return self.__edad >= 18


class Cuenta:
    def __init__(self, titular, cantidad=0.0):
        if not isinstance(titular, Persona):
            raise ValueError("El titular debe ser una persona")
        self._titular = titular
        self._cantidad = float(cantidad)
    
    def get_titular(self):
        return self._titular
    
    def get_cantidad(self):
        return self._cantidad
    
    def ingresar(self, cantidad):
        if cantidad > 0:
            self._cantidad += cantidad
            
    def retirar(self, cantidad):
        if cantidad > 0:
            self._cantidad -= cantidad
            
    def mostrar(self):
        return f"Titular:\n{self._titular.mostrar()}\nCantidad: {self._cantidad}€"


class CuentaJoven(Cuenta):
    def __init__(self, titular, cantidad=0.0, bonificacion=0):
        super().__init__(titular, cantidad)
        self.__bonificacion = 0
        self.set_bonificacion(bonificacion)
        
    def set_bonificacion(self, bonificacion):
        if 0 <= bonificacion <= 100:
            self.__bonificacion = bonificacion
            
    def get_bonificacion(self):
        return self.__bonificacion
        
    def es_titular_valido(self):
        return self._titular.get_edad() < 25 and self._titular.mayor_de_edad()
        
    def mostrar(self):
        return f"{super().mostrar()}\nBonificación: {self.__bonificacion}%"


# Programa principal de prueba
if __name__ == "__main__":
    # Crear una persona mayor de edad
    persona1 = Persona("Juan", "García López", "12345678A", 30)
    print("=== Persona 1 ===")
    print(persona1.mostrar())
    print(f"¿Es mayor de edad?: {persona1.mayor_de_edad()}")
    
    # Crear una persona joven
    persona2 = Persona("Ana", "Martínez Ruiz", "87654321B", 20)
    print("\n=== Persona 2 ===")
    print(persona2.mostrar())
    print(f"¿Es mayor de edad?: {persona2.mayor_de_edad()}")
    
    # Crear una cuenta normal
    cuenta1 = Cuenta(persona1, 1000)
    print("\n=== Cuenta Normal ===")
    print(cuenta1.mostrar())
    cuenta1.ingresar(500)
    cuenta1.retirar(200)
    print("\nDespués de operaciones:")
    print(f"Saldo: {cuenta1.get_cantidad()}€")
    
    # Crear una cuenta joven
    cuenta_joven = CuentaJoven(persona2, 500, 10)
    print("\n=== Cuenta Joven ===")
    print(cuenta_joven.mostrar())
    print(f"¿Titular válido para cuenta joven?: {cuenta_joven.es_titular_valido()}")
    cuenta_joven.ingresar(300)
    cuenta_joven.retirar(100)
    print("\nDespués de operaciones:")
    print(f"Saldo: {cuenta_joven.get_cantidad()}€")
