from colorama import Fore, Back


class SuperTupla(tuple):
    def __new__(cls, initial_tuple: tuple = (), name: str = "No name", color: str = "Fore.White"):
            return super().__new__(cls, initial_tuple, name, color)
            
    def __init__(self, name: str = "No name", color: str = Fore.RESET):
    
        self.name: str = name
        self.color: str = color

    def __str__(self):
    
        str_to_show = "Tuple name: " + self.name + "\n"
        str_to_show += "=" * (len(str_to_show)-1) + "\n"
        for idx, item in enumerate(self):
            str_to_show = str_to_show + \
                "Idx [" + str(idx) + "] ---> " + self.color + \
                item + Fore.RESET + "\n"

        return str_to_show


def main() -> None:
    tupla = SuperTupla.__new__(("a", "b", "c", "d", "e", "f"), "Letras", Fore.BLUE)

    print(tupla)


if __name__ == "__main__":
    main()