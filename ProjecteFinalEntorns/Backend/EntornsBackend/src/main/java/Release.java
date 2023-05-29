import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Release
 */
@WebServlet("/Release")
public class Release extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Release() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("mail");
		String session = request.getParameter("session");
		int idXip = Integer.parseInt(request.getParameter("idXIp"));
		String idMed = request.getParameter("idMed");
		String idPatient = request.getParameter("idPatient");
		
		String dataLimite = request.getParameter("date");
		String formatPattern = "yyyy-MM-dd";
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        LocalDate date = null;
        
        try {
            date = LocalDate.parse(dataLimite, formatter);
            System.out.println("Parsed Date: " + date);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
        
		Doctor doctor = new Doctor();
		boolean isLogged = doctor.isLogged(email, session);
		
		if (isLogged == true) {
			try {
				Connection conn = DataBaseConnection.getConnection();
				Statement st = null;
				st = conn.createStatement();
				
				String query = "INSERT INTO xip (id, doctor_mail, id_medicine, id_patient, date) VALUES ("+"'"+idXip+"' ,"+"'"+email+"' ,"+"'"+idMed+"' ,"+"'"+idPatient+"' ,"+date+")";
				
				st.executeUpdate(query);

			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
}