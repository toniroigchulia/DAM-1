import os
import colorama as col

class Tree:
    def __init__(self, root_path) -> None:
        
        self.__root_path = root_path
        self.__max_depth:int = None
        self.__masc = None
        self.__file_color = col.Fore.GREEN
        self.__dir_color = col.Fore.BLUE
  
    def __show(self, fullpath:str, max_depth:int, actual_depth:int = 0) -> None:
    
        if actual_depth > max_depth:
            
            return
            
        else:
        
            sub_items:list[os.DirEntry] = list(os.scandir(fullpath))
        
        sub_items.sort(key=self.sort_key)
        
        for item in sub_items:
            
            if item.is_dir():
                print("  "*actual_depth + col.Fore.BLUE + item.name)
                
                self.__show(item.path, max_depth, actual_depth+1)
                
            else:
                print("  "*actual_depth + col.Fore.GREEN + item.name)
    
    def set_file_color(self, file_color):
        self.__file_color = file_color
        
    def set_dir_color(self, dir_color):
        self.__dir_color = dir_color
        
    def set_masc(self, masc):
        self.__masc = masc
        
    def set_max_depth(self, max_depth):
        self.__max_depth = max_depth

    
    def sort_key(self, item:os.DirEntry):
        
        return "A" if item.is_file() else "B"
    
    
    def show(self):
        self.__show(self.__root_path, self.__max_depth, 0)
        



def main() -> None:

    tree_1 = Tree("C:\\Users\\Toni\\Documents\\1r DAM")
    tree_1.set_max_depth(1)
    tree_1.show()

if __name__ == "__main__":
    main()