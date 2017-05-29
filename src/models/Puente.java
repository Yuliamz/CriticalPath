package models;

public class Puente {
    
    private Actividad from;
    private Actividad to;

    public Puente(Actividad from, Actividad to) {
        this.from = from;
        this.to = to;
    }

    public Actividad getFrom() {
        return from;
    }

    public void setFrom(Actividad from) {
        this.from = from;
    }

    public Actividad getTo() {
        return to;
    }

    public void setTo(Actividad to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return from.getName()+"=>"+to.getName();
    }
    
}
