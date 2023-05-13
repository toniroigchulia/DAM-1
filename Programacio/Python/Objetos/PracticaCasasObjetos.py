# Classe Propietario
class Propietario():
    
    def __init__(self, nif: str = "None", nombre: str = "None", direccion: str = "None", propiedades: list = []):
        
        self.nif: str = nif
        self.nombre: str = nombre
        self.direccion: str = direccion
        self.propiedades: list = propiedades
        
    def add_vivienda():
        
        pass
    
    def add_finca(self, nombre, tipo, direccion, superficie, valor, descripcion):
    
        self.propiedades.append(Finca.__init__(nombre, tipo, direccion, superficie, valor, descripcion))
    
    def get_valor():
    
        pass
        
    def show():
    
        pass
    
# Classe Propiedad
class Propiedad():

    def __init__(self, descripcion: str = "None", valor: int = 0):
        
        self.descripcion: str = descripcion
        self.valor:int = valor
        
    def get_valor():
    
        pass
        
    def show():
    
        pass
        
# Classe Vivienda
class Vivienda(Propiedad):

    def __init__(self, direccion:str = "None", superficie:float = 0, nro_habitaciones:int = 0, descripcion:str = "None", valor:int = 0):
        
        super().__init__(descripcion, valor)
        self.direccion: str = direccion
        self.superficie: float = superficie
        self.nro_habitaciones: int = nro_habitaciones
        
    def get_valor():
    
        pass
        
    def show():
    
        pass
        
# Classe Finca
class Finca(Propiedad):

    def __init__(self, nombre:str = "None", tipo:str = "None", direccion:str = "None", superficie:float = 0, valor:float = 0, descripcion:str = "None"):
        
        super.__init__(descripcion, valor)
        self.nombre : str = nombre
        self.tipo: str = tipo
        self.direccion: str = direccion
        self.superficie: float = superficie
        
    def show():
    
        pass
    
    def set_vivienda():
    
        pass
    
    def set_huerto():
    
        pass
    
    def set_piscina():
    
        pass
        
    def get_valor():
    
        pass

# Classe Chalet
class Chalet(Vivienda):
    
    def __init__(self, direccion:str = "None", superficie:float = 0, nro_habitaciones:int = 0, descripcion:str = "None", valor:int = 0):
        
        super().__init__(direccion,superficie, nro_habitaciones, descripcion, valor)
    
    def set_piscina():
    
        pass
        
    def set_jardin():
    
        pass
        
    def show():
    
        pass
        
    def get_valor():
        
        pass

# Classe Adosado
class Adosado(Vivienda):
    
    def __init__(self, direccion:str = "None", superficie:float = 0, nro_habitaciones:int = 0, descripcion:str = "None", valor:int = 0):
        
        super().__init__(direccion,superficie, nro_habitaciones, descripcion, valor)
    
    def show():
    
        pass
        
    def set_jardin():
    
        pass
        
    def get_valor():
    
        pass
        
# Classe Apartamento
class Apartamento(Vivienda):

    def __init__(self, direccion:str = "None", superficie:float = 0, nro_habitaciones:int = 0, descripcion:str = "None", valor:int = 0, balcon:bool = False):
        
        super().__init__(direccion,superficie, nro_habitaciones, descripcion, valor)
        self.balcon : bool = balcon
        
    def show():
    
        pass

# Classe Huerto
class Huerto():

    def __init__(self, superficie:float = 0, reigo_por_goteo:bool = False):
    
        self.superficie : float = superficie
        self.riego_por_goteo : bool = reigo_por_goteo
        
    def show():
    
        pass
        
    def get_valor():
    
        pass

# Classe Piscina
class Piscina():

    def __init__(self, volumen:float = 0, cloracion_salina:bool = False, valor:int = 0):
    
        self.volumen : float = volumen
        self.cloracion_salina : bool = cloracion_salina
        self.valor : int = valor
        
    def show():
        
        pass
        
    def get_valor():
    
        pass

# Classe Jardin
class Jardin():

    def __init__(self, superficie:float = 0, plantas:list = []):
    
        self.superficie : float = superficie
        self.plantas : list = plantas
        
    def show():
    
        pass
    
    def get_valor():
    
        pass