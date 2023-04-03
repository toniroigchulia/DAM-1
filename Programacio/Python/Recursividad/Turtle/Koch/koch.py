from turtle import *


def koch(level, size):
    
    if level == 0:
        forward(size)
        return
    else:
        for angle in [0, 60, -120, 60]:
            left(angle)
            koch(level-1, size/3)
 
  
level = 4
size = 600

speed(0)
penup()
goto(-300, 150)
pendown()
hideturtle()

koch(level, size)
right(120)
koch(level, size)
right(120)
koch(level, size)

exitonclick()