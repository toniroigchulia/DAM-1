import re
import random

#FUNCIONES

def dicc_create(file, num_clave):

    dicc = {}
    
    for line in file:
    
        linesplit = line.split()
    
        num_words = len(linesplit)
    
    return dicc
        


def sentence_creator(num_sent, dicc_chain, num_clave):

    print("\n","=====Frases=====","\n")



def start_sentence_key(num_clave, index, word):
    num_clave = num_clave - index
    
    start_array = []
    while num_clave > 0:
        
        start_array.append("<<START>>")
        
    if index != 0: 
        start_array.append("<<" + word + ">>")
    
    start_key = array_a_string(start_array)
    return start_key
   
def array_a_string(array):

    array_to_string = ' '.join([str(elem) for elem in array])
        
    return array_to_string



def sentence_num_creation():
    
    x = True
    while x == True:
        try:
            sentence_num_creation = int(input("Cuantas frases quieres generar? "))
            x = False
        except:
            print("\n","!!!!!Debes de ingresar un numero valido!!!!\n")
    
    return sentence_num_creation

def num_clave_creation():

    x = True
    while x == True:
        try:
            num_clave_creation = int(input("Que cantidad de palabras quieres usar como clave? "))
            x = False
        except:
            print("\n","!!!!!Debes de ingresar un numero valido!!!!\n")
    
    return num_clave_creation


def file_seleccion():

    print("==Archivos disponibles==\n -star_wars.txt \n -frases_informatica.txt \n -sentence_list_432.txt \n")
    
    x == True
    while x == True:
        try:
            input_file = input("Que archivo quieres abrir? ")
            x = False
        except:
            print("!!!El nombre del archivo no es correcto!!!","\n")
    print("\n")
            
    file_clear(input_file)
    
    file = open("text.txt", "r")

    return file

def file_clear(file_to_clear):
    my_file_to_clear = open(file_to_clear).read()
    my_file_cleared = re.sub('[^a-zA-Z0-9\s\n]', ' ', my_file_to_clear)
    open("text.txt" , 'w').write(my_file_cleared)


#PROGRAMA PRINCIPAL

file_to_use = file_seleccion()

num_sentences = sentence_num_creation()

num_clave = num_clave_creation()


chain = dicc_create(file_to_use, num_clave)

sentence_creator(num_sentences, chain, num_clave)


print(chain)

print("\n")
file_to_use.close()