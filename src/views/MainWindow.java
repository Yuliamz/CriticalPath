/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import controller.Controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Node;

/**
 *
 * @author Pedro
 */
public class MainWindow extends JFrame {

    private static final String[] vector = {"Actividad", "Predecesor", "Tiempo Optimista", "Tiempo Probable", "Tiempo Pesimista", "Tiempo Esperado", "Varianza", "Desviacion Estandar"};
    private static DefaultTableModel model;
    private static JTable registroTabla;
    private JScrollPane jscrollPane2;
    private mxGraph graph;
    private mxGraphComponent graphComponent;
    protected static HashMap m = new HashMap();
    private int count;
    private JLabel jlCriticalPaht;

    public MainWindow(Controller controller) throws HeadlessException {
        setTitle("Metodo De La Ruta Critica");
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/imgs/icon.png")).getImage());
        setJMenuBar(new MyMenuBar(controller));

        JLabel jLabel = new JLabel(new ImageIcon(getClass().getResource("/imgs/image.png")));
        this.add(jLabel, BorderLayout.NORTH);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(vector);
        registroTabla = new JTable(model);

        JPanel jPanelCenter = new JPanel();
        jPanelCenter.setLayout(new GridLayout(2, 1));
        add(jPanelCenter, BorderLayout.CENTER);

        jscrollPane2 = new JScrollPane(registroTabla);
        jPanelCenter.add(jscrollPane2);

        JPanel jPanleGraph = new JPanel();
        jPanleGraph.setBorder(BorderFactory.createTitledBorder("Grafo"));
        JScrollPane jScrollPane = new JScrollPane(jPanleGraph);
        jPanelCenter.add(jScrollPane);

        graph = new mxGraph();
        graphComponent = new mxGraphComponent(graph);
        graphComponent.setPreferredSize(new Dimension(1100, 400));
        jPanleGraph.add(graphComponent);
        
        jlCriticalPaht = new JLabel();
        add(jlCriticalPaht, BorderLayout.SOUTH);

//        paintGraph("A");
//        paintGraph("B");
//        paintVertices("A", "B");
        this.setVisible(true);
    }

    public void addInfo(Object[] objects) {
        model.addRow(objects);
    }

    public void paintGraph(String name) {
        if (count <= 1000) {
            this.graph.getModel().beginUpdate();
            Object parent = this.graph.getDefaultParent();
            Object v1 = this.graph.insertVertex(parent, null, name, 25 + count, 30, 60, 60, "shape=ellipse");
            this.m.put(name, v1);
            this.graph.getModel().endUpdate();
            count += 80;
            System.out.println("XXXXXXXXXXXXXXXXXX: " + count);
//            criticalPath(name);
        }
    }

    public void paintVertices(String antecesor, String predecesor) {
        Object parent = this.graph.getDefaultParent();
        Object v1 = this.m.get(antecesor);
        Object v2 = this.m.get(predecesor);
        this.graph.insertEdge(parent, null, null, v1, v2);
    }

    public void criticalPath(String name) {
//        this.graph.getModel().beginUpdate();
//        Object parent = this.graph.getDefaultParent();
        Object v1 = this.graph.getEdges(name);
        graph.getModel().setStyle(v1, "fillColor=#FF0000;");
//        this.graph.setCellStyle("fillColor=#FF0000;", (Object[]) v1);
//        Object v1 = graph.insertVertex(parent, null, name, 20, 20, 80, 30, "fillColor=#FF0000;");
        this.graph.getModel().endUpdate();
    }
    
    public void addCriticalPaht(List<Node> list){
        for (Node node : list) {
            jlCriticalPaht.setText(node.getActividad().getName());
            System.out.println("critical path: " + node.getActividad().getName());
        }
    }
}
