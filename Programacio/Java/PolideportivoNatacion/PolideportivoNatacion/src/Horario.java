import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Horario {
    public static LinkedHashMap<String, ArrayList<Integer>> getHorario(String[] dias){
        LinkedHashMap<String, ArrayList<Integer>> horario = new LinkedHashMap<String, ArrayList<Integer>>();

    
        for (String dia : dias) {   
            ArrayList<Integer> horas = new ArrayList<Integer>();
                
            int totalalumnos = 0;
                
            for (int i = 5; i > 0; i--) {
                int randomNum = ThreadLocalRandom.current().nextInt(10, 20);
                        
                totalalumnos = totalalumnos + randomNum;    
                    
                horas.add(randomNum);  
            }
                
            horario.put(dia, horas);
        }
        
        return horario;
    }
}