import webbrowser

my_file = open("web.txt")

index = """
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd%22%3E
<html xmlns="http://www.w3.org/1999/xhtml%22%3E
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="estilos.css" title="style" />
<style type="text/css">

</style>
</head>
<body>


    <table>
        <thead>
          <tr>
            <th>Posicion</th>
            <th>Jugador</th>
            <th>Puntos</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>Miquel</td>
            <td>1000</td>
          </tr>
          <tr>
            <td>2</td>
            <td>Jumi</td>
            <td>18</td>
          </tr>
          <tr>
            <td>3</td>
            <td>Maximo</td>
            <td>14</td>
          </tr>
          <tr>
            <td>4</td>
            <td>Isaac</td>
            <td>13</td>
          </tr>
          <tr>
            <td>5</td>
            <td>Messi</td>
            <td>12</td>
          </tr>
        </tbody>
      </table>


</body>
</html>
"""

my_file.write(index)

my_file.close()

webbrowser.open_new_tab("web.txt")