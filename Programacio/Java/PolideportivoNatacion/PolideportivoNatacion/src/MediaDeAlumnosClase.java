import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MediaDeAlumnosClase {
    
    //Metodo para generar la media de alumnos por hora
    public static ArrayList<Double> getMediaAlumnosHora(LinkedHashMap<String, ArrayList<Integer>> horario){
    
        //Para calcular la media necesitamos el total de alumnos por eso generamos una array con el total y otra para la media
        ArrayList<Integer> totalAlumnosHoras = TotalAlumnosHora.getTotalAlumnosHora(horario);
        ArrayList<Double> mediaAlumnosHoras = new ArrayList<>();
    
        //Por cada valo de totalAlumnosHoras aplicamos la formula para sacar la media y guardarla en la array de mediaAlumnosHora
        for(double totalAlumnosHora : totalAlumnosHoras){
            mediaAlumnosHoras.add(totalAlumnosHora/totalAlumnosHoras.size());
        }
        
        return mediaAlumnosHoras;
    }
}
