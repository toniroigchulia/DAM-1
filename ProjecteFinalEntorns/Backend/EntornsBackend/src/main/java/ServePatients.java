
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("mail");
		String session = request.getParameter("session");

		Doctor doctor = new Doctor();
		boolean isLogged = doctor.isLogged(email, session);

		List<Patient> patients = new ArrayList<>();
		if (isLogged == true) {
			try {
				Connection conn = DataBaseConnection.getConnection();
				Statement st = null;
				st = conn.createStatement();

				String query = "SELECT mail FROM patient";

				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {

					Patient patient = new Patient();
					patient.load(rs.getString("mail"));
					patients.add(patient);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

			ObjectMapper objectMapper = new ObjectMapper();
			String json = null;
			json = objectMapper.writeValueAsString(patients);

			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type");
			response.setStatus(HttpServletResponse.SC_OK);

			response.getWriter().write(json);
		}
	}
}
