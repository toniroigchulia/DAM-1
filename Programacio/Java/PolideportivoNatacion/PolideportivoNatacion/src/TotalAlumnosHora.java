import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TotalAlumnosHora {
    public static ArrayList<Integer> getTotalAlumnosHora(LinkedHashMap<String, ArrayList<Integer>> horario){
        ArrayList<Integer> totalAlumnosHora = new ArrayList<>();
        
        for (ArrayList<Integer> horas : horario.values()){
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
