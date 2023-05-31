import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Classe de utilidad para conectarnos a la base de datos
public class DataBaseConnection {
	
	// Variables necesarios para hacer la coneccion
    private static final String DB = "farmaciaentorns";
    private static final String URL = "jdbc:mysql://localhost/";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    // Nombre de la coneccion
    private static Connection conn = null;
    
    // Metodo para conectarse a la base de datos
    protected static boolean connect() throws SQLException {
        
    	try {
    		// Comprobamos que el driver este instalado
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			
			System.out.println("No hay driver");
		}
        
    	// Nos conectamos a la base de datos
    	conn = DriverManager.getConnection(URL + DB, USER, PASSWORD);
        
    	// Si no se conecta mandamos una excepcion
    	if (conn == null) {
    		throw new SQLException("No se ha podido realizar la conexion");
    	}
        
    	// Si ha ido todo bien devolvemos TRUE para indicarlo
        return true;
    }
    
    // Metodo para desconectarnos de la base de datos
    protected static boolean disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // Metodo para comprobar saber si ya estamos conectados a la base de datos
    protected static boolean isConnected() {
        try {
            return conn != null && !conn.isClosed();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // Metdo que llamamos para conectarnos. Comprueba si estamos conectados si no estamos 
    // conectados llama al metodo para conectarse a la base de datos y devuelve la conexion
    protected static Connection getConnection() throws SQLException {
    	
    	if(!DataBaseConnection.isConnected()) {
    			DataBaseConnection.connect();
    	}
    	return conn;
    }
}