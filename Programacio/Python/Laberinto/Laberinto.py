from colorama import init, Back, Fore
import os
init(autoreset=True)

##Funciones
def print_maze(maze):
    os.system("cls")
    print("\n")
            
    for x in range (len(maze)):
        for y in range(len(maze[0])):
            if maze[x][y] == " w ":
                print(Fore.RED + maze[x][y], end = " ")
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
            else:
                print(maze[x][y])
                
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

def move(actual_position):
#Primer Intento Movimiento 
    #Arriba
    try:
        if maze[actual_position[0] - 1][actual_position[1]] == " c ":
            pos = [actual_position[0] - 1, actual_position[1]]
            maze[actual_position[0] - 1][actual_position[1]] = " v "
            return pos
    except:
        pass
     
    #Derecha
    try:
        if maze[actual_position[0]][actual_position[1] + 1] == " c ":
            pos = [actual_position[0], actual_position[1] + 1]
            maze[actual_position[0]][actual_position[1] + 1] = " v "
            return pos
    except:
        pass
    
    #Abajo
    try:
        if maze[actual_position[0] + 1][actual_position[1]] == " c ":
            pos = [actual_position[0] + 1, actual_position[1]]
            maze[actual_position[0] + 1][actual_position[1]] = " v "
            return pos
    except:
        pass

    #Izquierda
    try:
        if maze[actual_position[0]][actual_position[1] - 1] == " c ":
            pos = [actual_position[0], actual_position[1] - 1]
            maze[actual_position[0]][actual_position[1] - 1] = " v "
            return pos
    except:
        pass

#Si no hay caminos libres
    #Arriba
    try:
        if maze[actual_position[0] - 1][actual_position[1]] == " v ":
            pos = [actual_position[0] - 1, actual_position[1]]
            maze[actual_position[0]][actual_position[1]] = " n "
            return pos
    except:
        pass
        
    #Derecha
    try:
        if maze[actual_position[0]][actual_position[1] + 1] == " v ":
            pos = [actual_position[0], actual_position[1] + 1]
            maze[actual_position[0]][actual_position[1]] = " n "
            return pos
    except:
        pass
        
    #Abajo
    try:
        if maze[actual_position[0] + 1][actual_position[1]] == " v ":
            pos = [actual_position[0] + 1, actual_position[1]]
            maze[actual_position[0]][actual_position[1]] = " n "
            return pos
    except:
        pass
    
    
    #Izquierda
    try:
        if maze[actual_position[0]][actual_position[1] - 1] == " v ":
            pos = [actual_position[0], actual_position[1] - 1]
            maze[actual_position[0]][actual_position[1]] = " n "
            return pos
    except:
        pass


        

##Laberinto
maze = [[" w ", " S ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " c ", " w "],
        [" w ", " c ", " w ", " c ", " w ", " c ", " c ", " c ", " c ", " c ", " w "],
        [" w ", " c ", " w ", " c ", " w ", " c ", " w ", " w ", " c ", " w ", " w "],
        [" w ", " c ", " w ", " c ", " w ", " c ", " w ", " c ", " c ", " c ", " c "],
        [" w ", " c ", " w ", " c ", " c ", " c ", " w ", " c ", " w ", " w ", " c "],
        [" E ", " c ", " c ", " c ", " w ", " c ", " w ", " c ", " w ", " c ", " c "],
        [" w ", " w ", " w ", " c ", " w ", " c ", " w ", " c ", " w ", " w ", " w "],
        [" w ", " w ", " c ", " c ", " w ", " c ", " w ", " c ", " c ", " c ", " w "],
        [" w ", " c ", " c ", " w ", " w ", " c ", " w ", " w ", " w ", " c ", " w "],
        [" w ", " w ", " w ", " w ", " c ", " c ", " c ", " c ", " w ", " c ", " w "],
        [" w ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " w "],]

##Main
maze_exit = exit_position(maze)

actual_position = start_position(maze)

exit = False
while exit == False:
    
        
    actual_position = move(actual_position)
    print_maze(maze)
    
    user_input = int(input())
    
    if user_input == 1:
        exit = True
    else:
        pass