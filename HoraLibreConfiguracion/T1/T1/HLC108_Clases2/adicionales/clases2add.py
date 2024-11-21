class Producto:
    def __init__(self, nombre="", precio=0, descuento=0):
        self._nombre = ""
        self._precio = 0
        self._descuento = 0

        self.set_nombre(nombre)
        self.set_precio(precio)
        self.set_descuento(descuento)
    
    def set_nombre(self, nombre):
        if isinstance(nombre, str) and nombre.strip():
            self._nombre = nombre.upper()
        
    def set_precio(self, precio):
        if isinstance(precio, (int, float)) and precio >= 0: 
            self._precio = precio
            
    def set_descuento(self, descuento):
        if isinstance(descuento, (int, float)) and 0 <= descuento <= 1: 
            self._descuento = descuento
            
    def get_nombre(self):
        return self._nombre
        
    def get_precio(self):
        return self._precio
        
    def get_descuento(self):
        return self._descuento
        
    def mostrar(self):
        return f"Nombre: {self._nombre}\nPrecio: {self._precio}\nDescuento: {self._descuento}\n"

    def precio_final(self):
        return self._precio * (1 - self._descuento)

class Electronico(Producto):
    def __init__(self, nombre="", precio=0, descuento=0):
        if descuento > 10:
            print("El descuento no puede ser mayor a 10%")
        else:
            super().__init__(nombre, precio, descuento)
   
class Ropa(Producto):
    def __init__(self, nombre="", precio=0, descuento=0):
        if descuento > 20:
            print("El descuento no puede ser mayor a 20%")
        else:
            super().__init__(nombre, precio, descuento)
   
class Comida(Producto):
    def __init__(self, nombre="", precio=0):
            super().__init__(nombre, precio)
   

if __name__ == "__main__":
    try:
        electronico1 = Electronico("iPhone 15", 999, 8)
        electronico2 = Electronico("iPhone 16", 1499, 10)
        electronico3 = Electronico("iPhone 20", 2999, 15)
    
        ropa1 = Ropa("Pantalon Azul", 19.99, 10)
        ropa2 = Ropa("Pantalon Verde", 24.99, 20)
        ropa3 = Ropa("Pantalon Naranja", 199.99, 50)

        comida1 = Comida("Zumo de naranja", 1.99)
        comida2 = Comida("Zumo de piña", 2.50)
        comida3 = Comida("Zumo de papaya", 3.99)

        print(electronico1.mostrar())
        print(electronico2.mostrar())
        print(ropa1.mostrar())
        print(ropa2.mostrar())
        print(comida1.mostrar())
        print(comida2.mostrar())

    except ValueError:
        print("Error. Algun valor no es correcto")
    except TypeError:
        print("Error. Se ha introducido un valor no pedido.")
