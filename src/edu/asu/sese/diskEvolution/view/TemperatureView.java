
package edu.asu.sese.diskEvolution.view;

import org.jfree.chart.ChartPanel;
import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.model.TemperatureGrid;
import edu.asu.sese.diskEvolution.plot.PlotView;
import edu.asu.sese.diskEvolution.plot.UnitInterface;
import edu.asu.sese.diskEvolution.util.MidpointAdaptor;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.Unit;
public class TemperatureView extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private PlotView plot;
    private UnitInterface domainUnit;
    private UnitInterface rangeUnit;
    private TemperatureGrid temperatureGrid;


    public TemperatureView(){
		createGraph();
		}

    private void createGraph(){ 
        MidpointAdaptor radialGrid = new MidpointAdaptor(temperatureGrid.getRadialGrid());
        String domainLabel = "r";
        String rangeLabel = "T<sub>eff</sub>";
        domainUnit = new Unit("R⊕", "R<sub>⊕</sub>", 
                PhysicalConstants.earthRadiusInCm);
        rangeUnit = new Unit("K", "K", 1.0);
        plot = new PlotView(radialGrid, temperatureGrid, domainLabel, rangeLabel, domainUnit, rangeUnit);
    }

    public  ChartPanel getComponent() {
        return plot.getChartPanel();
    }

}
