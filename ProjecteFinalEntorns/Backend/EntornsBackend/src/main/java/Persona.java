
public abstract class Persona {
	
	String name;
	String mail;
	
	Persona(){};
	
	Persona(String name, String mail){
		
		this.name = name;
		this.mail = mail;
		
	};
	
	abstract void Load (String id);
	
}
