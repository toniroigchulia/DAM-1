import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Horario {
    //Metodo para generar el horario
    public static LinkedHashMap<String, ArrayList<Integer>> getHorario(String[] dias){
    
        //Generamos un nuevo HashMap para devolverlo
        LinkedHashMap<String, ArrayList<Integer>> horario = new LinkedHashMap<String, ArrayList<Integer>>();

        //Por cada dia en la array de dias reprtimos este proceso
        for (String dia : dias) {   
        
            //Generamos una array de horas que sera la que ira con los dias
            ArrayList<Integer> horas = new ArrayList<Integer>();
                
            //Llenamos la array de horas con numeros aleatorios entre 10, 20 
            for (int i = 5; i > 0; i--) {
                int randomNum = ThreadLocalRandom.current().nextInt(10, 20);
                                             
                horas.add(randomNum);  
            }
            
            //Hacemos que el valor de el dia  en que estemos sea la array de horas y repetimos el proceso.
            horario.put(dia, horas);
        }
        
        return horario;
    }
}