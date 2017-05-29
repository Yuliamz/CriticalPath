
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Actividad;
import views.CreateActividad;
import views.MainWindow;

/**
 *
 * @author Pedro
 */
public class Controller implements ActionListener{
    
    private MainWindow mainWindow;
    private CreateActividad createActividad;
    
    public Controller() {
        mainWindow = new MainWindow(this);
        createActividad = new CreateActividad(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Actions.valueOf(e.getActionCommand())){
            case CREATE:
                CreateActividad();
                break;
                
            case SHOW_DIALOG:
                createActividad.setVisible(true);
                break;
        }
    }
    
    public void CreateActividad(){
        Actividad actividad = createActividad.createactividad();
        mainWindow.addInfo(actividad.datos());
    }
    
}
