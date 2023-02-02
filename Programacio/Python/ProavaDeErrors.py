def fun1():
    print("fun_1 IN")
    fun2()
    print("fun_2 Out")
    
def fun2():
    print("fun_2 IN")
    fun3()
    print("fun_3 OUT")
    
def fun3():
    print("fun_3 IN")
    print(3/0)
    print("Do notinh")
    print("fun_3 OUT")
    
try:
    fun1()
except:
    print("peto")