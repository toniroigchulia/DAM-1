import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    
    // Sobrescribimos la classe service del servlet que esla encargada de manejar las peticiones para que cuando le pidan concretamente el OPTIONS
    // poder llamar a nuestro metodo para asi poder añadir los permisos correspondientes a la peticion y que asi no de problema de CORS todas las demas
    // peticiones que no sean OPTIONS las trata el service de forma estandard. (Esto solo es necesario al usar el POST)
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if ("OPTIONS".equals(request.getMethod())) {
    		
    		handleOptions(request, response);
    	} else {
    		
    		super.service(request, response);
    	}
    }
    
    // Nuestro metodo para tratar el OPTIONS y añadir el CORS
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
		
		// Añadimos permisos de CORS al mensaje que devolveremos
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		
		// Guardamos en variables lo que nos llega del frontend
		String email = request.getParameter("mail");
		String session = request.getParameter("session");
		String idPatient = request.getParameter("idPatient");
		
		int idXip = Integer.parseInt(request.getParameter("idXip"));
		int idMed = Integer.parseInt(request.getParameter("idMed"));
		
		// Guardamos el date y el formato en que lo transformaremos a continuacion
		String dataLimite = request.getParameter("date");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
		// Inicializamos la variable fuera del try/catch para usarla mas adelante
		java.sql.Date sqlDate = null;
        try {
        	
        	// Transformamos la fecha a sqlDate para poder insertarla en la base de datos
            java.util.Date utilDate = dateFormat.parse(dataLimite);
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            
        	e.printStackTrace();
        }
        
        // Cremos un objeto doctor y llamamos al isLogged para assegurarnos que la session sigue siendo valida
		Doctor doctor = new Doctor();
		boolean isLogged = doctor.isLogged(email, session);
		
		// Si la session es valida entramos al IF
		if (isLogged == true) {
			try {
				// Coneccion a la base de datos
				Connection conn = DataBaseConnection.getConnection();
				
				// Creamos la query con la informacion deseada
				String query = "INSERT INTO xip (id, doctor_mail, id_medicine, id_patient, date) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = conn.prepareStatement(query);
			    preparedStatement.setInt(1, idXip);
			    preparedStatement.setString(2, email);
			    preparedStatement.setInt(3, idMed);
			    preparedStatement.setString(4, idPatient);
			    preparedStatement.setDate(5, sqlDate);
				
			    // Hacemos el insert
			    preparedStatement.executeUpdate();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		// Si todo ha ido bien devolvemos "Ok" al frontend
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().write("Ok");
	}
}