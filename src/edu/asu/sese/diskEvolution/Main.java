package edu.asu.sese.diskEvolution;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;
import edu.asu.sese.diskEvolution.view.DiskView;


public class Main {

    public static void main(String[] args) {
        DiskSimulation simulation = new DiskSimulation();
                
        System.out.print("Running DiskSimulation");
        
        DiskView view = new DiskView(simulation);
        
        view.setVisible(true);
    }

}
