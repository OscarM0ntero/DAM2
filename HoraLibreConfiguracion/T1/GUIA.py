# VARIABLES
# Numeros
entero = 10  
flotante = 3.14  
complejo = 1 + 2j  

# Strings
cadena = "Hola, mundo"  

# Booleanos
verdadero = True  
falso = False  

# Listas, Arrays y Diccionarios
listas = ["A","B","C"]      # Se pueden modificar
tuplas = ("D","E","F")      # Son listas inmutables
diccionarios = {"Key":"Value", "Key2":"Value2"}   # Anadir elementos con: diccionarios["Key"]="Value"

anidado1 = {"a": 1, "b": 2}
anidado2 = {"a": 1, "b": 2}
d = {
    "anidado1": anidado1,
    "anidado2": anidado2
}
print(d) 
# {'anidado1': {'a': 1, 'b': 2}, 'anidado2': {'a': 1, 'b': 2}}


# METODOS PARA ENTEROS
# bin(x)	        Convierte un entero a su representación binaria.	                            bin(10) → '0b1010'
# hex(x)	        Convierte un entero a su representación hexadecimal.	                        hex(255) → '0xff'
# abs(x)	        Devuelve el valor absoluto del número.	                                        abs(-5) → 5
# pow(x, y[, z])	Devuelve x elevado a la potencia y. Si z se pasa, calcula (x^y) % z.	        pow(2, 3) → 8
# int(str)	        Convierte una cadena o flotante en un entero (si es posible).	                int("10") → 10
# bit_length()	    Devuelve el número de bits necesarios para representar el número en binario.	(10).bit_length() → 4

# METODOS PARA FLOATS
# round(x, n)	    Redondea el número x a n decimales.	                                round(3.14159, 2) → 3.14
# is_integer()	    Devuelve True si el flotante es equivalente a un entero.	        (10.0).is_integer() → True

# METODOS PARA STRINGS
# capitalize()	    Capitaliza la primera letra de la cadena.	                        "python".capitalize() → 'Python'
# lower()	        Convierte todos los caracteres a minúsculas.	                    "PYTHON".lower() → 'python'
# upper()	        Convierte todos los caracteres a mayúsculas.	                    "python".upper() → 'PYTHON'
# strip()	        Elimina espacios en blanco al principio y al final.	                " hola ".strip() → 'hola'
# replace(a, b)	    Reemplaza todas las ocurrencias de a por b.	                        "hola mundo".replace('o', 'a') → 'hala munda'
# split(sep)	    Divide la cadena por un separador en una lista de substrings.	    "a,b,c".split(',') → ['a', 'b', 'c']
# join(iterable)	Une elementos de un iterable con la cadena como separador.	        ",".join(['a', 'b', 'c']) → 'a,b,c'
# startswith(pref)	Devuelve True si la cadena empieza con el prefijo dado.	            "python".startswith('py') → True
# endswith(suff)	Devuelve True si la cadena termina con el sufijo dado.	            "python".endswith('on') → True
# find(sub)	        Devuelve la posición de la primera aparición de un substring o -1.	"hola".find('o') → 1
# isalpha()	        Devuelve True si todos los caracteres son alfabéticos.	            "hola".isalpha() → True
# isdigit()	        Devuelve True si todos los caracteres son dígitos.	                "123".isdigit() → True
# isalnum()	        Devuelve True si todos los caracteres son alfanuméricos.	        "abc123".isalnum() → True
# string[inicio:final]         Subcadena de la string
# string[inicio:final:Cuanto salta?]    Subcadena de la string con saltos
# string.count(sub)             Cuenta la cantidad de veces que aparece el sub
# swapcase()        Invierte entre Mayus y Minus
# find(s)           Busca la posición de la primera aparición de un substring o -1
# startwith(s) o endwith(s) Busca si la string comienza o termina con un substring


# METODOS PARA LISTAS
# append	            Añade un elemento al final de la lista	                                list.append(5)
# clear	                Elimina toda la información de la lista	                                list.clear()
# copy	                Devuelve una copia de la lista	                                        list.copy()
# count	                Devuelve el número de ocurrencias de un elemento en la lista	        list.count('p')
# extend	            Extiende una lista extendida con otra lista 	                        list.extend(lst2)
# index	                Devuelve el índice que ocupa un elemento	                            list.index('e')
# insert	            Inserta un elemento en la lista en una posición exacta	                list.insert(3, 'y')
# pop	                Elimina un elemento y lo devuelve de la posición marcada o del final	list.pop() o list.pop(4)
# remove	            Elimina todas las ocurrencias de un elemento de la lista	            list.remove('String or char')
# reverse	            Invierte el orden de los elementos de la lista	                        list.reverse()
# sort	                Permite ordenar la lista                                                list.sort()

# METODOS PARA DICCIONARIOS
# clear()	            Elimina todo el contenido del diccionario.	                                    diccionarios.clear()
# get(key[, default])	Devuelve el valor de la key, o un valor por defecto si no se encuentra.	        diccionarios.get('Key') o diccionarios.get('Key', 'No encontrado')
# items()	            Devuelve una lista con tuplas (key, value) del diccionario.	                    list(diccionarios.items())
# keys()	            Devuelve una lista con todas las keys del diccionario.	                        list(diccionarios.keys())
# values()	            Devuelve una lista con todos los valores del diccionario.	                    list(diccionarios.values())
# pop(key[, default])	Elimina y devuelve el valor asociado a la key, o devuelve un valor por defecto.	diccionarios.pop('Key', -1)
# popitem()	            Elimina de manera aleatoria un elemento del diccionario.	                    diccionarios.popitem()
# update(obj)	        Actualiza un diccionario con otro. Añade keys si no existen.	                d1.update({'d': 400}) o d1.update(d2) (Esto sobreescribe los valores de d1, si tienen la misma key)