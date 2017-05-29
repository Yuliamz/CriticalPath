
package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import models.Actividad;
import models.Puente;
import org.jgrapht.DirectedGraph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
/**
 *
 * @author Yuliamz
 */
public class GraphController {
    private DirectedGraph<String, DefaultEdge> dg;
    private List<List<Character>> paths;
    private List<Actividad> actividades;
    AllDirectedPaths adp;
    public GraphController(List<Actividad> actividades, List<Puente> puentes) {
        paths = new ArrayList<>();
        this.actividades=actividades;
        dg = new DefaultDirectedGraph<>(DefaultEdge.class);
        addVertexs(actividades);
        addEdges(puentes);
        adp = new AllDirectedPaths(dg);
    }
    
    public List<List<Character>> getAllPaths(){
        List<GraphPath> list = new LinkedList<>();
        Set s = new HashSet();
        Set d = new HashSet();
        getInitialActivities(actividades).stream().forEach((actividad) -> {s.add(actividad.getName());});
        d.add(actividades.get(actividades.size()-1).getName());
        list = adp.getAllPaths(s, d, true, Integer.MAX_VALUE);
        for (GraphPath graphPath : list) {
            paths.add(grap2Char(graphPath));
        }
        return paths;
    }
    
    private void addVertexs(List<Actividad> actividades){
        for (Actividad actividad : actividades) {
            if (!dg.containsVertex(actividad.getName())) {
                dg.addVertex(actividad.getName().toUpperCase());
            }
        }
    }
    
    private void addEdges(List<Puente> puentes){
        for (Puente puente : puentes) {
            if (!dg.containsEdge(puente.getFrom().getName(), puente.getTo().getName())) {
                dg.addEdge(puente.getFrom().getName(), puente.getTo().getName());
            }
        }
    }
    
    public List<GraphPath> getPaths(String VerexFrom, String Vertexto){
        return adp.getAllPaths(VerexFrom, Vertexto, true, Integer.MAX_VALUE);
    }
    
    public List<Actividad> getInitialActivities(List<Actividad> actividades){
        List<Actividad> initActivities = new ArrayList<>();
        for (Actividad actividad : actividades) {
            if (actividad.getPredecesor() ==null) {
                initActivities.add(actividad);
            }
        }
        return initActivities;
    }
    
    
    
    private List<Character> grap2Char(GraphPath gp){
        List<Character> characters = new ArrayList<>();
    String path = Arrays.toString(gp.getEdgeList().toArray())
                                .replace("[", "")
                                .replace("]", "")
                                .replaceAll(" +", "")
                                .replaceAll("[:,()]", "")
                                .replaceAll("(.)\\1", "$1");
        for (int i = 0; i < path.length(); i++) {
            characters.add(path.charAt(i));
        }
        return characters;
    }
    
    
    
//    public static void main(String[] args) {
//        
//        DirectedGraph<String, DefaultEdge> dg = new DefaultDirectedGraph<>(DefaultEdge.class);
//        dg.addVertex("A");
//        dg.addVertex("B");
//        dg.addVertex("C");
//        dg.addVertex("D");
//        dg.addVertex("E");
//        dg.addVertex("F");
//        dg.addVertex("G");
//        dg.addVertex("H");
//        dg.addVertex("I");
//        dg.addVertex("J");
//        dg.addEdge("A", "D");
//        dg.addEdge("B", "E");
//        dg.addEdge("C", "F");
//        dg.addEdge("C", "G");
//        dg.addEdge("D", "H");
//        dg.addEdge("E", "I");
//        dg.addEdge("F", "I");
//        dg.addEdge("G", "I");
//        dg.addEdge("H", "J");
//        dg.addEdge("I", "J");
//        
//        AllDirectedPaths adp = new AllDirectedPaths(dg);
//        List<GraphPath> list = new LinkedList<>();
//        System.out.println("CAMINOS DESDE A HASTA J");
//        list = adp.getAllPaths("A", "J", true, Integer.MAX_VALUE);
//        for (GraphPath graphPath : list) {
//            System.out.println(Arrays.toString(graphPath.getEdgeList().toArray()));
//        }
//        System.out.println("CAMINOS DESDE B HASTA J");
//        list = adp.getAllPaths("B", "J", true, Integer.MAX_VALUE);
//        for (GraphPath graphPath : list) {
//            System.out.println(Arrays.toString(graphPath.getEdgeList().toArray()));
//        }
//        System.out.println("CAMINOS DESDE C HASTA J");
//        list = adp.getAllPaths("C", "J", true, Integer.MAX_VALUE);
//        for (GraphPath graphPath : list) {
//            System.out.println(Arrays.toString(graphPath.getEdgeList().toArray()));
//            System.out.println(Arrays.toString(graphPath.getEdgeList().toArray())
//                                .replace("[", "")
//                                .replace("]", "")
//                                .replaceAll(" +", "")
//                                .replaceAll("[:,()]", "")
//                                .replaceAll("(.)\\1", "$1")
//                                );
//        }
//        Set s = new HashSet();
//        Set d = new HashSet();
//        s.add("A");
//        s.add("B");
//        s.add("C");
//        d.add("J");
//        
//        list = adp.getAllPaths(s, d, true, Integer.MAX_VALUE);
//        for (GraphPath graphPath : list) {
//            System.out.println(Arrays.toString(graphPath.getEdgeList().toArray()));
//        }
//    }
}
