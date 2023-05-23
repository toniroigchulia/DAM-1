#!/bin/bash

# Codigos de color
GREEN="\033[0;32m"
BLUE="\033[0;34m"
RED="\033[0;31m"
NC="\033[0m"

# Funcion recursiva para buscar los archivos y printearlos
search_files() {
    local dir="$1"
    local depth=$2
    local indent="$3"
    local search_query="$4"

    # Miramos que la profundidad de la busqueda este en rango
    if [ "$depth" -ge 0 ]; then

        # Printeamos el nombre del directorio en color verde
        echo -e "${GREEN}${indent}${dir}/${NC}"

        # Incrementamos la identacion para ver mejor donde estan situados los archivos
        local sub_indent="${indent}  "

        # Buscamos en los archivos del directorio en cuestion
        while IFS= read -r -d '' file; do

            # Comprobamos si el archivo es un directorio
            if [ -d "$file" ]; then

                # Si lo es llamamos a la funcion recursiva
                search_files "$file" $((depth - 1)) "$sub_indent" "$search_query"

            else

                # Si no lo es comprobamos si el archivo coincide con la busqueda
                if [[ "$file" == *"$search_query"* ]]; then

                    # Los archivos que coinciden de color rojo
                    echo -e "${sub_indent}${RED}└── ${file}${NC}"

                else

                    # Los otros de azul
                    echo -e "${sub_indent}${BLUE}└── ${file}${NC}"

                fi
            fi
            
        # Esta linea comprueba si esta dentro de la profunidad de busqueda correcta, elimina salidas de  
        # error del find y si encuentra algun otro archivo se lo manda otra vez al while como parmetro
        done < <(find "$dir" -mindepth 1 -maxdepth 1 -print0 2>/dev/null)
    fi
}

#Preguntamos donde quiere buscar el usuario
read -p "En que directorio quieres hacer la busqueda ([.] para el directorio actual): " search_directory

# Preguntamos que quiere buscar el usuario
read -p "Indica el nombre o la letra que quieras buscar (se diferencia en mayus y minus) " search_query

# Preguntamos en que rango de directorios quiere buscar
read -p "Indica la maxima profundidad de la busqueda (0 para solo el actual): " max_depth

# Printeamos lo que queria buscar el usuario
echo -e "Query de busqueda ${search_query}\n"

# Usamos la funcion para buscar los archivos
search_files "$search_directory" "$max_depth" "" "$search_query"

# Finaliza el programa
echo " "
echo "Ejecucion completada"