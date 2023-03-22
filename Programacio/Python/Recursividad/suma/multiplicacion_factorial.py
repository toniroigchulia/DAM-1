def sumaFactorial(num):
    
    if num == 0:
    
        return 0
        
    else:
        
        return num + sumaFactorial(num - 1)
        
def multiplicacionFactorial(num):

    if num == 0:
    
        return 1
        
    else:
    
        return num * multiplicacionFactorial(num - 1)
        

numero = 8

print("La suma factorial de", numero, "es", sumaFactorial(numero),".\n")
print("La multiplicacion factorial de", numero, "es", multiplicacionFactorial(numero), ".\n")