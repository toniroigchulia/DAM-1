import webbrowser
import os

background_color_primary = input("Color primario de la tabla: ")
background_color_secondary = input("Color secundario de la tabla: ")

jugadores = []
puntuacion = []

#Añadir informacion a las listas
num_jugadores = int(input("\n","Cuantos jugadores quieres añadir? "))
while num_jugadores > 0:
  os.system("cls")
  
  print("Color primaro: "+background_color_primary)
  print("Color secundario: " + background_color_secondary+"\n")
  
  print(jugadores)
  print(puntuacion,"\n")
  
  print("Jugadores restantes: ",num_jugadores)
  jugadores.append(input("Jugador: "))
  puntuacion.append(input("Puntuacion: "))
  print("\n")
  
  num_jugadores -= 1

#Abrimos los documentos para editarlos
html_file = open("web.html", "w")
css_file = open("estilos.css", "w")

#Principio de html
html = """
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" type="text/css" href="estilos.css" title="style" />

<style type="text/css">
</style>

</head>
<body>

  <table>
    <tr>
      <th>Posicion</th>
      <th>Jugador</th>
      <th>Puntos</th>
    </tr>
"""

#Añadimos una tupla por cada jugador en la array de jugadores
for x in range(len(jugadores)):

  html += f"""
      <tr>
        <td>{(x+1)}</td>
        <td>{jugadores[x]}</td>
        <td>{puntuacion[x]}</td>
      </tr>
  """

#Final de html
html += """
  </table>  
</body>
</html>
"""

#CSS
css = f"""

table {{
    font-family: Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
  }}
  
  table td, table th {{
    border: 1px solid #ddd;
    padding: 8px;
  }}
  
  table tr:nth-child(even){{background-color: {background_color_secondary};}}
  
  table tr:hover {{background-color: #ddd;}}
  
  table th {{
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: {background_color_primary};
    color: white;
  }}

"""

#Escribimos los documentos con la informacion correspondiente
html_file.write(html)
css_file.write(css)

#Cerramos los archivos
html_file.close()
css_file.close()

#Abrimos la pestaña
webbrowser.open_new_tab("web.html")