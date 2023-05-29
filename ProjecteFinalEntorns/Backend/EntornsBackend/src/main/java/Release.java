import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if ("OPTIONS".equals(request.getMethod())) {
    		
    		handleOptions(request, response);
    	} else {
    		
    		super.service(request, response);
    	}
    }
    
    protected void handleOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setStatus(HttpServletResponse.SC_OK);
    }
    
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
				
		String email = request.getParameter("mail");
		String session = request.getParameter("session");
		String idPatient = request.getParameter("idPatient");
		
		int idXip = Integer.parseInt(request.getParameter("idXip"));
		int idMed = Integer.parseInt(request.getParameter("idMed"));
		
		String dataLimite = request.getParameter("date");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
		java.sql.Date sqlDate = null;
		
        try {
        	
            java.util.Date utilDate = dateFormat.parse(dataLimite);
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            
        	e.printStackTrace();
        }
        
        
		Doctor doctor = new Doctor();
		boolean isLogged = doctor.isLogged(email, session);
		
		int rowsAffected = 0;
		if (isLogged == true) {
			try {
				Connection conn = DataBaseConnection.getConnection();
				
				String query = "INSERT INTO xip (id, doctor_mail, id_medicine, id_patient, date) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = conn.prepareStatement(query);
			    preparedStatement.setInt(1, idXip);
			    preparedStatement.setString(2, email);
			    preparedStatement.setInt(3, idMed);
			    preparedStatement.setString(4, idPatient);
			    preparedStatement.setDate(5, sqlDate);
				
			    rowsAffected = preparedStatement.executeUpdate();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		response.setStatus(HttpServletResponse.SC_OK);
		if (rowsAffected == 1) {
			response.getWriter().write("1");
		} else {
			response.getWriter().write("0");
		}
	}
}