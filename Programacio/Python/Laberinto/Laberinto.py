import random

def init_maze(width, height):
    maze = []
    for i in range(0, height):
        line = []
        for j in range(0, width):
            line.append(' u ')
        maze.append(line)
    return maze
    
def print_maze(maze):
    for i in range(0, len(maze)):
        for j in range(0, len(maze[0])):
            if maze[i][j] == ' u ':
                print(f'{maze[i][j]}', end="")
            elif maze[i][j] == ' c ':
                print(f'{maze[i][j]}', end="")
            else:
                print(f'{maze[i][j]}', end="")
        print('\n')
        
height = 10
width = 15
      
maze = init_maze(width, height)

print_maze(maze)
