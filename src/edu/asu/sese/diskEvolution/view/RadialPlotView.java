package edu.asu.sese.diskEvolution.view;

import java.text.DecimalFormat;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.util.LogFormat;
import org.jfree.data.xy.*;

import edu.asu.sese.diskEvolution.util.MidpointGrid;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class RadialPlotView extends ChartPanel {
    
    private static final long serialVersionUID = 1L;
    private static XYSeriesCollection dataset;
	private static String radialUnitName = "R⊕";
      
    
    public RadialPlotView(RadialGrid radialGrid, MidpointGrid densityGrid) {
        super(createSimpleXYChart(radialGrid, densityGrid));
    }    

    public static JFreeChart createSimpleXYChart(RadialGrid radialGrid, MidpointGrid densityGrid) {
        dataset = createDataset(radialGrid, densityGrid);

        String title = "Surface Density";
		String domainTitle = "r (" + radialUnitName +")";
        String rangeTitle = "Σ (g/cm²)";
        boolean showLegend = false;
        boolean useTooltips = true;
        boolean generateURLs = false;
        JFreeChart chart = ChartFactory.createXYLineChart(title, domainTitle,
                rangeTitle, dataset, PlotOrientation.VERTICAL,
                showLegend, useTooltips, generateURLs);
        
        convertToLogLogChart(domainTitle, rangeTitle, chart);
        
        return chart;
    }

    public static void convertToLogLogChart(String domainTitle,
            String rangeTitle, JFreeChart chart) {
        final XYPlot plot = chart.getXYPlot();
        final NumberAxis domainAxis = new NumberAxis(domainTitle);
        final LogarithmicAxis rangeAxis = new LogarithmicAxis(rangeTitle);
        rangeAxis.setLog10TickLabelsFlag(true);
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(rangeAxis);
    }

    public static XYSeriesCollection createDataset(RadialGrid radialGrid,
            MidpointGrid densityGrid) {
        XYSeries series = createDataSeries(radialGrid, densityGrid);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    private static XYSeries createDataSeries(RadialGrid radialGrid,
            MidpointGrid densityGrid) {
        XYSeries series = new XYSeries("Surface Density");
        int intervalCount = radialGrid.getIntervalCount();
        for (int i = 0; i < intervalCount; ++i) {
            series.add(radialGrid.getMidpoint(i) / PhysicalConstants.earthRadiusInCm,
                    densityGrid.getValue(i) + 1e-6);
        }
        return series;
    }

    public void updateData(RadialGrid radialGrid, MidpointGrid densityGrid) {
        dataset.removeAllSeries();
        XYSeries series = createDataSeries(radialGrid, densityGrid);
        dataset.addSeries(series);
    }

    public void setRange(double rmin, double rmax, double vmin, double vmax) {
        XYPlot xyPlot = (XYPlot) getChart().getPlot();
        NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        domain.setRange(rmin / PhysicalConstants.earthRadiusInCm,
                rmax / PhysicalConstants.earthRadiusInCm);
        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
        range.setRange(vmin, vmax);
    }

}
