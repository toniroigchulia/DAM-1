import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Medicine {
	
	int id;
	String name;
	Float tmax;
	Float tmin;
	
	Medicine(){}
	
	Medicine(int id, String name, Float tmax, Float tmin){
		
		this.id = id;
		this.name = name;
		this.tmax = tmax;
		this.tmin = tmin;
		
	}
	
	void load(int id) {
		try {
			Connection conn = DataBaseConnection.getConnection();
			Statement st = null;
			st = conn.createStatement();

			String query = "SELECT * FROM medicine WHERE id='" + id + "'";

			ResultSet rs = st.executeQuery(query);

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