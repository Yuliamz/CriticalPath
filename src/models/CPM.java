package models;

import java.util.List;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 *
 * @author Yuliamz
 */
public class CPM {
    public Graph graph;
    public List<Node> criticalPathLocal;
    public CPM(List<Actividad> actividades) {
        graph = new Graph(actividades);
        applyUPTimes();
        applyDownTimes();
    }
    
    private double getpathHolgura(List<Node> path){
        double holgura = path.get(0).getHolgura();
        for (int i = 1; i < path.size(); i++) {
            holgura+=path.get(i).getHolgura();
        }
        return holgura;
    }
    
    private void applyUPTimes(){
        for (List<Node> nodePath : graph.getNodePaths()) {
            nodePath.get(0).setUl(0);
            for (int i = 1; i < nodePath.size(); i++) {
                nodePath.get(i).setUl(nodePath.get(i-1).getUR());
            }
        }
    }
    
    private void applyDownTimes(){
        for (List<Node> nodePath : graph.getNodePaths()) {
            nodePath.get(nodePath.size()-1).setDR(nodePath.get(nodePath.size()-1).getUR());
            for (int i = nodePath.size()-2; i > -1; i--) {
                nodePath.get(i).setDR(nodePath.get(i+1).getDl());
            }
        }
    }
    
    public List<Node> getCriticalPath(){
        double cp = getpathHolgura(graph.getNodePaths().get(0));
        int actual =0;
        for (int i = 1; i < graph.getNodePaths().size(); i++) {
            if (getpathHolgura(graph.getNodePaths().get(i))<=cp) {
                cp=getpathHolgura(graph.getNodePaths().get(i));
                actual=i;
            }
        }
        criticalPathLocal = graph.getNodePaths().get(actual);
        return graph.getNodePaths().get(actual);
    }
    
    public double getProbabilityFinishIn(int x){
        double desviacion = 0;
        desviacion = criticalPathLocal.stream().map((criticalPathLocal1) -> criticalPathLocal1.getActividad().getDesviacionStandar()).reduce(desviacion, (accumulator, _item) -> accumulator + _item);
        double M = criticalPathLocal.get(criticalPathLocal.size()-1).getDR();
        return new NormalDistribution().cumulativeProbability((x-M)/desviacion)*100;
    }
    
}