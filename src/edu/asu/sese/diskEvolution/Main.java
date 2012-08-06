package edu.asu.sese.diskEvolution;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;
import edu.asu.sese.diskEvolution.view.DiskView;
import edu.asu.sese.diskEvolution.view.GraphicalApplication;


public class Main {

    public static void main(String[] args) {
        DiskSimulation simulation = new DiskSimulation();
                
        System.out.print("Running DiskSimulation");
        
        GraphicalApplication graphicalApp = new GraphicalApplication();
        
        DiskView view = new DiskView(simulation);
        
        view.setVisible(true);
    }

}
