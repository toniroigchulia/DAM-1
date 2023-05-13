import java.util.*;
public class App {

    public static void adjudicarPlazas(ArrayList<Persona> interins, ArrayList<Plaza> listaPlazas){
        for (int i = 0; i < listaPlazas.size(); i++){
            if (!listaPlazas.get(i).isAdjudicada()){
                
                if (listaPlazas.get(i).getTipo() == 'D'){
                    //Buscar docente
                    int index=-1;
                    for (int j = 0; j < interins.size(); j++){
                    
                        if (interins.get(j) instanceof Docente){
                        
                            if (index == -1){
                            
                                index = j;
                                
                            } else if (((Docente)interins.get(index)).getPunto() < ((Docente)interins.get(j)).getPunto()) {
                                
                                index = j;
                                
                            }
                        }
                        
                        if (index != 1) {
                            listaPlazas.get(i).setPersona(interins.get(index));
                            listaPlazas.get(i).setAdjudicada(true);
                            interins.remove(index);
                        }
                    }
                    
                } else {
                    //Buscar sanitario
                    int index=-1;
                    for (int j = 0; j < interins.size(); j++){
                    
                        if (interins.get(j) instanceof Sanitario){
                        
                            if (index == -1){
                            
                                index = j;
                                
                            } else if (((Sanitario)interins.get(index)).getDia() < ((Sanitario)interins.get(j)).getDia()) {
                                
                                index = j;
                                
                            }
                        }
                        
                        if (index != 1) {
                            listaPlazas.get(i).setPersona(interins.get(index));
                            listaPlazas.get(i).setAdjudicada(true);
                            interins.remove(index);
                        }
                    }
                }
            }
        }
    }
    
    public static void mostrarAdjudicaciones(ArrayList<Plaza> listaPlazas){
        for (int i = 0; i < listaPlazas.size(); i++){
            if (listaPlazas.get(i).getTipo() == 'S'){
            
                System.out.println(listaPlazas.get(i).toString());
                
            }
        }
        
        for (int i = 0; i < listaPlazas.size(); i++){
            if (listaPlazas.get(i).getTipo() == 'D'){
            
                System.out.println(listaPlazas.get(i).toString());
                
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        
    
    }
}
