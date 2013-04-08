package edu.asu.sese.diskEvolution.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;
import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.model.Snapshot;
import edu.asu.sese.diskEvolution.model.SnapshotCollection;
import edu.asu.sese.diskEvolution.plot.ArrayGrid;
import edu.asu.sese.diskEvolution.plot.PlotView;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.Unit;


public class MassView implements Observer {
    
    private PlotView plotView;
    private ArrayGrid timePlotGrid;
    private ArrayGrid massPlotGrid;
    private DensityGrid densityGrid;
    private DiskSimulation simulation;
    private SnapshotCollection snapshotCollection;
    
    MassView(SnapshotCollection snapshotCollection) {
    	this.snapshotCollection = snapshotCollection;
        createGraph();
        snapshotCollection.addObserver(this);
    }
    
    void createGraph() {
        createTimeGrid();
        Unit year = new Unit("yr", "yr", PhysicalConstants.year);
        createMassGrid();
        Unit lunarMass = new Unit("M☽", "M<sub>☽</sub>",
                PhysicalConstants.lunarMass);
        plotView = new PlotView(timePlotGrid, massPlotGrid, "t", "M_tot", year, lunarMass);
        double tmax = 30.0 * PhysicalConstants.year;
        double mmax = 1.8 * PhysicalConstants.lunarMass;
        plotView.setAxisLimits(0.0, tmax, 0.0, mmax);
    }

    private void createMassGrid() {
        double [] mass = new double [31];
        for (int i = 0; i < 31; i++){
        	mass[i] = 0.8 * PhysicalConstants.lunarMass;
        
        }
        massPlotGrid = new ArrayGrid(mass);
    }
    

    private void createTimeGrid() {
        double [] time = new double [31];
        for (int i = 0; i < 31; ++i) {
            time[i] = i * PhysicalConstants.year;
        }
        timePlotGrid = new ArrayGrid(time);
    }

    public JComponent getComponent() {
        return plotView.getChartPanel();
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		int count = snapshotCollection.getSnapshotCount();
		double [] mass = new double [31];
		for (int index = 0; index < count; index++){
			Snapshot snapshot = snapshotCollection.getSnapshot(index);
        	densityGrid = snapshot.getDensityGrid();
        	double finalMass = densityGrid.getTotalMass();
        	mass[index] =  finalMass;
        	System.out.println("mass is " + mass[index] + " for index " + index);
		}
        massPlotGrid = new ArrayGrid(mass);
        plotView.updateData(timePlotGrid, massPlotGrid);
	}
	
}
