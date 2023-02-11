import java.util.ArrayList;
import java.util.LinkedHashMap;

public class NumeroDeterminadoAlumnos {
    public static ArrayList<Integer> getNumeroDeterminadoAlumnos(int numeroAlumnos, LinkedHashMap<String, ArrayList<Integer>> horario, String dia){
        ArrayList<Integer> horaNumeroDeterminadoAlumnos = new ArrayList<>();
        
        int index = 0;
        for(int num: horario.get(dia)){
            if (num == numeroAlumnos){
                horaNumeroDeterminadoAlumnos.add(++index);    
            }else{
                index++;
            }
        }
        
        return horaNumeroDeterminadoAlumnos;
    }
}
