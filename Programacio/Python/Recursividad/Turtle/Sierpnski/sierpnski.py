from turtle import *

def sierpinski(level, size):
    
    if (level == 0):
        return
    
    sierpinski(level-1,size/2)
    forward(size)
    right(120)
    
    sierpinski(level-1,size/2)
    forward(size)
    right(120)
    
    sierpinski(level-1,size/2)
    forward(size)
    right(120)
     
     
speed(0)
penup()
goto(-300, 200)
pendown()
level = 7
size = 300
    
sierpinski(level, size)
    
exitonclick()