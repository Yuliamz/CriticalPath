package models;

import controller.GraphController;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {

    private List<Node> nodes;
    private List<Puente> puentes;
    private GraphController controller;
    private List<List<Node>> nodePaths;
    private List<List<Character>> charPaths;

    public Graph(List<Actividad> actividads) {
        nodes = new ArrayList<>();
        puentes = new ArrayList<>();
        nodePaths = new ArrayList<>();
        createNodes(actividads);
        createEdges(actividads);
        controller = new GraphController(actividads, puentes);
        charPaths = controller.getAllPaths();
        createAllNodePaths();
    }
    
    private void createAllNodePaths(){
        List<Node> list;
        for (List<Character> charPath : charPaths) {
            list = new LinkedList<>();
            for (Character character : charPath) {
                list.add(getNode(character));
            }
            nodePaths.add(list);
        }
    }
    
    private Node getNode(char name){
        for (Node node : nodes) {
            if (node.getActividad().getName().charAt(0)==name) {
                return node;
            }
        }
        return null;
    }
    
    public List<Node> getInitialNodes(){
     List<Node> iniNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node.getTypeNode()==TypeNode.INICIAL) {
                iniNodes.add(node);
            }
        }
        return iniNodes;
    }
    
    private void createNodes(List<Actividad> actividads){
        for (Actividad actividad : actividads) {
            nodes.add(new Node(actividad));
        }
    }
    
    private void createEdges(List<Actividad> actividades){        
        for (Actividad actividad : actividades) {
            if (actividad.getPredecesor()!=null) {
                for (Character predecesor : actividad.getPredecesor()) {
                    puentes.add(new Puente(getActividad(predecesor, actividades), actividad));
                }
            }
        }
    }
    
    private Actividad getActividad(Character name,List<Actividad> actividades){
       for (Actividad actividad : actividades) {
           if (actividad.getName().charAt(0)==(name)) {
               return actividad;
           }
       }
       return null;
   }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Puente> getPuentes() {
        return puentes;
    }

    public void setPuentes(List<Puente> puentes) {
        this.puentes = puentes;
    }

    public GraphController getController() {
        return controller;
    }

    public void setController(GraphController controller) {
        this.controller = controller;
    }

    public List<List<Node>> getNodePaths() {
        return nodePaths;
    }

    public void setNodePaths(List<List<Node>> nodePaths) {
        this.nodePaths = nodePaths;
    }

    public List<List<Character>> getCharPaths() {
        return charPaths;
    }

    public void setCharPaths(List<List<Character>> charPaths) {
        this.charPaths = charPaths;
    }
    
    
    
    
            
    
    
    
    
}
