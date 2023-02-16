from colorama import init, Back, Fore
import os
import time
init(autoreset = True)

##Funciones
def print_maze(maze):
    os.system("cls")
    print("\n")
            
    for x in range (len(maze)):
        for y in range(len(maze[0])):
        
            if maze[x][y] == " w ":
                print(Back.RED + maze[x][y] + Fore.RESET + Back.RESET, end = " ")
                
            elif maze[x][y] == " c ":
                print(Fore.GREEN + maze[x][y], end = " ")
                
            elif maze[x][y] == " v ":
                print(Fore.YELLOW + maze[x][y], end = " ")
                
            elif maze[x][y] == " n ":
                print(Fore.LIGHTBLACK_EX + maze[x][y], end = " ")
                
            elif maze[x][y] == " S ":
                print(Fore.BLUE + maze[x][y], end = " ")
                
            elif maze[x][y] == " E ":
                print(Fore.MAGENTA + maze[x][y], end = " ")
                
            elif maze[x][y] == " A ":
                print(Fore.WHITE + maze[x][y], end = " ")
                
            elif maze[x][y] == " h ":
                print(Fore.LIGHTBLACK_EX + maze[x][y], end = " ")
                
        print("\n")
    
def start_position(maze):
    for x in range (len(maze)):
        for y in range(len(maze[0])):
            
            if maze[x][y] == " S ":
                
                pos = [x, y] 
                return pos
                
            else:
                pass

def exit_position(maze):

    for x in range (len(maze)):
        for y in range(len(maze[0])):
            
            if maze[x][y] == " E ":
                
                pos = [x, y] 
                return pos
                
            else:
                pass

def explore(actual_position, start):
#Buscamos la salida
    #Arriba
    try:
        if maze[actual_position[0] - 1][actual_position[1]] == " E ":
            pos = [actual_position[0] - 1, actual_position[1]]
            return pos       
    except:
        pass
     
    #Derecha
    try:
        if maze[actual_position[0]][actual_position[1] + 1] == " E ":
            pos = [actual_position[0], actual_position[1] + 1]
            return pos        
    except:
        pass
    
    #Abajo
    try:
        if maze[actual_position[0] + 1][actual_position[1]] == " E ":
            pos = [actual_position[0] + 1, actual_position[1]]
            return pos
    except:
        pass

    #Izquierda
    try:
        if maze[actual_position[0]][actual_position[1] - 1] == " E ":
            pos = [actual_position[0], actual_position[1] - 1]
            return pos
    except:
        pass

#Si no hay salida nos movemos a un camino inexplorado
    #Arriba
    try:
        if maze[actual_position[0] - 1][actual_position[1]] == " c ":
            pos = [actual_position[0] - 1, actual_position[1]]
            return pos       
    except:
        pass
     
    #Derecha
    try:
        if maze[actual_position[0]][actual_position[1] + 1] == " c ":
            pos = [actual_position[0], actual_position[1] + 1]
            return pos        
    except:
        pass
    
    #Abajo
    try:
        if maze[actual_position[0] + 1][actual_position[1]] == " c ":
            pos = [actual_position[0] + 1, actual_position[1]]
            return pos
    except:
        pass

    #Izquierda
    try:
        if maze[actual_position[0]][actual_position[1] - 1] == " c ":
            pos = [actual_position[0], actual_position[1] - 1]
            return pos
    except:
        pass
    
    
    maze[actual_position[0]][actual_position[1]] = " n "
    for x in range (len(maze)):
        for y in range(len(maze[0])):
            
            if maze[x][y] == " v ":
                
                maze[x][y] = " c "
                
            else:
                pass
    
    
    return start

# #Si no hay camino inexplorado volvemos atras y marcamos el camino como sin salida
#     #Arriba
#     try:
#         if maze[actual_position[0] - 1][actual_position[1]] == " v ":
#             pos = [actual_position[0] - 1, actual_position[1]]
#             maze[actual_position[0]][actual_position[1]] = " n "
#             return pos
#     except:
#         pass
        
#     #Derecha
#     try:
#         if maze[actual_position[0]][actual_position[1] + 1] == " v ":
#             pos = [actual_position[0], actual_position[1] + 1]
#             maze[actual_position[0]][actual_position[1]] = " n "
#             return pos
#     except:
#         pass
        
#     #Abajo
#     try:
#         if maze[actual_position[0] + 1][actual_position[1]] == " v ":
#             pos = [actual_position[0] + 1, actual_position[1]]
#             maze[actual_position[0]][actual_position[1]] = " n "
#             return pos
#     except:
#         pass
    
    
#     #Izquierda
#     try:
#         if maze[actual_position[0]][actual_position[1] - 1] == " v ":
#             pos = [actual_position[0], actual_position[1] - 1]
#             maze[actual_position[0]][actual_position[1]] = " n "
#             return pos
#     except:
#         pass
        
        
##Laberinto
maze = [[" w ", " S ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " w "],
        [" w ", " c ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " c ", " w ", " w "],
        [" w ", " c ", " w ", " c ", " w ", " c ", " c ", " c ", " c ", " c ", " w ", " w "],
        [" w ", " c ", " w ", " c ", " w ", " c ", " w ", " w ", " c ", " w ", " w ", " w "],
        [" w ", " c ", " c ", " c ", " w ", " c ", " w ", " c ", " c ", " c ", " c ", " w "],
        [" w ", " w ", " w ", " c ", " c ", " c ", " w ", " c ", " w ", " w ", " c ", " w "],
        [" E ", " c ", " c ", " c ", " w ", " c ", " w ", " c ", " w ", " c ", " c ", " w "],
        [" w ", " w ", " c ", " w ", " w ", " c ", " w ", " c ", " w ", " w ", " w ", " w "],
        [" w ", " w ", " c ", " w ", " w ", " c ", " w ", " c ", " c ", " c ", " w ", " w "],
        [" w ", " c ", " c ", " w ", " w ", " c ", " w ", " w ", " w ", " c ", " w ", " w "],
        [" w ", " w ", " c ", " c ", " c ", " c ", " c ", " w ", " w ", " c ", " w ", " w "],
        [" w ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " w "]]


##Main
maze_exit = exit_position(maze)

start = start_position(maze)

actual_position = start

exit = False
while exit == False:

    if maze[actual_position[0]][actual_position[1]] == " S ":
        pass
    else:
        maze[actual_position[0]][actual_position[1]] = " v "
        
    actual_position = explore(actual_position, start)
    maze[actual_position[0]][actual_position[1]] = " A "

    if actual_position == maze_exit:
        exit = True
    else:
        pass
    
    maze[start[0]][start[1]] = " S "
    print_maze(maze)

