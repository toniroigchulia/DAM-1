import java.io.IOException;

import org.json.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Xat
 */
@WebServlet("/Xat")
public class Xat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Xat() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String session = request.getParameter("session");
		boolean enviados = Boolean.parseBoolean(request.getParameter("enviados"));
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");

		User u = new User();
		u.setMail(mail);
		u.setSession(session);

		String mensages = u.getMissatges(enviados);
		
		response.getWriter().append(mensages);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String session = request.getParameter("session");
		String receptor = request.getParameter("receptor");
		String text = request.getParameter("sms");

		User u = new User();
		u.setMail(mail);
		u.setSession(session);

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");

		Missatge sms = null;
		if (u.isLogged()) {
			sms = new Missatge();
			sms.setReceptor(receptor);
			sms.setText(text);
			sms.setEmisor(mail);
			sms.guardar();
		}
	}
}