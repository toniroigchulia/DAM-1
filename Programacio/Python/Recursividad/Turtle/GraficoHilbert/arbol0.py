from turtle import *
from colorsys import *


def tree(level=4,angle=45,size=100):
    # Condición de parada de recursividad
    if(level == 0):
        return

    # Pintar un rama
    pendown()
    left(angle)
    forward(size)
    
    # Crear 2 subárboles
    tree(level-1, angle, size)
    tree(level-1, -angle, size)

    # Volver al punto inicial del árbol
    forward(-size)
    left(-angle)




pendown()
goto(0,-200)

pencolor(hsv_to_rgb(0.3,1,1))
pensize(5)
bgcolor("black")
speed(0)

angle = 45
left(90-angle)
tree(5,angle, 50)

exitonclick()