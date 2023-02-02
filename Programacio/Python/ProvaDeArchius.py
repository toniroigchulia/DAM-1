import re
import random

#Leer frases
#Usar split() para separar las frases en sus palabras en una lista
#Meter todas esas palabras en un diccionario como key
#Y como valor metemos una lista metemos todas las palabras que van despues de esta(repetidas incluidas)"""

#Definicion de funciones

def file_clear(file_to_clear):

    my_file = open(".\Programacio\Python\Assets\ProvadeArchiusAssets" +  file_to_clear).read()
    my_file_clear = re.sub('[^a-zA-Z0-9\s\n]', ' ', my_file)
    open('.\Programacio\Python\Assets\ProvadeArchiusAssets' + file_to_clear , 'w').write(my_file_clear)
    
def word_extract(file):

    for line in file:
        
        linesplit = line.split()
        
        primerapalabra.append(linesplit[0])
        ultimapalabra.append(linesplit[-1])
        
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

    
def sentence_creator(num, primerapalabraarray, ultimapalabraarray, dicc):
    
    while num >= 0:
        
        sentence = []
        
        clave = (random.choice(primerapalabraarray))
        
        sentence.append(clave)
        
        seguir = True
        while seguir == True:
            
            
            clave = (random.choice(dicc[clave]))
            
            if clave in ultimapalabraarray:
            
                sentence.append(clave)
                
                # num = randint(1,2)
                
                # if num == 1:
                
                #     seguir = False
                    
                # else:
                #     pass
                
            else:
            
                sentence.append(clave)
        
        
        print(array_a_string(sentence))
        
        num = num - 1
    
def array_a_string(array):

    arrayastring = ' '.join([str(elem) for elem in array])
        
    return arrayastring

#Programa principal

    #DeclaracionES
dicc = {}

primerapalabra = []

ultimapalabra = []


    #Preguntamos que archivo quiere abrir el usuario
print("==Archivos disponibles==\n -\star_wars.txt \n -\\frases_informatica.txt \n -\sentence_list_432.txt \n")
user_file = input("Que archivo quieres abrir? ")
print("\n")
sentence_num_creation = int(input("Cuantas frases quieres generar? "))
print("\n")
print("==Frases==\n")

    #Depuramos el archivo de caracteres especiales
file_clear(user_file)

    #Una vez depurado lo volvemos a abrir para su uso
file = open(".\Programacio\Python\Assets\ProvadeArchiusAssets" + user_file, "r")

    #Extraemos las palabras del texto y formamos un diccionario con ellas
word_extract(file)

print("=============")
print(primerapalabra)
print("===================")
    #Creamos las nuevas frases
sentence_creator(sentence_num_creation, primerapalabra, ultimapalabra, dicc)

#print(dicc)
#print(primerapalabra)

file.close()