import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TotalAlumnosHora {

    //Funcion para calcular el total de alumnos por hora
    public static ArrayList<Integer> getTotalAlumnosHora(LinkedHashMap<String, ArrayList<Integer>> horario){
    
        //Array para guardar la informacion
        ArrayList<Integer> totalAlumnosHora = new ArrayList<>();
        
        
        //Por cada array de alumnos de cada dia
        for (ArrayList<Integer> horas : horario.values()){
            
            //Lo recorremos y sumamos su valor a la hora correspondiente
            for(int i = 0; i < horas.size(); i++){
            
                try {
                    totalAlumnosHora.set(i, totalAlumnosHora.get(i) + horas.get(i));
                } catch (IndexOutOfBoundsException e) {
                    totalAlumnosHora.add(horas.get(i));
                }
            }
        }
        
        return totalAlumnosHora;
    }
}
