import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        boolean end = false;
        Scanner sc = new Scanner(System.in);
        Persona persona = new Persona();
        
        
        while(!end){
            System.out.println("\n"+"Que vols fer?");
            System.out.println("1. Introduir persona");
            System.out.println("2. Sortir"+"\n");
            
            String opcio = sc.nextLine();
            
            if (opcio.equals("1")){
            
                System.out.println("Nom persona: ");
                persona.setNom(sc.nextLine());
                
                System.out.println("Any neixament persona: ");
                persona.setAny(sc.nextInt());
                sc.nextLine();
                
                Bbdd.insertPersona(persona);
                
            } else if (opcio.equals("2")){
            
                end = true;
            
            } else {
            
                System.out.println("\n"+"No has triat una opcio valida");
                
            }
        }
    }
}