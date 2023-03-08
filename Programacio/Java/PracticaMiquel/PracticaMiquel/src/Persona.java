public class Persona {
    //Atributs
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int id;
    private int contador = 0;
    
    
    //Constructor
    public Persona(){
        this.setId(contador);
        setContador();
    }
    
    public Persona(String nombre, String apellido1, String apellido2) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.setId(contador);
        setContador();
    }
    
    
    //toString()
    @Override
    public String toString(){
        return "\nID:" +  id + 
                "\nNombre:"+ nombre +
                "\nApellido1:"+ apellido1 +
                "\nApellido2:"+ apellido2;
    }
    
    //Getters and Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido1() {
        return apellido1;
    }
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }
    
    public String getApellido2() {
        return apellido2;
    }
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getContador() {
        return contador;
    }
    public void setContador() {
        this.contador = contador + 1;
    }
}

