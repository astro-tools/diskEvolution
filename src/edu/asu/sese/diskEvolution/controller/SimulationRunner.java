package edu.asu.sese.diskEvolution.controller;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.model.InitialConditions;
import edu.asu.sese.diskEvolution.model.MassFlowCalculator;
import edu.asu.sese.diskEvolution.model.MassFlowGrid;
import edu.asu.sese.diskEvolution.model.MassMover;
import edu.asu.sese.diskEvolution.model.DensitySnapshotCollection;
import edu.asu.sese.diskEvolution.model.TimeStep;
import edu.asu.sese.diskEvolution.model.TracerDensityGrid;
import edu.asu.sese.diskEvolution.model.TracerFlowCalculator;
import edu.asu.sese.diskEvolution.model.TracerFlowGrid;
import edu.asu.sese.diskEvolution.model.TracerMover;
import edu.asu.sese.diskEvolution.model.ViscosityGrid;
import edu.asu.sese.diskEvolution.util.GridFactory;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class SimulationRunner {
	private TimeStep simulationTimeStep;
    private double totalDuration;
    private double snapshotInterval;
    
    private GridFactory gridFactory;
    private Application application;
    private DiskSimulation simulation;
/*    private DiskSimulation tracerSimulation;*/  
    private InitialConditions initialConditions;
    private MassMover massMover;
    private MassFlowCalculator massFlowCalculator;
    private DensitySnapshotCollection snapshotCollection;
/*    private TracerFlowCalculator tracerFlowCalculator;
    private TracerMover tracerMover;*/
	
	public SimulationRunner(Application diskSimulation) {
	    this.application = diskSimulation;
	    gridFactory = application.getGridFactory();
	    initialConditions = application.getInitialConditions();
	    simulationTimeStep = new TimeStep();
	    snapshotCollection = new DensitySnapshotCollection();
	    setDefaultParameters();
	}

    private void setDefaultParameters() {
        simulationTimeStep.setTime(10.0 * PhysicalConstants.hour);
	    totalDuration = 30.0 * PhysicalConstants.year;
	    snapshotInterval = 1.0 * PhysicalConstants.year;
    }

	public void run() {
	    simulation = new DiskSimulation(gridFactory, initialConditions);
	    snapshotCollection.setSimulation(simulation);
	    createMassMover();
	    createMassFlowCalculator();
        System.out.println("Running simulation...");
        snapshotCollection.takeSnapshot();
        double time = 0.0;
        double nextSnapshotTime = snapshotInterval;
        while (time < totalDuration){
        	massFlowCalculator.calculate();
        	
        	DensityGrid densityGrid = simulation.getDensityGrid();
            MassFlowGrid massFlowGrid = simulation.getMassFlowGrid();
            simulationTimeStep.update(densityGrid, massFlowGrid);
            
        	double timeStep = simulationTimeStep.getTime();
			massMover.setTimeStep(timeStep);
        	massMover.moveMass();
        	time +=  timeStep;
        	simulation.setCurrentTime(time);
        	
            if (time >= nextSnapshotTime) {
                snapshotCollection.takeSnapshot();
                nextSnapshotTime += snapshotInterval;
            }
        }
    }

    private void createMassFlowCalculator() {
        MassFlowGrid massFlowGrid = simulation.getMassFlowGrid();
        RadialGrid radialGrid = simulation.getRadialGrid();
        DensityGrid densityGrid = simulation.getDensityGrid();
        ViscosityGrid viscosityGrid = simulation.getViscosityGrid();
        massFlowCalculator = new MassFlowCalculator(
                massFlowGrid, radialGrid, densityGrid, viscosityGrid);
    }

    private void createMassMover() {
        DensityGrid density = simulation.getDensityGrid();
        MassFlowGrid massFlow = simulation.getMassFlowGrid();
        RadialGrid radialGrid = simulation.getRadialGrid();
        massMover = new MassMover(density , massFlow, radialGrid);
    }
    
/*    private void useTracer(){

    	tracerSimulation = new DiskSimulation(gridFactory, initialConditions);
	    snapshotCollection.setSimulation(tracerSimulation);
	    createTracerMover();
	    createTracerFlowCalculator();
        System.out.println("Running tracer simulation...");
        snapshotCollection.takeSnapshot();
        double time = 0.0;
        double nextSnapshotTime = snapshotInterval;
        while (time < totalDuration){
        	tracerFlowCalculator.calculate();
        	
        	DensityGrid densityGrid = simulation.getDensityGrid();
            MassFlowGrid massFlowGrid = simulation.getMassFlowGrid();
            simulationTimeStep.update(densityGrid, massFlowGrid);
            
        	double timeStep = simulationTimeStep.getTime();
			tracerMover.setTimeStep(timeStep);
        	tracerMover.moveTracer();
        	time +=  timeStep;
        	simulation.setCurrentTime(time);
        	
            if (time >= nextSnapshotTime) {
                snapshotCollection.takeSnapshot();
                nextSnapshotTime += snapshotInterval;
            }
        }
    	 
    }
    */
/*    private void createTracerMover() {
        TracerDensityGrid tracerDensity = simulation.getTracerDensityGrid();
        TracerFlowGrid tracerFlow = simulation.getTracerFlowGrid();
        RadialGrid radialGrid = simulation.getRadialGrid();
        tracerMover = new TracerMover(tracerDensity , tracerFlow, radialGrid);
    }

    private void createTracerFlowCalculator() {
        TracerFlowGrid tracerFlowGrid = simulation.getTracerFlowGrid();
        RadialGrid radialGrid = simulation.getRadialGrid();
        TracerDensityGrid tracerDensityGrid = simulation.getTracerDensityGrid();
        ViscosityGrid viscosityGrid = simulation.getViscosityGrid();
        tracerFlowCalculator = new TracerFlowCalculator(
                tracerFlowGrid, radialGrid, tracerDensityGrid, viscosityGrid);
    }*/
    
    public double getSimulationTimeStep() {
        return simulationTimeStep.getTime();
    }

    public void setSimulationTimeStep(double timeStep) {
        simulationTimeStep.setTime(timeStep);
    }

    public double getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(double duration) {
        totalDuration = duration;
    }

    public double getSnapshotInterval() {
        return snapshotInterval;
    }

    public void setSnapshotIntervalView(double snapshotInterval) {
        this.snapshotInterval = snapshotInterval;
    }

    public GridFactory getGridFactory() {
        return gridFactory;
    }

    public DensitySnapshotCollection getSnapshotCollection() {
        return snapshotCollection;
    }

}
