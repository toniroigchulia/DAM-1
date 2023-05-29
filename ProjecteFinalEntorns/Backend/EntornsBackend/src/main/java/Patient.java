import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Patient extends Persona{
	
	
	Patient() {}
	
	Patient(String name, String mail) {
		
		super(name, mail);
		
	}
	
	@Override
	void load(String id) {
		try {
			Connection conn = DataBaseConnection.getConnection();
			Statement st = null;
			st = conn.createStatement();

			String query = "SELECT * FROM patient Where mail=" + "'" + id + "'";

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {

				this.mail = id;
				this.name = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
