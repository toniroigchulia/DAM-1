import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        
        //Array para los dias de la semana
        String[] dias = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes" };
        
        //Generamos un horario aleatorio
        LinkedHashMap<String, ArrayList<Integer>> horario = Horario.getHorario(dias);
        
        //Creamos el menu y pedimos que quiere hacer el usuario
        Menu(horario, dias);
        System.out.print("\n"+"--Inserte el Numero de la opcion deseada: ");
        Scanner sc = new Scanner(System.in);
        int opcionSeleccionada;
        
        //Preguntamos lo que queremos mostrar hasta que el usuario decida salir del programa
        do {
            
            opcionSeleccionada = sc.nextInt();   
            
            //Generar un nuevo horario
            if (opcionSeleccionada == 1) {
                horario = Horario.getHorario(dias);
                Menu(horario, dias);
                
                System.out.print("\n"+"--Inserte el Numero de la opcion deseada: ");
                
            //Sumar todos los alumnos por dia
            } else if (opcionSeleccionada == 2) {
                Menu(horario, dias);
                
                for (String dia : dias) {
                    System.out.println(dia + ": " + horario.get(dia) + " --> " + "Total: "
                            + TotalAlumnosDia.getTotalalumnos(horario.get(dia)));
                }
                
                System.out.print("\n"+"--Inserte el Numero de la opcion deseada: ");
                
            //Total alumnos por hora
            } else if (opcionSeleccionada == 3) {
                Menu(horario, dias);
                
                int index = 0;
                for (int totalAlumnosHora : TotalAlumnosHora.getTotalAlumnosHora(horario)) {
                    System.out.println((++index) + ".Horas: " + totalAlumnosHora);
                }
                
                System.out.print("\n"+"--Inserte el Numero de la opcion deseada: ");
            
            //Media de alumnos por hora
            } else if (opcionSeleccionada == 4) {
                Menu(horario, dias);
                
                int index = 0;
                for (double mediaAlumnosHora : MediaDeAlumnosClase.getMediaAlumnosHora(horario)) {
                    System.out.println((++index) + ".Media: " + mediaAlumnosHora);
                }
                
                System.out.print("\n"+"--Inserte el Numero de la opcion deseada: ");
                
            //Buscar a que horas hay una cantidad determinada de alumnos
            } else if (opcionSeleccionada == 5) {
                System.out.print("Inserte el Numero de alumnos que quieras buscar: ");
                int numeroAlumnos = sc.nextInt();
                
                Menu(horario, dias);
                System.out.println("Horas que contienen " + numeroAlumnos + " alumnos."+"\n");
                
                for(String dia: dias){
                    System.out.println(dia+": " + NumeroDeterminadoAlumnos.getNumeroDeterminadoAlumnos(numeroAlumnos, horario, dia));
                }
                
                System.out.print("\n"+"--Inserte el Numero de la opcion deseada: ");
                
            //Si no es una opcion valida se vuelve a pedir
            } else if (opcionSeleccionada != 6) {
                System.out.println("\n"+"!!!El numero insertado no pertenece a ninguna opcion.");
                System.out.print("--Inserte el Numero de la opcion deseada: ");
            }
        
        //Si la opcion es la 6 se termina el programa
        } while (opcionSeleccionada != 6);

        sc.close();
    }
    
    //Funcion para generar el menu de opciones disponibles
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
