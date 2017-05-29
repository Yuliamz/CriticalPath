package models;

/**
 *
 * @author Pedro
 * @param <T>
 */
public class Node<T> {

    private double Ul;
    private double UR;
    private double Dl;
    private double DR;
    private Actividad actividad;
    private TypeNode typeNode;
    
    public Node(Actividad actividad) {
        this.UR = 0;
        this.Ul = 0;
        this.DR = 0;
        this.Dl = 0;
        this.typeNode = actividad.getPredecesor()!=null?TypeNode.INTERMEDIO:TypeNode.INICIAL;
        this.actividad = actividad;
    }

    public double getUR() {
        return UR;
    }

    public void setUR(double val) {
        this.UR = val + actividad.getTiempoEsperado();
    }

    public double getUl() {
        return Ul;
    }

    public void setUl(double val) {
        if (this.Ul < val) {
            this.Ul = val;
        }
        setUR(this.Ul);
    }

    public void setDR(double val) {
        if (DR != 0 && val < DR) {
            this.DR = val;
        }
        if (DR == 0) {
            this.DR = val;
        }
        setDl(this.DR);
    }

    public double getDR() {
        return DR;
    }

    public void setDl(double Dl) {
        this.Dl = DR - actividad.getTiempoEsperado();
    }

    public double getDl() {
        return Dl;
    }

    public double getRh() {
        return this.UR - this.DR;
    }

    public double getLh() {
        return this.Ul - this.Dl;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public TypeNode getTypeNode() {
        return typeNode;
    }

    public void setTypeNode(TypeNode typeNode) {
        this.typeNode = typeNode;
    }
    public double getHolgura(){
        return Math.abs(getLh()+getRh());
    }

    @Override
    public String toString() {
        return actividad.getName()+": UL: "+Ul+", UR: "+UR+", DL: "+Dl+", DR: "+DR;
    }
}
