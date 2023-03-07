public class Sanitario extends Persona {
    //Atributs
    private String especialidad;
    private int dia;
    
    
    //Constructor
    public Sanitario(){
        super();
        this.setDia(0);
    }
    
    public Sanitario(String nombre, String apellido1, String apellido2, Integer id, Integer contador, String especialidad, int dia) {
        super(nombre, apellido1, apellido2);
        this.setEspecialidad(especialidad);
        this.setDia(dia);
    }
    
    
    //toString
    @Override
    public String toString() {
        return super.toString() + 
                "\nEspecialidad:" + especialidad + 
                "\nDia: " + dia;
    }
    
    
    //Getters and Setters
    public String getEspecialidad() {
        return especialidad;
    }


    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }


    public int getDia() {
        return dia;
    }


    public void setDia(int dia) {
        this.dia = dia;
    }
}

