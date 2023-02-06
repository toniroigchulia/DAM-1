import re
import random

### FUNCIONES###


def dicc_create(file, num_clave):

    dicc = {}
    dicc[initial_key_creator(num_clave)] = []

    for line in file:

        linesplit = line.split()

        x = dicc[initial_key_creator(num_clave)]
        x.append(linesplit[0])
        dicc[initial_key_creator(num_clave)] = x

        num_words = len(linesplit)

        for index in range(num_words):

            key = key_creator(num_clave, index, linesplit)

            if not key in dicc:

                try:
                    dicc[key] = [linesplit[index + 1]]
                except:
                    dicc[key] = ["<<END>>"]

            else:

                x = dicc[key]

                try:
                    x.append(linesplit[index + 1])
                except:
                    x.append("<<END>>")

                dicc[key] = x

    return dicc


def sentence_creator(num_sent, dicc_chain, num_clave):

    print("\n", "=====Frases=====", "\n")

    sentence_dicc = {}

    while num_sent > 0:

        sentence = []

        clave = initial_key_creator(num_clave).split()

        string_clave = array_a_string(clave)
        next_word = (random.choice(dicc_chain[string_clave]))
        sentence.append(next_word)

        x = True
        while x == True:

            clave.append("<<"+next_word+">>")
            clave.pop(0)

            string_clave = array_a_string(clave)
            next_word = (random.choice(dicc_chain[string_clave]))

            if next_word == "<<END>>":

                x = False

            else:

                sentence.append(next_word)

        string_sentence = array_a_string(sentence)
        if not string_sentence in sentence_dicc:

            sentence_dicc[string_sentence] = [[]]
            print(string_sentence)

        else:
            pass

        num_sent = num_sent - 1


def initial_key_creator(num_clave):

    start_array = []
    while num_clave > 0:

        start_array.append("<<START>>")
        num_clave = num_clave - 1

    start_key = array_a_string(start_array)
    return start_key


def key_creator(num_clave, index, linesplit):

    key = []

    x = index
    z = num_clave
    y = True
    while (z > 0) & (y == True):

        key.append("<<"+linesplit[x]+">>")

        if x == 0:
            y = False

        z = z - 1
        x = x - 1

    index_clave = num_clave - (index + 1)
    while index_clave > 0:

        key.append("<<START>>")
        index_clave = index_clave - 1

    key.reverse()
    key = array_a_string(key)
    return key


def array_a_string(array):

    array_to_string = ' '.join([str(elem) for elem in array])

    return array_to_string


def sentence_num_creation():

    x = True
    while x == True:
        try:
            sentence_num_creation = int(
                input("\n"+"Cuantas frases quieres generar? "))
            x = False
        except:
            print("\n", "!!!!!Debes de ingresar un numero valido!!!!\n")

    return sentence_num_creation


def num_clave_creation():

    x = True
    while x == True:
        try:
            num_clave_creation = int(input("\n"+"Que cantidad de palabras quieres usar como clave? "))
            x = False
        except:
            print("\n", "!!!!!Debes de ingresar un numero valido!!!!\n")

    return num_clave_creation


def file_seleccion():

    print("==Archivos disponibles==\n -star_wars.txt \n -frases_informatica.txt \n")

    x = True
    while x == True:
        try:
            input_file = input("Que archivo quieres abrir? ")
            file_clear(input_file)
            file = open("text.txt", "r")
            x = False
        except:
            print("!!!El nombre del archivo no es correcto!!!", "\n")

    return file


def file_clear(file_to_clear):
    my_file_to_clear = open(file_to_clear).read()
    my_file_cleared = re.sub('[^a-zA-Z0-9\s\n]', ' ', my_file_to_clear)
    open("text.txt", 'w').write(my_file_cleared)


### PROGRAMA PRINCIPAL###
file_to_use = file_seleccion()

num_sentences = sentence_num_creation()

num_clave = num_clave_creation()

chain = dicc_create(file_to_use, num_clave)

sentence_creator(num_sentences, chain, num_clave)

print("\n")
file_to_use.close()