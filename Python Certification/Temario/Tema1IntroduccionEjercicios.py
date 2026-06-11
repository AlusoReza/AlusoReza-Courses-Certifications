# 1. Crea una variable llamada name y asígnale la cadena Alice. Recuerda rodear el valor con comillas simples o dobles como se muestra en el ejemplo.
name = 'Alice'

# 2. Imprime la variable name. Revisa la salida en el terminal.
#print(name)

# 3. Usa la función type() con name como argumento e imprime la salida como en el ejemplo. Revisa la salida en el terminal que muestra que name es del tipo str (cadena).
#print(type(name))

# 4. Declara una variable llamada is_student y asígnale el valor True.
is_student = True

# 5. Imprime tanto is_student como type(is_student) en la misma línea usando una coma , como se muestra en el ejemplo. Luego, verifica la salida en la terminal que muestra el valor de is_student y su tipo como bool (booleano).
print(is_student, type(is_student))

# 6. Elimina las salidas anteriores de la variable name. Luego, imprime name y type(name) juntos en una línea separados por una coma como en el paso anterior.
print(name, type(name))

# 7. Declara una variable llamada age y asígnale el valor entero 20. Luego, imprime el valor y el tipo de dato de age juntos separados por una coma. Revisa la salida en el terminal que muestra el valor de age y su tipo como int (entero).
age = 20
print(age, type(age))

# 8. Ahora, añade la puntuación del estudiante. Declara una variable llamada score y asígnale el valor 80.5. Aunque tanto age como score son números, pueden no ser del mismo tipo. Python proporciona una función llamada isinstance() para verificar esto. Usa isinstance() para verificar si score es un int, y muestra el resultado en el terminal como se muestra en el ejemplo anterior.
score = 80.5
#print(isinstance(score, int))

# 9 Reemplaza int con float en la llamada existente a isinstance() para confirmar esto.
print(isinstance(score, float))

# 10. Completa la boleta imprimiendo el valor de score junto con su tipo de dato usando una sola instrucción print().
print(score, type(score))

