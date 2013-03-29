package edu.asu.sese.diskEvolution.view;

import javax.swing.JComponent;

import edu.asu.sese.diskEvolution.plot.ArrayGrid;
import edu.asu.sese.diskEvolution.plot.PlotView;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.Unit;


public class MassView {
    
    private PlotView plotView;
    private ArrayGrid timePlotGrid;
    private ArrayGrid massPlotGrid;

    MassView() {
        createGraph();
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
        for (int i = 0; i < 31; ++i) {
            mass[i] = PhysicalConstants.lunarMass;
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

}
