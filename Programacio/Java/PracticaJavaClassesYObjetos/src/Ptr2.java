public class Ptr2 {

    private int ejex;
    private int ejey;   
    private int area;
    
    public Ptr2(int ejex, int ejey){
        
        this.ejex = ejex;
        
        this.ejey = ejey;
        
        this.area = ejex * ejey;
        
    }
    
    public void setEjex(int x){
    
        this.ejex = x;
        
        this.area = this.ejex * this.ejey;
        
    }
    
    public void setEjey(int y){
    
        this.ejex = y;
        
        this.area = this.ejex * this.ejey;
        
    }
    
    public int getEjex(){
        
        return this.ejex;
        
    }
    
    public int getEjey(){
        
        return this.ejey;
        
    }
    
    public void estePunto(){
    
        System.out.println(this.ejex + this.ejey + this.area);
    
    }
}   

