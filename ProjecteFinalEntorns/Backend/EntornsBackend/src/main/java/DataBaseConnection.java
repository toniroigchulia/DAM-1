import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseConnection {
    private static final String DB = "farmaciaentorns";
    private static final String URL = "jdbc:mysql://localhost/";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    private static Connection conn = null;
    
    protected static boolean connect() throws SQLException {
        
    	try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay driver");
		}
            
    	conn = DriverManager.getConnection(URL + DB, USER, PASSWORD);
            
    	if (conn == null) {
    		throw new SQLException("No se ha podido realizar la conexion");
    	}
                  
        return true;
    }
    
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
    
    protected static boolean isConnected() {
        try {
            return conn != null && !conn.isClosed();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    protected static Connection getConnection() throws SQLException {
    	
    	if(!DataBaseConnection.isConnected()) {
    			DataBaseConnection.connect();
    	}
    	return conn;
    }
}