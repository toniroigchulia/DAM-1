import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MediaDeAlumnosClase {
    public static ArrayList<Double> getMediaAlumnosHora(LinkedHashMap<String, ArrayList<Integer>> horario){
        ArrayList<Integer> totalAlumnosHoras = TotalAlumnosHora.getTotalAlumnosHora(horario);
        ArrayList<Double> mediaAlumnosHoras = new ArrayList<>();

        for(double totalAlumnosHora : totalAlumnosHoras){
            mediaAlumnosHoras.add(totalAlumnosHora/totalAlumnosHoras.size());
        }
        
        return mediaAlumnosHoras;
    }
}
