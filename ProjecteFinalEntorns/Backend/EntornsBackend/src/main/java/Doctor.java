import java.time.LocalDate;
import java.util.ArrayList;

public class Doctor extends Persona {
	
	String pass;
	LocalDate lastlog;
	String session;
	ArrayList<Xip> releaseList;
	
	
	Doctor (){}
	
	Doctor (String name, String mail, String pass, LocalDate lastlog, String session){
		
		super(name, mail);
		this.pass = pass;
		this.lastlog = lastlog;
		this.session = session;
		
	}
	
	void setreleaseList(ArrayList<Xip> releaseList){
		
		this.releaseList = releaseList;
		
	}
	
	void Login(String mail, String pass) {
		
	}
	
	boolean isLogged(String mail, String pass) {
		
	}
	
	@Override
	void Load(String id) {
		// TODO Auto-generated method stub
		
	}
	
	void loadReleaseLit() {
		
	}
	
	String getTable() {
		
	}
	
	
}
