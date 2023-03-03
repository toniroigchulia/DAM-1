import java.util.ArrayList;
import java.util.LinkedHashMap;

public class NumeroDeterminadoAlumnos {

    //Funcion para encontrar el numero especifico de alumnos.
    public static ArrayList<Integer> getNumeroDeterminadoAlumnos(int numeroAlumnos, LinkedHashMap<String, ArrayList<Integer>> horario, String dia){
        
        //Generamos una array donde guardar los resultados
        ArrayList<Integer> horaNumeroDeterminadoAlumnos = new ArrayList<>();
        
        //Usamos un blucle para movernos por todo el diccionario
        int index = 0;
        for(int num: horario.get(dia)){
        
            //Si el numero que nos intersa coincide con el numero de la posicion actual lo almazenamos en la array
            if (num == numeroAlumnos){
                horaNumeroDeterminadoAlumnos.add(++index);    
            }else{
                index++;
            }
        }
        
        return horaNumeroDeterminadoAlumnos;
    }
}
