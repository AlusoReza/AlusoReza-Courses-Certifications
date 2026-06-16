#Taller 1:
print('Taller 1:')

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

#---------------------------------------------------------------
print('------------------------------------------------------')

#Taller 2:
print('Taller 2:')

#1. Crea una variable first_name que almacene la cadena John y una variable last_name que almacene la cadena Doe. Luego imprime first_name y last_name.
first_name = 'John'
last_name = 'Doe'
#print(first_name, last_name)
print(first_name)
print(last_name)

#2. Crea una variable full_name concatenando first_name y last_name. Luego imprime full_name.
#full_name = first_name + last_name
#print(full_name)

#3. Actualiza tu variable full_name para que concatene first_name, un espacio y last_name.
full_name = first_name + ' ' + last_name
print(full_name)

#4. A continuación, crea una variable address para almacenar la dirección del empleado. Asígnale la cadena 123 Main Street y finalmente imprime address.
address = '123 Main Street'
print(address)

#5. Usa el operador += para agregar la cadena , Apartment 4B a tu variable address.
address += ', Apartment 4B'
print(address)

#6. Ahora crea una variable llamada employee_age y asígnale el entero 28.
employee_age = 28

#7. Ahora, quieres crear una cadena que muestre la edad del empleado. Comienza creando una variable employee_info y asígnale el resultado de concatenar: la variable full_name.y una cadena que consiste en los caracteres is precedidos y seguidos por un espacio.
employee_info = full_name + ' is '

#8. Actualiza la asignación de employee_info para también concatenar employee_age al final.Para solucionar esto, debes convertir el número a una cadena primero usando la función str(), que devuelve la versión en cadena de un objeto:
employee_info += str(employee_age)

#9. Finalmente, imprime employee_info concatenando ' years old'.
employee_info += ' years old'
print(employee_info)

#10. Crea una variable llamada experience_years y asígnale el entero 5. Luego, crea una variable experience_info. Asígnale una cadena formada por la concatenación de 'Experience: ', la variable experience_years (convertida a cadena) y ' years'. Imprime el resultado en el terminal.
experience_years = 5
experience_info = 'Experience: ' + str(experience_years) + ' years'
print(experience_info)

#11. Crea una variable employee_card y asígnale un f-string que muestre Employee: seguido de un espacio y el valor de la variable full_name.
employee_card = f'Employee: {full_name}'

#12. Actualiza la asignación de employee_card para incluir la edad del empleado. La cadena final debe verse así: Employee: [name] | Age: [age] con [name] reemplazado por el nombre del empleado, y [age] reemplazado por la edad del empleado.
employee_card = f'Employee: {full_name} | Age: {employee_age}'

#13. Crea una variable llamada position con el valor de la cadena Data Analyst y una variable llamada salary con el valor del entero 75000. Luego, actualiza tu f-string employee_card para incluir el puesto y el salario. Debe seguir este formato exacto: Employee: [full_name] | Age: [employee_age] | Position: [position] | Salary: $[salary]. Reemplaza los marcadores con las variables correspondientes. Finalmente, imprime employee_card para ver el resultado.
position = 'Data Analyst'
salary = 75000
employee_card = f'Employee: {full_name} | Age: {employee_age} | Position: {position} | Salary: ${salary}'

#14. Define employee_code como 'DEV-2026-JD-001'. Después, crea una variable department y asígnale el segmento de employee_code desde el índice 0 hasta el 3. Luego imprime department en la terminal.
employee_code = 'DEV-2026-JD-001'
department = employee_code[0:3]
print(department)

#15. Crea una variable year_code y asígnale el segmento de employee_code desde el índice 4 hasta el 8. Esto extraerá 2026. Luego crea una variable initials y asígnale el segmento de employee_code desde el índice 9 hasta el 11. Esto extraerá JD. Finalmente, imprime ambas variables en el terminal.
year_code = employee_code[4:8]
initials = employee_code[9:11]
print(year_code)
print(initials)

#16. Crea una variable llamada last_three. Usa indexación negativa para extraer los últimos tres caracteres de employee_code (que representan el número de identificación). Finalmente, imprime last_three en la terminal.
last_three = employee_code[-3:]
print(last_three)

#---------------------------------------------------------------
print('------------------------------------------------------')

#Taller 3:
print('Taller 3:')

#1. Crea una variable llamada running_total y asígnale el valor 0.
running_total = 0

#2.Crea una variable llamada num_of_friends y asígnale el valor de 4. Esto se usará más adelante en el taller para calcular la división final.
num_of_friends = 4

#3.Crea cuatro variables: appetizers con valor 37.89, main_courses con valor 57.34, desserts con valor 39.39 y drinks con valor 64.21.
appetizers = 37.89
main_courses = 57.34
desserts = 39.39
drinks = 64.21

#4.Usa el operador += una vez para agregar appetizers, main_courses, desserts y drinks a running_total. Finalmente, usa print() para mostrar la cadena Total bill so far: seguida de un espacio y el valor de running_total.
running_total += appetizers + main_courses + desserts + drinks
print(f'Total bill so far: {running_total}')

#5. Crea una variable llamada tip y asígnale el resultado de multiplicar running_total por 0.25. Finalmente, usa print() para mostrar la cadena Tip amount: seguida de un espacio y el valor de tu variable tip.
tip = running_total * 0.25
print(f'Tip amount: {tip}')

#6. Usa el operador += para sumar el valor de tip a tu running_total. Finalmente, usa print() para mostrar la cadena Total with tip: seguida de un espacio y el valor de running_total.
running_total += tip
print(f'Total with tip: {running_total}')

#7. Crea una variable llamada final_bill y asígnale el resultado de dividir running_total entre num_of_friends. Finalmente, usa la función print() para mostrar la cadena Bill per person: seguida de un espacio y el valor de final_bill.
final_bill = running_total / num_of_friends
print(f'Bill per person: {final_bill}')

#8. Usa la función round() para redondear final_bill a dos decimales y asigna el resultado a una nueva variable llamada each_pays. Finalmente, usa print() para mostrar la cadena Each person pays: seguida de un espacio y tu variable each_pays.
each_pays = round(final_bill, 2)
print(f'Each person pays: {each_pays}')

#---------------------------------------------------------------
print('------------------------------------------------------')

#Taller 4:
print('Taller 4:')

















