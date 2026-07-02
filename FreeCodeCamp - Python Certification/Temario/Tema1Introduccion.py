# 1. Declarar variables:
nombre = "Juan"
edad = 30

# Reglas de nombrado:
# - No pueden comenzar con un número.
# - No pueden contener espacios.
# - No pueden ser palabras reservadas (como "if", "for", etc.).
# - Se recomienda usar nombres descriptivos y en minúsculas, separando palabras con guiones bajos (snake_case).
# - Python es de tipado dinámico, lo que significa que no es necesario declarar el tipo de dato de una variable; Python lo infiere automáticamente.
# - Python es sensible a mayúsculas y minúsculas, por lo que "nombre" y "Nombre" serían variables diferentes.

# 2. Tipos de datos:
# - Cadenas de texto (str):
nombre_completo = "Juan Pérez"
# - Números enteros (int):
edad = 30
# - Números decimales (float):
altura = 1.75
# - Booleanos (bool):
es_estudiante = True
# - Listas (list):
frutas = ["manzana", "banana", "naranja"]
# - Diccionarios (dict):
persona = {"nombre": "Juan", "edad": 30, "altura": 1.75}
# - Tuplas (tuple):
coordenadas = (10.0, 20.0)
# - Conjuntos (set):
colores = {"rojo", "verde", "azul"}
# - Rangos (range):
numeros = range(1, 10)  # Crea un rango de números del 1 al 9
# - None (NoneType):
valor_desconocido = None
# - Tipos de datos personalizados (clases):
class Persona:
    def __init__(self, nombre, edad):
        self.nombre = nombre
        self.edad = edad
persona1 = Persona("Juan", 30)  
# - Tipos de datos complejos (como objetos, funciones, etc.) también pueden ser utilizados en Python.


# 3. Operaciones básicas:
# - Suma:
a = 5
b = 10  
suma = a + b  # Resultado: 15
# - Resta:
resta = a - b  # Resultado: -5
# - Multiplicación:
multiplicacion = a * b  # Resultado: 50
# - División:
division = b / a  # Resultado: 2.0
# - Concatenación de cadenas:
saludo = "Hola, " + nombre  # Resultado: "Hola, Juan"

# 4. Mostrar por pantalla:
print("Nombre:", nombre)
print("Edad:", edad)
print("Altura:", altura)
print("Es estudiante:", es_estudiante)

# Funciones type() y isinstance():
print(type(nombre))  # Resultado: <class 'str'>
print(isinstance(nombre, str))  # Resultado: True
# Es posible comprobar varios tipos de datos con isinstance():
print(isinstance(edad, (int, float)))  # Resultado: True si es al menos uno.


# 5. Cadenas:
#   En python da igual que uses comillas simples o dobles para definir una cadena de texto.
#   Para definir una cadena de texto que contenga comillas simplemente usas la contraria.

#   - in devuelve un booleano indicando si la cadena de texto contiene o no la subcadena.
cadena1 = 'Hola, mundo'
print('Hola' in cadena1)  # Resultado: True

#   - len() devuelve la longitud de la cadena de texto.
print(len(cadena1))  # Resultado: 11

#   - indices:
#     Los índices comienzan en 0, por lo que el primer carácter de la cadena tiene índice 0, el segundo carácter tiene índice 1, y así sucesivamente.
print(cadena1[0])  # Resultado: 'H'
print(cadena1[1:5])  # Resultado: 'Hola'
#   - indices negativos:
print(cadena1[-1])  # Resultado: 'o'
print(cadena1[-5:-1])  # Resultado: 'mundo'

#   - las cadenas de texto son inmutables, lo que significa que no se pueden cambiar una vez creadas. Si deseas modificar una cadena, debes crear una nueva.
#   - las comillas triples permiten escribir cadenas de texto en varias líneas.
#   - puedes aplicar operadores matematicos para concatenar cadenas de texto, como el operador + para concatenar y el operador * para repetir. Pero debes asegurarte de que ambos operandos sean cadenas de texto, de lo contrario obtendrás un error.
#   - puedes usar el método .format() para insertar valores en una cadena de texto. Por ejemplo:
#   - str() convierte un valor a cadena de texto.
nombre = "Juan"
cadena_con_nombre = "Hola, {}".format(nombre)
print(cadena_con_nombre)  # Resultado: "Hola, Juan"
#   - f-strings (disponibles a partir de Python 3.6) permiten insertar valores directamente en la cadena de texto usando llaves {}. Por ejemplo:

cadena_con_nombre = f"Hola, {nombre}"
print(cadena_con_nombre)  # Resultado: "Hola, Juan"
