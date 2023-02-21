import java.util.ArrayList;

public class TotalAlumnosDia {

    //Funcion para calcular el total de alumnos por dia
    public static int getTotalalumnos(ArrayList<Integer> horas){
        
        //Recorremos la array de alumnos por hora y los vamos sumando
        int totalalumnos = 0;
        for (int i : horas){
            totalalumnos = totalalumnos + i;
        }
        
        return totalalumnos;
    }
}
