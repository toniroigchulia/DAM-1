import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Login() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Guardamos lo que recibimos del frontend en sus variables correspondientes
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// Hasheamos la contraseña para guardarla en la base de datos
		String hashedPassword = Utils.hash(password);
		
		
		// Hacemos el login usando el metodo del doctor
		Doctor doctor = new Doctor();
		doctor.Login(email, hashedPassword);
		
		// Convertimos el objeto del doctor a json
		ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        json = objectMapper.writeValueAsString(doctor);
        
        
        // Damos permiso de CORS y mandamos el json al frontend
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setStatus(HttpServletResponse.SC_OK);
        
		response.getWriter().write(json);
	}
}
