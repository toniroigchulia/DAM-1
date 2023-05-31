import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Doctor extends Persona {

	String pass;
	Date lastlog;
	String session;
	List<Xip> releaseList;

	Doctor() {
	}

	Doctor(String name, String mail, String pass, Date lastlog, String session) {

		super(name, mail);
		this.pass = pass;
		this.lastlog = lastlog;
		this.session = session;

	}

	void Login(String mail, String pass) {

		try {
			Connection conn = DataBaseConnection.getConnection();
			Statement st = null;
			st = conn.createStatement();

			String query = "SELECT name FROM doctor WHERE mail='" + mail + "' AND pass='" + pass + "'";

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {

				Random random = new Random();
				String characters = "0123456789ABCDEF";
				int length = 10;
				String session = "";

				for (int i = 0; i < length; i++) {
					int index = random.nextInt(characters.length());
					session += characters.charAt(index);
				}

				Date date = new Date();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());

				query = "UPDATE doctor SET session=" + "'" + session + "'" + ", last_log=" + "'" + sqlDate + "'"
						+ " WHERE mail=" + "'" + mail + "'";
				st.executeUpdate(query);

				this.load(mail);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

	boolean isLogged(String mail, String session) {

		boolean isLessThan24Hours = false;

		try {
			Connection conn;
			conn = DataBaseConnection.getConnection();

			Statement st = null;
			st = conn.createStatement();

			String query = "SELECT last_log, session FROM doctor WHERE session=" + "'" + session + "'";

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {				
				if (rs.getString("session").equals(session)) {
					java.sql.Date sqlDate = rs.getDate("last_log");

					LocalDate currentDate = LocalDate.now();
					LocalDate sqlLocalDate = sqlDate.toLocalDate();

					long dateDifferenceDays = ChronoUnit.DAYS.between(sqlLocalDate, currentDate);

					isLessThan24Hours = dateDifferenceDays < 1;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (isLessThan24Hours == true) {
			this.load(mail);
		}
		
		return isLessThan24Hours;
	}
	

	@Override
	void load(String id) {

		try {
			Connection conn = DataBaseConnection.getConnection();

			Statement st = null;
			st = conn.createStatement();

			String query = "SELECT * FROM doctor WHERE mail=" + "'" + id + "'";

			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				
				this.releaseList = new ArrayList<>();
				this.mail = rs.getString("mail");
				this.name = rs.getString("name");
				this.lastlog = rs.getDate("last_log");
				this.session = rs.getString("session");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void loadReleaseList() {
		String mail = this.mail;
		try {
			Connection conn = DataBaseConnection.getConnection();
			Statement st = null;
			st = conn.createStatement();
			
			String query = "SELECT id FROM xip WHERE doctor_mail='" + mail +"' AND date >= CURDATE()"; 
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()){
				
				Xip xip = new Xip();
				xip.load(rs.getInt("id"));
				this.releaseList.add(xip);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	String getTable() {
		List<Xip> releaseList = this.releaseList;
		String tabla = "  <tr class=\"titulo_tabla\">\r\n"
				+ "           <th colspan=\"4\">Altas del Doctor</th>\r\n"
				+ "       </tr>\r\n"
				+ "       <tr class=\"subtitulos\">\r\n"
				+ "       <th class=\"paciente\">Paciente</td>\r\n"
				+ "       <th class=\"numero_xip\">Numero Xip</td>\r\n"
				+ "       <th class=\"medicamento\">Medicamento</td>\r\n"
				+ "       <th class=\"fecha_caducidad\">Fecha Caducidad</td>";
		
		for (int i = 0; i < releaseList.size(); i++) {
			tabla += "  <tr>\r\n"
					+ "    <td>"+releaseList.get(i).getId_patient()+"</td>\r\n"
					+ "    <td>"+releaseList.get(i).getId()+"</td>\r\n"
					+ "    <td>"+releaseList.get(i).getId_medicine()+"</td>\r\n"
					+ "    <td>"+releaseList.get(i).getDate()+"</td>\r\n"
					+ "  </tr>";
		}

		return tabla;
	}

	@JsonIgnore
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Date getLastlog() {
		return lastlog;
	}

	public void setLastlog(Date lastlog) {
		this.lastlog = lastlog;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	@JsonIgnore
	public List<Xip> getReleaseList() {
		return releaseList;
	}

	public void setReleaseList(List<Xip> releaseList) {
		this.releaseList = releaseList;
	}

}
