package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import models.Actividad;
import models.CPM;
import models.ManejadorActividades;
import models.Node;
import views.CreateActividad;
import views.MainWindow;

/**
 *
 * @author Pedro
 */
public class Controller implements ActionListener {

    private MainWindow mainWindow;
    private CreateActividad createActividad;
    private ManejadorActividades manejadorActividades;

    public Controller() {
        mainWindow = new MainWindow(this);
        createActividad = new CreateActividad(this);
        manejadorActividades = new ManejadorActividades();
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
            System.out.println("critical path: " + node.getActividad().getName());
        }
//        mainWindow.addCriticalPaht(cpm.getCriticalPath());
    }

}
