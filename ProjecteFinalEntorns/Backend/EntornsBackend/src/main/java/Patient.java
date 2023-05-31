import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Classe Paciente que extiende de persona
public class Patient extends Persona{
	
	// No tiene atributos propios
	
	// Constructor vacio y con los atributos del padre
	Patient() {}
	
	Patient(String name, String mail) {
		
		super(name, mail);
		
	}
	
	// Metodo para cargar en el objeto la informacion de la base de datos
	@Override
	void load(String id) {
		try {
			// Coneccion a la base de datos
			Connection conn = DataBaseConnection.getConnection();
			Statement st = null;
			st = conn.createStatement();
			
			// Query para cojer todas las columnas del paciente deseado
			String query = "SELECT * FROM patient Where mail=" + "'" + id + "'";

			ResultSet rs = st.executeQuery(query);
			
			// Si la query devuelve algo lo guardamos en su atributo correspondiente
			if (rs.next()) {

				this.mail = id;
				this.name = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
