import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {     
    
        int num1Sum = 0;
        int num2Sum = 0;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Añade el primer numero");
        int num1 = input.nextInt(); 
        
        System.out.println("");
        
        System.out.println("Añade el segundo numero");
        int num2 = input.nextInt();
        
        System.out.println("");
        
        for (int i = 1; i < num1; i++){
            
            if(num1 % i == 0){
            
                num1Sum = num1Sum + i;
                System.out.println("Suma del primer numero: " + num1Sum);
                
            }
        }
        
        System.out.println("");
        
        if(num1Sum == num2){
            
            
            for (int i = 1; i < num2; i++){
                
                if(num2 % i == 0){
                
                    num2Sum = num2Sum + i;
                    System.out.println("Suma del segundo numero: " + num2Sum);
                    
                }
            }
            
            System.out.println("");
            
            if(num2Sum != num1){
                
                System.out.println("El numero: " + num1 + " y el numero:" + num2 + " NO SON AMIGOS.");
                
            } else {
            
                System.out.println("El numero: " + num1 + " y el numero:" + num2 + " SON AMIGOS.");
            
            }
            
        } else {
        
            System.out.println("El numero: " + num1 + " y el numero:" + num2 + " NO SON AMGIOS.");
        
        }    
        
        input.close();
    }  
}