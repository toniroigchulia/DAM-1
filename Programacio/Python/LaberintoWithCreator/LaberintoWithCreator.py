## Imports
import random
import time
import os
from colorama import init
from colorama import Fore, Back, Style

##Funciones PROPIAS
def print_maze(maze):
    os.system("cls")
    print("\n")
            
    for x in range (len(maze)):
        for y in range(len(maze[0])):
        
            if maze[x][y] == " w ":
                print(Back.RED + maze[x][y] + Fore.RESET + Back.RESET, end = " ")
                
            elif maze[x][y] == " c ":
                print(Fore.GREEN + maze[x][y] + Fore.RESET, end = " ")
                
            elif maze[x][y] == " v ":
                print(Fore.YELLOW + maze[x][y] + Fore.RESET, end = " ")
                
            elif maze[x][y] == " n ":
                print(Fore.LIGHTBLACK_EX + maze[x][y] + Fore.RESET, end = " ")
                
            elif maze[x][y] == " S ":
                print(Fore.BLUE + maze[x][y] + Fore.RESET, end = " ")
                
            elif maze[x][y] == " E ":
                print(Fore.MAGENTA + maze[x][y] + Fore.RESET, end = " ")
                
            elif maze[x][y] == " A ":
                print(Fore.WHITE + maze[x][y] + Fore.RESET, end = " ")
          
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

#Si no hay camino inexplorado volvemos atras y marcamos el camino como sin salida
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

########################
def surroundingCells(rand_wall):
	s_cells = 0
	if (maze[rand_wall[0]-1][rand_wall[1]] == ' c '):
		s_cells += 1
	if (maze[rand_wall[0]+1][rand_wall[1]] == ' c '):
		s_cells += 1
	if (maze[rand_wall[0]][rand_wall[1]-1] == ' c '):
		s_cells +=1
	if (maze[rand_wall[0]][rand_wall[1]+1] == ' c '):
		s_cells += 1

	return s_cells

## Main code
# Init variables
wall = ' w '
cell = ' c '
unvisited = ' u '
height = 11
width = 27
maze = []

# Initialize colorama
init()

# Denote all cells as unvisited
for i in range(0, height):
	line = []
	for j in range(0, width):
		line.append(unvisited)
	maze.append(line)

# Randomize starting point and set it a cell
starting_height = int(random.random()*height)
starting_width = int(random.random()*width)
if (starting_height == 0):
	starting_height += 1
if (starting_height == height-1):
	starting_height -= 1
if (starting_width == 0):
	starting_width += 1
if (starting_width == width-1):
	starting_width -= 1

# Mark it as cell and add surrounding walls to the list
maze[starting_height][starting_width] = cell
walls = []
walls.append([starting_height - 1, starting_width])
walls.append([starting_height, starting_width - 1])
walls.append([starting_height, starting_width + 1])
walls.append([starting_height + 1, starting_width])

# Denote walls in maze
maze[starting_height-1][starting_width] = ' w '
maze[starting_height][starting_width - 1] = ' w '
maze[starting_height][starting_width + 1] = ' w '
maze[starting_height + 1][starting_width] = ' w '


