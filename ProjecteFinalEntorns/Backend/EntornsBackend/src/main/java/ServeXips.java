import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServeXips
 */
@WebServlet("/ServeXips")
public class ServeXips extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServeXips() {
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Guardamos en variables lo que nos llega del frontend
		String email = request.getParameter("mail");
		String session = request.getParameter("session");
		
		// Iniciamos la string de tabla
		String tabla = "";
		
		// Comprobamos si la session es valida
		Doctor doctor = new Doctor();
		boolean isLogged = doctor.isLogged(email, session);		
		if (isLogged == true) {
			
			// Primero cargamos en el objeto del doctor la lista de todos sus xips que esten en alta todavia
			doctor.loadReleaseList();
			
			// Creamos la tabla que devolveremos al frontend
			tabla = doctor.getTable();
		}
        
		// Añadimos permisos de CORS
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setStatus(HttpServletResponse.SC_OK);
        
		// Devolvemos la tabla al frontend
		response.getWriter().write(tabla);
	}
}
