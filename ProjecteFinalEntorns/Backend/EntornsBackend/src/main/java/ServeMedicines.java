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
 * Servlet implementation class ServeMedicines
 */
@WebServlet("/ServeMedicines")
public class ServeMedicines extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServeMedicines() {
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Guardamos en variables la informacion recivida del fronend
		String email = request.getParameter("mail");
		String session = request.getParameter("session");
		
		// Creamos un objecto doctor para comprobar si la sesion es valida
		Doctor doctor = new Doctor();
		boolean isLogged = doctor.isLogged(email, session);
		
		// Creamos una lista de objetos medicina para despues mandarla al frontend
		List<Medicine> medicines = new ArrayList<>();
		if (isLogged == true) {
			try {
				// Coneccion a la base de datos
				Connection conn = DataBaseConnection.getConnection();
				Statement st = null;
				st = conn.createStatement();
				
				// Query para obtener todas las id de las medicinas
				String query = "SELECT id FROM medicine";
				
				ResultSet rs = st.executeQuery(query);
				
				// Mientras haya mas tuplas ejecutamos lo siguiente
				while (rs.next()) {
					
					// Creamos un objeto de medicina le cargamos la informacion deseada y lo guardamos en la lista
					Medicine medicine = new Medicine();
					medicine.load(rs.getInt("id"));
					medicines.add(medicine);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			// Transformamos la lista de medicinas a json para poder acceder a su informacion en el frontend mas facilmente
			ObjectMapper objectMapper = new ObjectMapper();
	        String json = null;
	        json = objectMapper.writeValueAsString(medicines);
	        
	        // Añadimos el Cors
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type");
			response.setStatus(HttpServletResponse.SC_OK);
	        
			// Devolvemos al frontend el json
			response.getWriter().write(json);
		}
	}
}
