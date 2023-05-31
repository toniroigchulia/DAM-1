import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Classe para las medicinas
public class Medicine {
	
	// Atributos
	int id;
	String name;
	Float tmax;
	Float tmin;
	
	// Constructor vacio y con todos los atributos
	Medicine(){}
	
	Medicine(int id, String name, Float tmax, Float tmin){
		
		this.id = id;
		this.name = name;
		this.tmax = tmax;
		this.tmin = tmin;
		
	}
	
	// Metodo para cargar en los atributos del objeto la informacion de la base de datos
	void load(int id) {
		try {
			// Coneccion a la base de datos
			Connection conn = DataBaseConnection.getConnection();
			Statement st = null;
			st = conn.createStatement();
			
			// Query para cojer toda las columnas de la medicina deseada
			String query = "SELECT * FROM medicine WHERE id='" + id + "'";

			ResultSet rs = st.executeQuery(query);
			
			// Si la query devuelve algo lo guardamos en su atributo correspondiente
			if (rs.next()) {

				this.id = id;
				this.name = rs.getString("name");
				this.tmax = rs.getFloat("tmax");
				this.tmin = rs.getFloat("tmin");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Getters And Setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Float getTmax() {
		return tmax;
	}
	
	public void setTmax(Float tmax) {
		this.tmax = tmax;
	}
	
	public Float getTmin() {
		return tmin;
	}
	
	public void setTmin(Float tmin) {
		this.tmin = tmin;
	}
}