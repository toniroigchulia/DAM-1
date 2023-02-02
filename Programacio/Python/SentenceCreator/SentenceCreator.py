import re
import random

#FUNCIONES

    #Funcion para depurar el archivo
def file_clear(file_to_clear):

    my_file_to_clear = open(file_to_clear).read()
    my_file_cleared = re.sub('[^a-zA-Z0-9\s\n]', ' ', my_file_to_clear)
    open("text.txt" , 'w').write(my_file_cleared)
    
    #Funcion para extraer las palabras y crear un diccionario con ellas
def cadena_markov(file):

    for line in file:
        
        linesplit = line.split()
        
        primera_palabra.append(linesplit[0])
        ultima_palabra.append(linesplit[-1])
        
        words = len(linesplit)
        
        for index in range (words):
    
            if not linesplit[index] in dicc:
            
                #<3
                if  index == (words - 1):
                    dicc[linesplit[index]] = []
                else:
                    dicc[linesplit[index]] = [linesplit[index + 1]]
                 
            else:
                
                if index == (words - 1):
                    pass
                else:
                    k = dicc[linesplit[index]]
                
                    k.append(linesplit[index + 1])
                
                    dicc[linesplit[index]] = k

    #Funcion para crear las nuevas frases
def sentence_creator(num, primera_palabra_array, ultima_palabra_array, dicc):
    
    print("\n","=====Frases=====","\n")
    
    while num > 0:
        
        sentence = []
        
        clave = (random.choice(primera_palabra_array))
        
        sentence.append(clave)
        
        seguir = True
        while seguir == True:
            
            
            clave = (random.choice(dicc[clave]))
            
            
            if clave in ultima_palabra_array:
            
                sentence.append(clave)
                
                a = random.randrange(start=0, stop=1)
                
                if a == 0:
                    seguir = False
                else: 
                    pass
                
            else:
            
                sentence.append(clave)
        
        
        print(array_a_string(sentence))
        
        num = num - 1
    
    #Funcion para transformar de array a string
def array_a_string(array):

    arrayastring = ' '.join([str(elem) for elem in array])
        
    return arrayastring

    #Funcion para preguntar cuantas frases tenemos que crear
def sentence_num_creation():
    
    z = True
    while z == True:
        try:
            sentence_num_creation = int(input("Cuantas frases quieres generar? "))
            z = False
        except:
            print("\n","!!!!!Debes de ingresar un numero valido!!!!\n")
    
    return sentence_num_creation
    
    #Funcion para preguntar que archivo queremos usar
def file_seleccion():

    print("==Archivos disponibles==\n -star_wars.txt \n -frases_informatica.txt \n -sentence_list_432.txt \n")
    input_file = input("Que archivo quieres abrir? ")
    print("\n")

    return input_file


#PROGRAMA PRINCIPAL


    #Declaraciones
dicc = {}

primera_palabra = []

ultima_palabra = []


    #Preguntamos que archivo vamos a usar y eliminamos los caracteres especiales
x = True
while x == True:
    try:
        user_file = file_seleccion()
        file_clear(user_file)
        x = False
    except:
        print("!!!El nombre del archivo no es correcto!!!","\n")


    #Pregunto cuantas frases quiere crear el usuario
numero_frases = sentence_num_creation()


    #Una vez depurado lo volvemos a abrir para su uso
file = open("text.txt", "r")


    #Extraemos las palabras del texto y formamos un diccionario con ellas
cadena_markov(file)


    #Creamos las nuevas frases
sentence_creator(numero_frases, primera_palabra, ultima_palabra, dicc)

print("\n")
file.close()