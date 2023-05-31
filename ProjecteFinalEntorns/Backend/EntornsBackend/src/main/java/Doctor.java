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

// Classe del doctor que extiende de la classe padre Persona
public class Doctor extends Persona {
	
	// Atributos
	String pass;
	Date lastlog;
	String session;
	List<Xip> releaseList;
		
	// Constructor vacio y constructor con todos los atributos menos releaseList
	Doctor() {
	}

	Doctor(String name, String mail, String pass, Date lastlog, String session) {

		super(name, mail);
		this.pass = pass;
		this.lastlog = lastlog;
		this.session = session;

	}
	
	// Metodo para hacer Login
	void Login(String mail, String pass) {

		try {
			// Nos conectamos a la base de datos
			Connection conn = DataBaseConnection.getConnection();
			Statement st = null;
			st = conn.createStatement();
			
			// Creamos la query y la ejecutamos
			String query = "SELECT name FROM doctor WHERE mail='" + mail + "' AND pass='" + pass + "'";

			ResultSet rs = st.executeQuery(query);
			
			// Si nos devuelve tuplas procedemos a lo siguiente
			if (rs.next()) {

				// Creamos un codigo de session en hexadecimal
				Random random = new Random();
				String characters = "0123456789ABCDEF";
				int length = 10;
				String session = "";

				for (int i = 0; i < length; i++) {
					int index = random.nextInt(characters.length());
					session += characters.charAt(index);
				}
				
				// Creamos un sqlDate del momento actual para insertarlo a la base de datos
				Date date = new Date();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				
				
				// Creamos la query y la ejecutamos guardando en la base de datos la informacion deseada para usarla en un futuro
				query = "UPDATE doctor SET session=" + "'" + session + "'" + ", last_log=" + "'" + sqlDate + "'"
						+ " WHERE mail=" + "'" + mail + "'";
				st.executeUpdate(query);
				
				
				// Llamamos al load pasandola el mail para cargar la informacion del doctor deseado en el objeto
				this.load(mail);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Metodo para comprobar si la session del doctor sigue siendo valida
	boolean isLogged(String mail, String session) {
		
		// Bool usado para devolverlo a continuacion dependiendo de si el docotr sigue logeado o no
		boolean isLessThan24Hours = false;

		try {
			// Conexion a la base de datos
			Connection conn;
			conn = DataBaseConnection.getConnection();
			
			// Preparamos y ejecutamos la query
			Statement st = null;
			st = conn.createStatement();

			String query = "SELECT last_log, session FROM doctor WHERE session=" + "'" + session + "'";

			ResultSet rs = st.executeQuery(query);
			
			// Si devuelve algo entramos al if
			if (rs.next()) {		
				
				// Si la session que passamos por parametros y la de la base de datos es igual entramos al if
				if (rs.getString("session").equals(session)) {
					
					// Cojemos el date que devuelve la base de datos y usamos un metodo para compararlo con la fecha
					// actual y comprobar si han passado menos de 24h
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
		
		// Si han passado menos de 24h cargamos la informacion en el objeto doctor
		if (isLessThan24Hours == true) {
			this.load(mail);
		}
		
		// Devolvemos el valor
		return isLessThan24Hours;
	}
	
	
	// Metodo heredado de Persona usado para cargar la informacion de la Base de datos en el objeto
	@Override
	void load(String id) {

		try {
			// Nos conectamos a la base de datos
			Connection conn = DataBaseConnection.getConnection();

			Statement st = null;
			st = conn.createStatement();
			
			// Query que recoje toda la informacion del doctor deseado
			String query = "SELECT * FROM doctor WHERE mail=" + "'" + id + "'";

			ResultSet rs = st.executeQuery(query);
			
			// Si devuelve algo lo assignamos a su respectivo atributo
			if (rs.next()) {
				
				// Inicializamos el releaseList par su futuro uso
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
	
	// Metodo para guardar todos los xips del doctor deseado en el releaseList
	void loadReleaseList() {
		
		// Cojemos el mail del doctor de su propio atributo
		String mail = this.mail;
		
		try {
			// Coneccion a la base de datos
			Connection conn = DataBaseConnection.getConnection();
			Statement st = null;
			st = conn.createStatement();
			
			// Query para recojer el id de los xips del doctor deseado y que esten en uso todavia
			String query = "SELECT id FROM xip WHERE doctor_mail='" + mail +"' AND date >= CURDATE()"; 
			ResultSet rs = st.executeQuery(query);
			
			// Por cada tupla que devuelve la query creamos un xip cargamos su informacion usando su metodo 
			// load con la id y lo guardamos en la lista
			while (rs.next()){
				
				Xip xip = new Xip();
				xip.load(rs.getInt("id"));
				this.releaseList.add(xip);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	// Metodo para crear la tabla con todos los xips del doctor deseado
	String getTable() {
		
		// Usamos la lista guardada en el objeto del doctor
		List<Xip> releaseList = this.releaseList;
		
		// Iniciamos la tabla poniendo el titulo y los subtitulos de las columnas
		String tabla = "  <tr class=\"titulo_tabla\">\r\n"
				+ "           <th colspan=\"4\">Altas del Doctor</th>\r\n"
				+ "       </tr>\r\n"
				+ "       <tr class=\"subtitulos\">\r\n"
				+ "       <th class=\"paciente\">Paciente</td>\r\n"
				+ "       <th class=\"numero_xip\">Numero Xip</td>\r\n"
				+ "       <th class=\"medicamento\">Medicamento</td>\r\n"
				+ "       <th class=\"fecha_caducidad\">Fecha Caducidad</td>";
		
		// Recorremos la lista y añadimos a la string la informacion deseada
		for (int i = 0; i < releaseList.size(); i++) {
			
			// Usando los geters del objeto xip añadimos la informacion
			tabla += "  <tr>\r\n"
					+ "    <td>"+releaseList.get(i).getId_patient()+"</td>\r\n"
					+ "    <td>"+releaseList.get(i).getId()+"</td>\r\n"
					+ "    <td>"+releaseList.get(i).getId_medicine()+"</td>\r\n"
					+ "    <td>"+releaseList.get(i).getDate()+"</td>\r\n"
					+ "  </tr>";
		}

		return tabla;
	}

	// Getters and setter
	@JsonIgnore // Esto indica al convertidor de objetos a json que este atributo no lo tiene que devlver
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

	@JsonIgnore // Esto indica al convertidor de objetos a json que este atributo no lo tiene que devlver
	public List<Xip> getReleaseList() {
		return releaseList;
	}

	public void setReleaseList(List<Xip> releaseList) {
		this.releaseList = releaseList;
	}

}