package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import models.Actividad;
import models.CPM;
import models.Node;

public class testCPM {
 
//    public static void main(String[] args) {
//        //lista de actividades
//        List<Actividad> actividades = new ArrayList<>();
//        
//        //predecesores en forma de set
//        Set<Character> pD = new HashSet<>(Arrays.asList('A'));
//        Set<Character> pE = new HashSet<>(Arrays.asList('B'));
//        Set<Character> pF = new HashSet<>(Arrays.asList('C'));
//        Set<Character> pG = new HashSet<>(Arrays.asList('C'));
//        Set<Character> pH = new HashSet<>(Arrays.asList('D'));
//        Set<Character> pI = new HashSet<>(Arrays.asList('E','F','G'));
//        Set<Character> pJ = new HashSet<>(Arrays.asList('H','I'));
//        
//        //actividades
//        Actividad a = new Actividad("A", null, 5, 6, 7);
//        Actividad b = new Actividad("B", null, 1, 3, 5);
//        Actividad c = new Actividad("C", null, 1, 4, 7);
//        Actividad d = new Actividad("D", pD, 1, 2, 3);
//        Actividad e = new Actividad("E", pE, 1, 2, 9);
//        Actividad f = new Actividad("F", pF, 1, 5, 9);
//        Actividad g = new Actividad("G", pG, 2, 2, 8);
//        Actividad h = new Actividad("H", pH, 4, 4, 10);
//        Actividad i = new Actividad("I", pI, 2, 5, 10);
//        Actividad j = new Actividad("J", pJ, 2, 2, 8);
//        
//        //agregar las actividades a la lista
//        actividades.add(a);
//        actividades.add(b);
//        actividades.add(c);
//        actividades.add(d);
//        actividades.add(e);
//        actividades.add(f);
//        actividades.add(g);
//        actividades.add(h);
//        actividades.add(i);
//        actividades.add(j);
//        
//        //crear el cpm
//        CPM cpm = new CPM(actividades);
//        
//        //obtener la ruta crtica
//        System.out.println("==============La ruta crtica es================");
//        for (Node criticalPath : cpm.getCriticalPath()) {
//            System.out.println(criticalPath.getActividad().getName());
//        }
//    }
}