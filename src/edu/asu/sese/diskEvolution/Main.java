package edu.asu.sese.diskEvolution;

import edu.asu.sese.diskEvolution.controller.Application;
import edu.asu.sese.diskEvolution.view.GraphicalApplication;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running diskEvolution application\n");

        Application application = new Application();           
        new GraphicalApplication(application);       
    }
    
}