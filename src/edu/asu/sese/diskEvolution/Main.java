package edu.asu.sese.diskEvolution;

import javax.swing.JOptionPane;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;
import edu.asu.sese.diskEvolution.view.GraphicalApplication;

public class Main {
    public static void main(String[] args) {
    	final long startTime = System.nanoTime();
        DiskSimulation simulation = new DiskSimulation();   
        System.out.print("Running DiskSimulation");
        
        new GraphicalApplication(simulation);
       
        final long duration = System.nanoTime() - startTime ;
        JOptionPane.showMessageDialog(null, "Time =" + duration/1000000 + " MilliSeconds");
    }
    
}