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
        JMenuItem itemAddProducto = new JMenuItem("Agregar Actividad");
        itemAddProducto.addActionListener(controller);
        itemAddProducto.setActionCommand(Actions.SHOW_DIALOG.name());
        itemAddProducto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        menuArchivo.add(itemAddProducto);
        add(menuArchivo);
        
    }
}
