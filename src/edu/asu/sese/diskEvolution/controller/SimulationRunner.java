package edu.asu.sese.diskEvolution.controller;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.model.InitialConditions;
import edu.asu.sese.diskEvolution.model.MassFlowCalculator;
import edu.asu.sese.diskEvolution.model.MassFlowGrid;
import edu.asu.sese.diskEvolution.model.MassMover;
import edu.asu.sese.diskEvolution.model.DensitySnapshotCollection;
import edu.asu.sese.diskEvolution.model.TemperatureCalculator;
import edu.asu.sese.diskEvolution.model.TemperatureGrid;
import edu.asu.sese.diskEvolution.model.TemperatureSnapshotCollection;
import edu.asu.sese.diskEvolution.model.TimeStep;
import edu.asu.sese.diskEvolution.model.WaterDensityGrid;
import edu.asu.sese.diskEvolution.model.VolatileFlowCalculator;
import edu.asu.sese.diskEvolution.model.VolatileFlowGrid;
import edu.asu.sese.diskEvolution.model.VolatileMover;
import edu.asu.sese.diskEvolution.model.ViscosityCalculator;
import edu.asu.sese.diskEvolution.model.ViscosityGrid;
import edu.asu.sese.diskEvolution.model.ViscositySnapshotCollection;
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
    private DiskSimulation volatileSimulation;
    private InitialConditions initialConditions;
    private MassMover massMover;
    private MassFlowCalculator massFlowCalculator;
    private DensitySnapshotCollection densitySnapshotCollection;
    private TemperatureSnapshotCollection temperatureSnapshotCollection;
    private TemperatureCalculator temperatureCalculator;
    private ViscositySnapshotCollection viscositySnapshotCollection;
    private ViscosityCalculator viscosityCalculator;
    private VolatileFlowCalculator volatileFlowCalculator;
    private VolatileMover volatileMover;
	
	public SimulationRunner(Application diskSimulation) {
	    this.application = diskSimulation;
	    gridFactory = application.getGridFactory();
	    initialConditions = application.getInitialConditions();
	    simulationTimeStep = new TimeStep();
	    densitySnapshotCollection = new DensitySnapshotCollection();
	    temperatureSnapshotCollection = new TemperatureSnapshotCollection();
	    viscositySnapshotCollection = new ViscositySnapshotCollection();
	    setDefaultParameters();
	}

    private void setDefaultParameters() {
        simulationTimeStep.setTime(10.0 * PhysicalConstants.hour);
	    totalDuration = 30.0 * PhysicalConstants.year;
	    snapshotInterval = 1.0 * PhysicalConstants.year;
    }

	public void run() {
	    simulation = new DiskSimulation(gridFactory, initialConditions);
	    densitySnapshotCollection.setSimulation(simulation);
	    temperatureSnapshotCollection.setSimulation(simulation);
	    viscositySnapshotCollection.setSimulation(simulation);
	    createMassMover();
	    createMassFlowCalculator();
	    createTemperatureCalculator();
	    createViscosityCalculator();
        System.out.println("Running simulation...");
        densitySnapshotCollection.takeSnapshot();
    	temperatureCalculator.calculate();
        temperatureSnapshotCollection.takeSnapshot();
        viscosityCalculator.calculate();
        viscositySnapshotCollection.takeSnapshot();

        double time = 0.0;
        double nextSnapshotTime = snapshotInterval;
        while (time < totalDuration){
        	massFlowCalculator.calculate();
        	
        	ViscosityGrid viscosityGrid = simulation.getViscosityGrid();
            RadialGrid radialGrid = simulation.getRadialGrid();
            simulationTimeStep.update(viscosityGrid, radialGrid);
            
        	double timeStep = simulationTimeStep.getTime();
			massMover.setTimeStep(timeStep);
        	massMover.moveMass();
        	time +=  timeStep;
        	simulation.setCurrentTime(time);
        	temperatureCalculator.calculate();
        	viscosityCalculator.calculate();
        	
            if (time >= nextSnapshotTime) {
                densitySnapshotCollection.takeSnapshot();
                temperatureSnapshotCollection.takeSnapshot();
                viscositySnapshotCollection.takeSnapshot();
                nextSnapshotTime += snapshotInterval;
            }
        }
    }
	
	private void createTemperatureCalculator() {
        TemperatureGrid temperatureGrid = simulation.getTemperatureGrid();
        RadialGrid radialGrid = simulation.getRadialGrid();
        DensityGrid densityGrid = simulation.getDensityGrid();
        ViscosityGrid viscosityGrid = simulation.getViscosityGrid();
		temperatureCalculator = new TemperatureCalculator(temperatureGrid, radialGrid, densityGrid, viscosityGrid);
	}

	private void createViscosityCalculator() {
        ViscosityGrid viscosityGrid = simulation.getViscosityGrid();
        RadialGrid radialGrid = simulation.getRadialGrid();
        DensityGrid densityGrid = simulation.getDensityGrid();
		viscosityCalculator = new ViscosityCalculator(viscosityGrid, radialGrid, densityGrid);
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
    
    private void useTracer(){

    	volatileSimulation = new DiskSimulation(gridFactory, initialConditions);
	    densitySnapshotCollection.setSimulation(volatileSimulation);
	    createVolatileMover();
	    createVolatileFlowCalculator();
        System.out.println("Running volatile simulation...");
        densitySnapshotCollection.takeSnapshot();
        double time = 0.0;
        double nextSnapshotTime = snapshotInterval;
        while (time < totalDuration){
        	volatileFlowCalculator.calculate();
        	
        	ViscosityGrid viscosityGrid = simulation.getViscosityGrid();
            RadialGrid radialGrid = simulation.getRadialGrid();
            simulationTimeStep.update(viscosityGrid, radialGrid);
            
        	double timeStep = simulationTimeStep.getTime();
			volatileMover.setTimeStep(timeStep);
        	volatileMover.moveVolatile();
        	time +=  timeStep;
        	simulation.setCurrentTime(time);
        	
            if (time >= nextSnapshotTime) {
                densitySnapshotCollection.takeSnapshot();
                nextSnapshotTime += snapshotInterval;
            }
        }
    	 
    }
    
    private void createVolatileMover() {
        WaterDensityGrid volatileDensity = simulation.getVolatileDensityGrid();
        VolatileFlowGrid volatileFlow = simulation.getVolatileFlowGrid();
        RadialGrid radialGrid = simulation.getRadialGrid();
        volatileMover = new VolatileMover(volatileDensity , volatileFlow, radialGrid);
    }

    private void createVolatileFlowCalculator() {
        VolatileFlowGrid volatileFlowGrid = simulation.getVolatileFlowGrid();
        RadialGrid radialGrid = simulation.getRadialGrid();
        WaterDensityGrid volatileDensityGrid = simulation.getVolatileDensityGrid();
        ViscosityGrid viscosityGrid = simulation.getViscosityGrid();
        volatileFlowCalculator = new VolatileFlowCalculator(
                volatileFlowGrid, radialGrid, volatileDensityGrid, viscosityGrid);
    }
    
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

    public DensitySnapshotCollection getDensitySnapshotCollection() {
        return densitySnapshotCollection;
    }
    
    public TemperatureSnapshotCollection getTemperatureSnapshotCollection() {
        return temperatureSnapshotCollection;
    }
    
    public ViscositySnapshotCollection getViscositySnapshotCollection() {
        return viscositySnapshotCollection;
    }
}