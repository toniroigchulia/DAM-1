// Classe abstracta Persona
public abstract class Persona {
	
	// Atributos
	String name;
	String mail;
	
	// Constructor vacio y con todos los atributos
	Persona(){};
	
	Persona(String name, String mail){
		
		this.name = name;
		this.mail = mail;
		
	};
	
	// Declaramos metodo abstracto
	abstract void load (String id);
	
	// Getters And Setters
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
