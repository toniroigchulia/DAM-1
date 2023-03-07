public class Docente extends Persona{
    //Atributos
    private String titulo;
    private float punto;



    //Constructores
    public Docente(){
        super();
        this.setPunto(0);
    }
    
    public Docente(String nombre, String apellido1, String apellido2, String titulo, float punto){
        super(nombre, apellido1, apellido2);
        this.setTitulo(titulo);
        this.setPunto(punto);
    }
    
    
    //toString
    @Override
    public String toString() {
        return super.toString() + 
                "\nTitulo: " + titulo + 
                "\nPunto: " + punto;
    }
    
    
    //Getters and Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public float getPunto() {
        return punto;
    }
    
    public void setPunto(float punto) {
        this.punto = punto;
    }
}
