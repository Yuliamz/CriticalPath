package views;

import controller.Actions;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

public class MyMenuBar extends JMenuBar {

    private static final long serialVersionUID = 1L;

    public MyMenuBar(Controller controller) {

        JMenu menuArchivo = new JMenu("File");
        JMenuItem itemAddActividad = new JMenuItem("Agregar Actividad");
        itemAddActividad.addActionListener(controller);
        itemAddActividad.setActionCommand(Actions.SHOW_DIALOG.name());
        itemAddActividad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        menuArchivo.add(itemAddActividad);
        
        JMenuItem itemCalculateCPM = new JMenuItem("Calcular Ruta Critica");
        itemCalculateCPM.addActionListener(controller);
        itemCalculateCPM.setActionCommand(Actions.CALCULATE_CRITICAL_PATH.name());
        itemCalculateCPM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        menuArchivo.add(itemCalculateCPM);
        
        add(menuArchivo);
        
        
    }
}
