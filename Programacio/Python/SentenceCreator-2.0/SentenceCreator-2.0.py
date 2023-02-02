import re
import random

#FUNCIONES

#Crear diccionario
def dicc_create(file, num_clave):

    dicc = {}
    dicc[initial_key_creator(num_clave)] = []
    
    for line in file:
    
        linesplit = line.split()
        
        x = dicc[initial_key_creator(num_clave)]       
        x.append(linesplit[0])       
        dicc[initial_key_creator(num_clave)] = x
        
        num_words = len(linesplit)
        
        for index in range (num_words):
            
            words = []
            index_clave = num_clave + index
            while index_clave > index:
                try:
                    words.append(linesplit[index_clave - 1])
                except:
                    words.append("<<END>>")
                index_clave = index_clave - 1
            words.reverse()
            print(words)
            
            
            if num_clave > index:
            
                if not key_creator(num_clave, index, words) in dicc:
                
                    try:
                        dicc[key_creator(num_clave, index, words)] = [linesplit[index]]
                    except:
                        pass
                        
                else:
                    
                    x = dicc[key_creator(num_clave, index, words)]
                    try:
                        x.append(linesplit[index])
                    except:
                        x.append("<<END>>")
                    dicc[key_creator(num_clave, index, words)] = x

    return dicc
        
#Creador de frases
def sentence_creator(num_sent, dicc_chain, num_clave):

    print("\n","=====Frases=====","\n")

#Clave inicial
def initial_key_creator(num_clave):
        
        start_array = []
        while num_clave > 0:
        
            start_array.append("<<START>>")
            num_clave = num_clave - 1
        
        start_key = array_a_string(start_array)
        return start_key
               
#Creador de claves
def key_creator(num_clave, index, words):
    key = []
    
    if (num_clave - 1) > index:
        while (num_clave - 1) > 0:
            key.append("<<Start>>")
            num_clave =- 1
        key.append("<<"+words[index]+">>")
        
    key = array_a_string(key)
    return key

#Cambiar una array a string
def array_a_string(array):

    array_to_string = ' '.join([str(elem) for elem in array])
        
    return array_to_string

#Cantidad de frases que quieres crear
def sentence_num_creation():
    
    x = True
    while x == True:
        try:
            sentence_num_creation = int(input("Cuantas frases quieres generar? "))
            x = False
        except:
            print("\n","!!!!!Debes de ingresar un numero valido!!!!\n")
    
    return sentence_num_creation

#Selecion de cantidad de palabras clave
def num_clave_creation():

    x = True
    while x == True:
        try:
            num_clave_creation = int(input("Que cantidad de palabras quieres usar como clave? "))
            x = False
        except:
            print("\n","!!!!!Debes de ingresar un numero valido!!!!\n")
    
    return num_clave_creation

#Elejir el archivo deseado
def file_seleccion():

    print("==Archivos disponibles==\n -star_wars.txt \n -frases_informatica.txt \n -sentence_list_432.txt \n")
    
    x = True
    while x == True:
        try:
            input_file = input("Que archivo quieres abrir? ")
            file_clear(input_file)
            file = open("text.txt", "r")
            x = False
        except:
            print("!!!El nombre del archivo no es correcto!!!","\n")
    print("\n")
            
    

    return file

#Depurar el archivo de caracteres especiales
def file_clear(file_to_clear):
    my_file_to_clear = open(file_to_clear).read()
    my_file_cleared = re.sub('[^a-zA-Z0-9\s\n]', ' ', my_file_to_clear)
    open("text.txt" , 'w').write(my_file_cleared)


#PROGRAMA PRINCIPAL

file_to_use = file_seleccion()

num_sentences = sentence_num_creation()

num_clave = num_clave_creation()


chain = dicc_create(file_to_use, num_clave)

#sentence_creator(num_sentences, chain, num_clave)


print(chain)

print("\n")
file_to_use.close()