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
		// TODO Auto-generated method stub
		String email = request.getParameter("mail");
		String session = request.getParameter("session");
		
		String tabla = "";
		
		Doctor doctor = new Doctor();
		boolean isLogged = doctor.isLogged(email, session);		
		if (isLogged == true) {
			doctor.loadReleaseList();
			tabla = doctor.getTable();
		}
        
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setStatus(HttpServletResponse.SC_OK);
        
		response.getWriter().write(tabla);
	}
}
