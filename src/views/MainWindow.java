/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.Controller;
import java.awt.BorderLayout;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pedro
 */
public class MainWindow extends JFrame {

    private static final String[] vector = {"Actividad", "Predecesor", "Tiempo Optimista","Tiempo Probable", "Tiempo Pesimista", "Tiempo Esperado", "Varianza", "Desviacion Estandar"};
    private static DefaultTableModel model;
    private static JTable registroTabla;
    private JScrollPane jscrollPane2;

    public MainWindow(Controller controller) throws HeadlessException {
        setTitle("Metodo De La Ruta Critica");
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
         setJMenuBar(new MyMenuBar(controller));
        model = new DefaultTableModel();
        model.setColumnIdentifiers(vector);
        registroTabla = new JTable(model);

        jscrollPane2 = new JScrollPane(registroTabla);
        jscrollPane2.setBounds(0, 100, 800, 200);
        add(jscrollPane2, BorderLayout.CENTER);
        
        this.setVisible(true);
    }
    
    public void addInfo(Object[] objects){
        model.addRow(objects);
    }
}
