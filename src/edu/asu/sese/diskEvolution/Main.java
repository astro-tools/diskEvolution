package edu.asu.sese.diskEvolution;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;
import edu.asu.sese.diskEvolution.view.GraphicalApplication;

public class Main {
    public static void main(String[] args) {
        DiskSimulation simulation = new DiskSimulation();   
        System.out.print("Running DiskSimulation");
        
        new GraphicalApplication(simulation);       
    }
    
}