package models;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Yuliamz
 */
public class CPM {
    public Graph graph;

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
//            System.out.println("=============UP TIMES para "+nodePath.get(0).getActividad().getName()+"=============");
//                System.out.println(nodePath.get(0).getUl()+", "+nodePath.get(0).getUR());
            for (int i = 1; i < nodePath.size(); i++) {
                nodePath.get(i).setUl(nodePath.get(i-1).getUR());
//                System.out.println("=============UP TIMES para "+nodePath.get(i).getActividad().getName()+"=============");
//                System.out.println(nodePath.get(i).getUl()+", "+nodePath.get(i).getUR());
            }
        }
    }
    
    private void applyDownTimes(){
        for (List<Node> nodePath : graph.getNodePaths()) {
            nodePath.get(nodePath.size()-1).setDR(nodePath.get(nodePath.size()-1).getUR());
//            System.out.println("===============DOWN TIMES para "+nodePath.get(nodePath.size()-1).getActividad().getName()+"==============");
//                System.out.println(nodePath.get(nodePath.size()-1).getDR()+", "+nodePath.get(nodePath.size()-1).getDl());
            for (int i = nodePath.size()-2; i > -1; i--) {
                nodePath.get(i).setDR(nodePath.get(i+1).getDl());
//                System.out.println("===============DOWN TIMES para "+nodePath.get(i).getActividad().getName()+"==============");
//                System.out.println(nodePath.get(i).getDR()+", "+nodePath.get(i).getDl());
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
        
        return graph.getNodePaths().get(actual);
    }
    
}