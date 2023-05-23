import java.time.LocalDate;

public class Xip {
	
	int id;
	Medicine medicine;
	Patient patient;
	LocalDate date;
	
	Xip () {}
	
	Xip (int id, Medicine medicine, Patient patient, LocalDate date) {
		
		this.id = id;
		this.medicine = medicine;
		this.patient = patient;
		this.date = date;
		
	}
	
	void load(int id) {
		
	}
}