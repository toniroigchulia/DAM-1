import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

// Classe xip
public class Xip {
	
	// Atributos
	int id;
	String doctor_mail;
	int id_medicine;
	String id_patient;
	Date date;
	
	// Constructor vacio y con todos los atributos
	Xip () {}
	
	Xip (int id, int id_medicine, String id_patient, Date date, String doctor_mail) {
		
		this.id = id;
		this.id_medicine = id_medicine;
		this.id_patient = id_patient;
		this.date = date;
		this.doctor_mail = doctor_mail;
		
	}
	
	// Metodo para cargar en el objeto la informacion de la base de datos
	void load(int id) {
		
		try {
			// Coneccion a la base de datos
			Connection conn = DataBaseConnection.getConnection();
			Statement st = null;
			st = conn.createStatement();
			
			// Query para obtener todas las columnas del xip con la id correspondiente
			String query = "SELECT * FROM xip WHERE id='" + id + "'";
			ResultSet rs = st.executeQuery(query);
			
			// Si la query devuelve algo guardamos cada valor en su atributo correspondiente
			if (rs.next()) {
				
				this.id = id;
				this.doctor_mail = rs.getString("doctor_mail");
				this.id_medicine = rs.getInt("id_medicine");
				this.id_patient = rs.getString("id_patient");
				this.date = rs.getDate("date");
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

	public String getDoctor_mail() {
		return doctor_mail;
	}

	public void setDoctor_mail(String doctor_mail) {
		this.doctor_mail = doctor_mail;
	}

	public int getId_medicine() {
		return id_medicine;
	}

	public void setId_medicine(int id_medicine) {
		this.id_medicine = id_medicine;
	}

	public String getId_patient() {
		return id_patient;
	}

	public void setId_patient(String id_patient) {
		this.id_patient = id_patient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}