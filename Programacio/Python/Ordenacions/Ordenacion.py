import random
from datetime import datetime

##Crear una array aleatoria (tanto de tamanyo como de contenido) y ordenar la array X numero de veces usando 3 metodos de ordenacion distintos y registrar sus tiempos.

#Insercion
def ord_Insercion(A):

    time_start = datetime.now()
    
    for i in range(len(A)):
            for j in range(i ,0 ,-1):
                if(A[j-1] > A[j]):
                    aux=A[j]
                    A[j]=A[j-1]
                    A[j-1]=aux
                    
    time_finis = datetime.now()
    time = time_finis - time_start
    
    return time


#Seleccion
def ord_Seleccion(A):
    
    time_start = datetime.now()

    for i in range(len(A)):
        minimo=i
        for j in range(i,len(A)):
            if(A[j] < A[minimo]):
                minimo=j
        if(minimo != i):
            aux=A[i]
            A[i]=A[minimo]
            A[minimo]=aux
            
    time_finis = datetime.now()
    time = time_finis - time_start
    
    return time
    
    
#Burbuja
def ord_Burbuja(A):

    time_start = datetime.now()

    for i in range(1,len(A)):
        for j in range(0,len(A)-i):
            if(A[j+1] < A[j]):
                aux=A[j]
                A[j]=A[j+1]
                A[j+1]=aux
                
    time_finis = datetime.now()
    time = time_finis - time_start
    
    return time


tamanyoarray = int(input("\n" + "-De que tamaño debe de ser la array? "))

numerodetest = int(input("\n" + "-Cuantas veces se tiene que repetir el test? "))

  
matriz = []
for n in range(numerodetest):
    
    array = []
    
    for i in range(tamanyoarray):

        array.append(random.randint(0,9))
    
    matriz.insert(n, array)
    array.clear

arraySeleccion = []
arrayBurbuja = []
arrayInsercion = []

for l in range (numerodetest):
    
    arraySeleccion.append(ord_Seleccion(matriz[l]))
    arrayBurbuja.append(ord_Burbuja(matriz[l]))
    arrayInsercion.append(ord_Insercion(matriz[l]))
    
print("\n","Tiempo del metodo de Seleccion: ",arraySeleccion)
print("\n""Tiempo del metodo Burbuja: ",arrayBurbuja)
print("\n""Tiempo del metodo de Insercion: ",arrayInsercion)