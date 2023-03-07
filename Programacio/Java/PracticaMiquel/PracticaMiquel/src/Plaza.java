public class Plaza {
    //Atributs
    private int id;
    private char tipo;
    private boolean adjudicada;
    private Persona persona;
    
    
    //Constructores
    public Plaza(int id, char tipo, boolean adjudicada, Persona persona) {
        this.id = id;
        this.tipo = tipo;
        this.adjudicada = adjudicada;
        this.persona = persona;
    }
    
    public Plaza(){
        System.out.println("Es necesario assignar valores para crear una Plaza");
    }
    
    
    //toString
    @Override
    public String toString() {
        return "ID Plaza:" + id + 
                "\nTipo: " + tipo + 
                "\nAdjudicada: " + adjudicada + 
                "\nPersona: " + persona;
    }
    
    
    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public boolean isAdjudicada() {
        return adjudicada;
    }

    public void setAdjudicada(boolean adjudicada) {
        this.adjudicada = adjudicada;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
