
public abstract class Persona {
	

	String name;
	String mail;
	
	Persona(){};
	
	Persona(String name, String mail){
		
		this.name = name;
		this.mail = mail;
		
	};
	
	abstract void load (String id);
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
}
