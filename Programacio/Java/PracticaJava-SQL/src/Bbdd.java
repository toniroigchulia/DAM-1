import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Bbdd {
    public static void insertPersona(Persona persona){
        
        Connection con = null;
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practicajava","root","");    
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        Statement st = null;
        
        try {
            st = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        String query = "INSERT INTO taula VALUES ('"+persona.getNom()+"',"+persona.getAny()+")";
        
        try {
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