while (walls):
	# Pick a random wall
	rand_wall = walls[int(random.random()*len(walls))-1]

	# Check if it is a left wall
	if (rand_wall[1] != 0):
		if (maze[rand_wall[0]][rand_wall[1]-1] == ' u ' and maze[rand_wall[0]][rand_wall[1]+1] == ' c '):
			# Find the number of surrounding cells
			s_cells = surroundingCells(rand_wall)

			if (s_cells < 2):
				# Denote the new path
				maze[rand_wall[0]][rand_wall[1]] = ' c '

				# Mark the new walls
				# Upper cell
				if (rand_wall[0] != 0):
					if (maze[rand_wall[0]-1][rand_wall[1]] != ' c '):
						maze[rand_wall[0]-1][rand_wall[1]] = ' w '
					if ([rand_wall[0]-1, rand_wall[1]] not in walls):
						walls.append([rand_wall[0]-1, rand_wall[1]])


				# Bottom cell
				if (rand_wall[0] != height-1):
					if (maze[rand_wall[0]+1][rand_wall[1]] != ' c '):
						maze[rand_wall[0]+1][rand_wall[1]] = ' w '
					if ([rand_wall[0]+1, rand_wall[1]] not in walls):
						walls.append([rand_wall[0]+1, rand_wall[1]])

				# Leftmost cell
				if (rand_wall[1] != 0):	
					if (maze[rand_wall[0]][rand_wall[1]-1] != ' c '):
						maze[rand_wall[0]][rand_wall[1]-1] = ' w '
					if ([rand_wall[0], rand_wall[1]-1] not in walls):
						walls.append([rand_wall[0], rand_wall[1]-1])
			

			# Delete wall
			for wall in walls:
				if (wall[0] == rand_wall[0] and wall[1] == rand_wall[1]):
					walls.remove(wall)

			continue

	# Check if it is an upper wall
	if (rand_wall[0] != 0):
		if (maze[rand_wall[0]-1][rand_wall[1]] == ' u ' and maze[rand_wall[0]+1][rand_wall[1]] == ' c '):

			s_cells = surroundingCells(rand_wall)
			if (s_cells < 2):
				# Denote the new path
				maze[rand_wall[0]][rand_wall[1]] = ' c '

				# Mark the new walls
				# Upper cell
				if (rand_wall[0] != 0):
					if (maze[rand_wall[0]-1][rand_wall[1]] != ' c '):
						maze[rand_wall[0]-1][rand_wall[1]] = ' w '
					if ([rand_wall[0]-1, rand_wall[1]] not in walls):
						walls.append([rand_wall[0]-1, rand_wall[1]])

				# Leftmost cell
				if (rand_wall[1] != 0):
					if (maze[rand_wall[0]][rand_wall[1]-1] != ' c '):
						maze[rand_wall[0]][rand_wall[1]-1] = ' w '
					if ([rand_wall[0], rand_wall[1]-1] not in walls):
						walls.append([rand_wall[0], rand_wall[1]-1])

				# Rightmost cell
				if (rand_wall[1] != width-1):
					if (maze[rand_wall[0]][rand_wall[1]+1] != ' c '):
						maze[rand_wall[0]][rand_wall[1]+1] = ' w '
					if ([rand_wall[0], rand_wall[1]+1] not in walls):
						walls.append([rand_wall[0], rand_wall[1]+1])

			# Delete wall
			for wall in walls:
				if (wall[0] == rand_wall[0] and wall[1] == rand_wall[1]):
					walls.remove(wall)

			continue

	# Check the bottom wall
	if (rand_wall[0] != height-1):
		if (maze[rand_wall[0]+1][rand_wall[1]] == ' u ' and maze[rand_wall[0]-1][rand_wall[1]] == ' c '):

			s_cells = surroundingCells(rand_wall)
			if (s_cells < 2):
				# Denote the new path
				maze[rand_wall[0]][rand_wall[1]] = ' c '

				# Mark the new walls
				if (rand_wall[0] != height-1):
					if (maze[rand_wall[0]+1][rand_wall[1]] != ' c '):
						maze[rand_wall[0]+1][rand_wall[1]] = ' w '
					if ([rand_wall[0]+1, rand_wall[1]] not in walls):
						walls.append([rand_wall[0]+1, rand_wall[1]])
				if (rand_wall[1] != 0):
					if (maze[rand_wall[0]][rand_wall[1]-1] != ' c '):
						maze[rand_wall[0]][rand_wall[1]-1] = ' w '
					if ([rand_wall[0], rand_wall[1]-1] not in walls):
						walls.append([rand_wall[0], rand_wall[1]-1])
				if (rand_wall[1] != width-1):
					if (maze[rand_wall[0]][rand_wall[1]+1] != ' c '):
						maze[rand_wall[0]][rand_wall[1]+1] = ' w '
					if ([rand_wall[0], rand_wall[1]+1] not in walls):
						walls.append([rand_wall[0], rand_wall[1]+1])

			# Delete wall
			for wall in walls:
				if (wall[0] == rand_wall[0] and wall[1] == rand_wall[1]):
					walls.remove(wall)


			continue

	# Check the right wall
	if (rand_wall[1] != width-1):
		if (maze[rand_wall[0]][rand_wall[1]+1] == ' u ' and maze[rand_wall[0]][rand_wall[1]-1] == ' c '):

			s_cells = surroundingCells(rand_wall)
			if (s_cells < 2):
				# Denote the new path
				maze[rand_wall[0]][rand_wall[1]] = ' c '

				# Mark the new walls
				if (rand_wall[1] != width-1):
					if (maze[rand_wall[0]][rand_wall[1]+1] != ' c '):
						maze[rand_wall[0]][rand_wall[1]+1] = ' w '
					if ([rand_wall[0], rand_wall[1]+1] not in walls):
						walls.append([rand_wall[0], rand_wall[1]+1])
				if (rand_wall[0] != height-1):
					if (maze[rand_wall[0]+1][rand_wall[1]] != ' c '):
						maze[rand_wall[0]+1][rand_wall[1]] = ' w '
					if ([rand_wall[0]+1, rand_wall[1]] not in walls):
						walls.append([rand_wall[0]+1, rand_wall[1]])
				if (rand_wall[0] != 0):	
					if (maze[rand_wall[0]-1][rand_wall[1]] != ' c '):
						maze[rand_wall[0]-1][rand_wall[1]] = ' w '
					if ([rand_wall[0]-1, rand_wall[1]] not in walls):
						walls.append([rand_wall[0]-1, rand_wall[1]])

			# Delete wall
			for wall in walls:
				if (wall[0] == rand_wall[0] and wall[1] == rand_wall[1]):
					walls.remove(wall)

			continue

	# Delete the wall from the list anyway
	for wall in walls:
		if (wall[0] == rand_wall[0] and wall[1] == rand_wall[1]):
			walls.remove(wall)
	

# Mark the remaining unvisited cells as walls
for i in range(0, height):
	for j in range(0, width):
		if (maze[i][j] == ' u '):
			maze[i][j] = ' w '

# Set entrance and exit
for i in range(0, width):
	if (maze[1][i] == ' c '):
		maze[0][i] = ' S '
		break

for i in range(width-1, 0, -1):
	if (maze[height-2][i] == ' c '):
		maze[height-1][i] = ' E '
		break
########################

##Main PROPIO
maze_exit = exit_position(maze)

actual_position = start_position(maze)

exit = False
while exit == False:
    
    if maze[actual_position[0]][actual_position[1]] == " S ":
        pass
    else:
        maze[actual_position[0]][actual_position[1]] = " v "
        
    actual_position = explore(actual_position)
    maze[actual_position[0]][actual_position[1]] = " A "

    if actual_position == maze_exit:
        exit = True
    else:
        pass
    
    print_maze(maze)