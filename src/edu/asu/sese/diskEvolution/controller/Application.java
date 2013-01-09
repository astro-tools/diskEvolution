package edu.asu.sese.diskEvolution.controller;

import edu.asu.sese.diskEvolution.model.*;
import edu.asu.sese.diskEvolution.util.GridFactory;
import edu.asu.sese.diskEvolution.util.SimpleObservable;

public class Application extends SimpleObservable {

    private InitialConditions initialConditions;
    private GridFactory gridFactory;
    private SimulationRunner runner;

    public Application() {
        setupApplication();
    }

    public void setupApplication() {
        initialConditions = new InitialConditions();
        gridFactory = new GridFactory();
        gridFactory.initializeParameters();
        runner = new SimulationRunner(this);
    }
    
    public InitialConditions getInitialConditions() {
        return initialConditions;
    }

    public GridFactory getGridFactory() {
        return gridFactory;
    }

    public SimulationRunner getRunner() {
        return runner;
    }

}
