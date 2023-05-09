def ord1():

    print("ord1")
    
def ord2():

    print("ord2")

def ord3():

    print("ord3")
    
def ord4():

    print("ord4")
    
def ord5():

    print("ord5")


def menu():

    lista = [ord1, ord2, ord3, ord4, ord5]
    
    elejir = 1

    while elejir >= 1 and elejir <= 5:
        
        print("Que ordenacion quieres usar","\n")
        for x in range (len(lista)):
        
            print("Ordenacion ", x+1)
        
        print("Salir -1")
        
        elejir = input()
        
        try:
            
            elejir = int(elejir)
            
        except:
        
            print("El numero elejido no es valido")
            
            exit()
        
        if elejir >= 1 and elejir <= 5:
        
            lista[elejir - 1]()
            
menu()