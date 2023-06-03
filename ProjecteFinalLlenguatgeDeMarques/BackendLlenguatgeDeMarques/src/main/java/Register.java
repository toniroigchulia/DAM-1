import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Register() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		String codeCountry = request.getParameter("codeCountry");
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		
		User persona = new User(user,mail,pass,codeCountry);
		boolean resultat = persona.register();
		response.getWriter().append(String.valueOf(resultat));
		System.out.println("Registre: "+resultat);
		
	}
	
	//Retorna la llista de paisos necessari pel registre
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
				
		String json = Country.getList();
		System.out.println(json);
		response.getWriter().append(json);
	}

}
