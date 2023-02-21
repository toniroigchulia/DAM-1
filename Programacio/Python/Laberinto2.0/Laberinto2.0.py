from colorama import init, Back, Fore
init(autoreset = True)

##Funciones
def print_maze(maze):
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

def explore(actual_position):
    
    #Posiciones para comprobar
    look = [[1,0],
            [0,1],
            [-1,0],
            [0,-1]]
    
    #Casillas para buscar
    search_for = [" E ", " c "] 
    
    #Usando las dos arrays anteriores se busca en las casillas cercanas y si se encuentra una casilla valida nos movemos a ella
    for y in search_for:
        for x in range (len(look)):
                
            if maze[actual_position[0] + (look[x][0])][actual_position[1] + (look[x][1])] == y:
                pos = [actual_position[0] + (look[x][0]), actual_position[1] + (look[x][1])]
                camino_salida.append(pos)
                return pos  
            else: pass
    
    #Si no se encuentra ninguna casilla valida volvemos hacia atras
    maze[actual_position[0]][actual_position[1]] = " n "
    camino_salida.pop(-1)
    pos = camino_salida[-1]
    return pos

##Laberinto
maze = [[" w ", " S ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " w "],
        [" w ", " c ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " c ", " w ", " w "],
        [" w ", " c ", " w ", " c ", " w ", " c ", " c ", " c ", " c ", " c ", " w ", " w "],
        [" w ", " c ", " w ", " c ", " w ", " c ", " c ", " c ", " c ", " w ", " w ", " w "],
        [" w ", " c ", " c ", " c ", " w ", " c ", " c ", " c ", " c ", " c ", " c ", " w "],
        [" w ", " w ", " c ", " c ", " c ", " c ", " c ", " c ", " w ", " w ", " c ", " w "],
        [" E ", " c ", " c ", " w ", " c ", " c ", " c ", " c ", " w ", " c ", " c ", " w "],
        [" w ", " w ", " c ", " w ", " w ", " c ", " w ", " c ", " w ", " w ", " w ", " w "],
        [" w ", " w ", " c ", " w ", " w ", " c ", " w ", " c ", " c ", " c ", " w ", " w "],
        [" w ", " c ", " c ", " w ", " w ", " c ", " w ", " w ", " w ", " c ", " c ", " w "],
        [" w ", " w ", " c ", " c ", " c ", " c ", " c ", " c ", " c ", " w ", " c ", " w "],
        [" w ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " w ", " w "]]

###Main
##Init variables
#Salida Laberinto
maze_exit = exit_position(maze)

#Entrada laberinto
maze_start = start_position(maze)
actual_position = maze_start

#Movimientos realizados
camino_salida = []
movimientos_realizados = []

##Bucle Principal
exit = False
while not exit:
    
    if not maze[actual_position[0]][actual_position[1]] == " S " or maze[actual_position[0]][actual_position[1]] == " E ":
        maze[actual_position[0]][actual_position[1]] = " v "
    else: pass
    
    actual_position = explore(actual_position)
    
    movimientos_realizados.append(actual_position)
    
    if actual_position == maze_exit:
        exit = True
    else:
        pass

    
print_maze(maze)
print("\n"+"Movimientos realizados para encontrar la salida: "+"\n",movimientos_realizados)
print("\n"+"Camino a la salida: "+"\n",camino_salida)