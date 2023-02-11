import java.util.ArrayList;

public class TotalAlumnosDia {
    public static int getTotalalumnos(ArrayList<Integer> horas){
        
        int totalalumnos = 0;
        for (int i : horas){
            totalalumnos = totalalumnos + i;
        }
        
        return totalalumnos;
    }
}
