package models;

import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author Pedro
 */
public class ManejadorActividades {
    
   private  ArrayList<Actividad> listActividad;
   
    public ManejadorActividades() {
        listActividad = new ArrayList<>();
    }
    
   public static Actividad createActividad(String name, Set<Character> predecesor, int TiempoOptimista, int TiempoProbable, int TiempoPesimista){
       return new Actividad(name, predecesor, TiempoOptimista, TiempoProbable, TiempoPesimista);
   }
   
   public void addActividad(Actividad actividad){
       listActividad.add(actividad);
   }
   
   public Actividad getActividad(String name){
       for (Actividad actividad : listActividad) {
           if (actividad.getName().equals(name)) {
               return actividad;
           }
       }
       return null;
   }

    public ArrayList<Actividad> getListActividad() {
        return listActividad;
    }
   
}
