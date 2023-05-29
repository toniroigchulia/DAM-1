import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Doctor extends Persona {

	String pass;
	Date lastlog;
	String session;
	ArrayList<Xip> releaseList;

	Doctor() {
	}

	Doctor(String name, String mail, String pass, Date lastlog, String session) {

		super(name, mail);
		this.pass = pass;
		this.lastlog = lastlog;
		this.session = session;

	}

	void setreleaseList(ArrayList<Xip> releaseList) {

		this.releaseList = releaseList;

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

		boolean isMoreThan24Hours = false;

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

					isMoreThan24Hours = dateDifferenceDays < 1;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isMoreThan24Hours;
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

				this.mail = rs.getString("mail");
				this.name = rs.getString("name");
				this.lastlog = rs.getDate("last_log");
				this.session = rs.getString("session");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void loadReleaseLit() {

	}

	String getTable() {

		String b = "asda";
		return b;

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
	public ArrayList<Xip> getReleaseList() {
		return releaseList;
	}

	public void setReleaseList(ArrayList<Xip> releaseList) {
		this.releaseList = releaseList;
	}

}
