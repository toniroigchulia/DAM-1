import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        
        String[] dias = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes" };
        
        LinkedHashMap<String, ArrayList<Integer>> horario = Horario.getHorario(dias);
        
        Menu(horario, dias);
        System.out.print("\n"+"--Inserte el Numero de la opcion deseada: ");
        int opcionSeleccionada;
        Scanner sc = new Scanner(System.in);
        
        do {
            opcionSeleccionada = sc.nextInt();

            if (opcionSeleccionada == 1) {
                horario = Horario.getHorario(dias);
                Menu(horario, dias);
                
                System.out.print("\n"+"--Inserte el Numero de la opcion deseada: ");
            } else if (opcionSeleccionada == 2) {
                Menu(horario, dias);
                
                for (String dia : dias) {
                    System.out.println(dia + ": " + horario.get(dia) + " --> " + "Total: "
                            + TotalAlumnosDia.getTotalalumnos(horario.get(dia)));
                }
                
                System.out.print("\n"+"--Inserte el Numero de la opcion deseada: ");
            } else if (opcionSeleccionada == 3) {
                Menu(horario, dias);
                
                int index = 0;
                for (int totalAlumnosHora : TotalAlumnosHora.getTotalAlumnosHora(horario)) {
                    System.out.println((++index) + ".Hora: " + totalAlumnosHora);
                }
                
                System.out.print("\n"+"--Inserte el Numero de la opcion deseada: ");
            } else if (opcionSeleccionada == 4) {
                Menu(horario, dias);
                
                int index = 0;
                for (double mediaAlumnosHora : MediaDeAlumnosClase.getMediaAlumnosHora(horario)) {
                    System.out.println((++index) + ".Media: " + mediaAlumnosHora);
                }
                
                System.out.print("\n"+"--Inserte el Numero de la opcion deseada: ");
            } else if (opcionSeleccionada == 5) {
                System.out.print("Inserte el Numero de alumnos que quieras buscar: ");
                int numeroAlumnos = sc.nextInt();
                
                Menu(horario, dias);
                System.out.println("Horas que contienen " + numeroAlumnos + " alumnos."+"\n");
                
                for(String dia: dias){
                    System.out.println(dia+": " + NumeroDeterminadoAlumnos.getNumeroDeterminadoAlumnos(numeroAlumnos, horario, dia));
                }
                
                System.out.print("\n"+"--Inserte el Numero de la opcion deseada: ");
            }
        } while (opcionSeleccionada != 6);

        sc.close();
    }
    
    public static void Menu(LinkedHashMap<String, ArrayList<Integer>> horario, String[] dias){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        
        System.out.println("===== Horario de la Semana =====");
        for (String dia : dias) {
            System.out.println(dia + ": " + horario.get(dia));
        }
        
        System.out.println("\n"+"===== Opciones disponibles =====");
        System.out.println("1. Generar Nuevo Horario.");
        System.out.println("2. Mostrar el total de alumnos por día.");
        System.out.println("3. Mostrar el total de alumnos por hora.");
        System.out.println("4. Mostrar la media de alumnos por clase.");
        System.out.println("5. Mostrar numero determinado alumnos.");
        System.out.println("6. Cerrar el programa."+"\n");

    }
}
