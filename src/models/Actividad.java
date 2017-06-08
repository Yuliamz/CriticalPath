package models;

import java.util.Set;

public class Actividad {

    private String name;
    private Set<Character> predecesor;
    private int TiempoOptimista;
    private int TiempoProbable;
    private int TiempoPesimista;
    private int TiempoEsperado;
    private Double varianza;
    private double desviacionStandar;
    private boolean isCPM;
    
    public Actividad(String name, Set<Character> predecesor, int TiempoOptimista, int TiempoProbable, int TiempoPesimista) {
        this.name = name;
        this.predecesor = predecesor;
        this.TiempoOptimista = TiempoOptimista;
        this.TiempoProbable = TiempoProbable;
        this.TiempoPesimista = TiempoPesimista;
        this.TiempoEsperado = (TiempoOptimista + 4 * (TiempoProbable) + TiempoPesimista)/6;
        varianza = Math.pow(((double)(this.TiempoPesimista-this.TiempoOptimista)/6),2);
        desviacionStandar += varianza;
        this.isCPM = false;
    }
    
    public String getName() {
        return name;
    }

    public double getDesviacionStandar() {
        return Math.sqrt(desviacionStandar);
    }
    
    public Set<Character> getPredecesor() {
        return predecesor;
    }

    public int getTiempoOptimista() {
        return TiempoOptimista;
    }

    public int getTiempoProbable() {
        return TiempoProbable;
    }

    public int getTiempoPesimista() {
        return TiempoPesimista;
    }

    public int getTiempoEsperado() {
        return TiempoEsperado;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPredecesor(Set<Character> predecesor) {
        this.predecesor = predecesor;
    }

    public void setTiempoOptimista(int TiempoOptimista) {
        this.TiempoOptimista = TiempoOptimista;
    }

    public void setTiempoProbable(int TiempoProbable) {
        this.TiempoProbable = TiempoProbable;
    }

    public void setTiempoPesimista(int TiempoPesimista) {
        this.TiempoPesimista = TiempoPesimista;
    }

    public void setTiempoEsperado(int TiempoEsperado) {
        this.TiempoEsperado = TiempoEsperado;
    }

    public double getVarianza() {
        return varianza;
    }
    
    public Object[] datos() {
        Object[] info = {name, predecesor==null?"":predecesor.toString().replace("[", "").replace("]", "").replace(",", " "), TiempoOptimista, TiempoProbable, TiempoPesimista, TiempoEsperado, varianza};
        return info;
    }

    public void setIsCPM(boolean isCPM) {
        this.isCPM = isCPM;
    }

    public boolean isIsCPM() {
        return isCPM;
    }
}