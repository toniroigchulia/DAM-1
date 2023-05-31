
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServePatients
 */
@WebServlet("/ServePatients")
public class ServePatients extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServePatients() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Guardamos en variables lo que recibimos del frontend
		String email = request.getParameter("mail");
		String session = request.getParameter("session");
		
		// Creamos un objeto doctor para comprovar si la session es valida
		Doctor doctor = new Doctor();
		boolean isLogged = doctor.isLogged(email, session);
		
		
		// Creamos una lista de objetos pacientes para devovlerla al frontend
		List<Patient> patients = new ArrayList<>();
		if (isLogged == true) {
			try {
				// Coneccion a la base de datos
				Connection conn = DataBaseConnection.getConnection();
				Statement st = null;
				st = conn.createStatement();
				
				// Query para obtener el mail de todos los pacientes
				String query = "SELECT mail FROM patient";

				ResultSet rs = st.executeQuery(query);
				
				// Por cada tupla que devuelve la query ejecutamos lo siguiente
				while (rs.next()) {
					
					// Creamos un objeto de paciente usando el mail cargamos su informacion y lo añadimos a la lista
					Patient patient = new Patient();
					patient.load(rs.getString("mail"));
					patients.add(patient);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			// Transformamos la lista a JSON pra devolverlo al frontend
			ObjectMapper objectMapper = new ObjectMapper();
			String json = null;
			json = objectMapper.writeValueAsString(patients);
			
			// Añadimos permiso de CORS
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type");
			response.setStatus(HttpServletResponse.SC_OK);
			
			// Devolvemos el json
			response.getWriter().write(json);
		}
	}
}
