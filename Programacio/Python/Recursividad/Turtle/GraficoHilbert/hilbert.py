from turtle import *

speed(0)

def hilbert(level, angle, size):
    
    if level == 0:
        return
    else:
        pass
       
 
    right(angle)
    hilbert(level-1, -angle, size)
    forward(size)
    
    left(angle)
    hilbert(level-1, angle, size)
    forward(size)
    

    hilbert(level-1, angle, size)

    
    left(angle)
    forward(size)
    hilbert(level-1, -angle, size)
    
    right(angle)
    
    
  
level = 10
angle = -90
size = 5

hilbert(level, angle, size)


exitonclick()