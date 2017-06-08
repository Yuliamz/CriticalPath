package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import models.Actividad;
import models.CPM;
import models.ManejadorActividades;
import models.Node;
import views.CreateActividad;
import views.Help;
import views.MainWindow;

/**
 *
 * @author Pedro
 */
public class Controller implements ActionListener {

    private MainWindow mainWindow;
    private CreateActividad createActividad;
    private ManejadorActividades manejadorActividades;
    private Help help;

    public Controller() {
        mainWindow = new MainWindow(this);
        createActividad = new CreateActividad(this);
        manejadorActividades = new ManejadorActividades();
        help = new Help();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Actions.valueOf(e.getActionCommand())) {
            case CREATE:
                CreateActividad();
                break;
            case SHOW_DIALOG:
                createActividad.setVisible(true);
                break;
            case CALCULATE_CRITICAL_PATH:
                calcularCPM();
                break;
            case HELP:
                help.setResizable(false);
                help.setVisible(true);
//                help.setModal(false);
                help.setLocationRelativeTo(mainWindow);
                break;
        }
    }

    public void CreateActividad() {
        Actividad actividad = createActividad.createactividad();
        manejadorActividades.addActividad(actividad);
        mainWindow.addInfo(actividad.datos());
        mainWindow.paintGraph(actividad.getName());
        if (actividad.getPredecesor()!=null) {
            char aux[] = actividad.getPredecesor().toString().replace("[", "").replace("]", "").replace(",", " ").toCharArray();
            System.out.println("======================= " + aux + "=================== " + aux);
            for (int i = 0; i < aux.length; i++) {
                mainWindow.paintVertices(String.valueOf(aux[i]), actividad.getName());
            }
        }
    }

    private void calcularCPM() {
//        ArrayList<Actividad> aux = manejadorActividades.getListActividad();
//        for (Actividad actividad : aux) {
//            System.out.println("=================actividad: "+actividad.getName());
//        }
        CPM cpm = new CPM(manejadorActividades.getListActividad());
        for (Node node : cpm.getCriticalPath()) {
            node.getActividad().setIsCPM(true);
            System.out.println("critical path: " + node.getActividad().getName());
        }
        mainWindow.addCriticalPaht(cpm.getCriticalPath());
        calcularDesviacionEstandar(cpm.getCriticalPath());
        calcularDesviacionEstandar();
    }
    
    public void calcularDesviacionEstandar(){
        double desviacion = 0;
        for (Actividad actividad : manejadorActividades.getListActividad()) {
            if (actividad.isIsCPM() == true) {
                desviacion +=actividad.getVarianza();
            }
        }
        double desviacionEstandar = Math.pow(desviacion, 0.5);
        mainWindow.addDesviacionEstandar(desviacionEstandar);
    }

    private void calcularDesviacionEstandar(List<Node> criticalPath) {
        for (Node nodo : criticalPath) {
            nodo.getActividad().setIsCPM(true);
        }
    }
}