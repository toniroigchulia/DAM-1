public class Persona {
    // Atributs
    private String nom;
    private int any;
    
    // Constructors
    public Persona(){
    
    }
    
    public Persona(String nom, int any){
        this.setNom(nom);
        this.setAny(any);
    }
    
    
    // Getters And Setters
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getAny() {
        return any;
    }
    public void setAny(int any) {
        this.any = any;
    }
    
}