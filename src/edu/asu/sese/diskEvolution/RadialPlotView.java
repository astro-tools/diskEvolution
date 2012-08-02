package edu.asu.sese.diskEvolution;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;

public class RadialPlotView extends ChartPanel {
    
    private static final long serialVersionUID = 1L;
      
    
    public RadialPlotView(RadialGrid radialGrid, MidpointGrid densityGrid) {
        super(createSimpleXYChart(radialGrid, densityGrid));
    }    

    public static JFreeChart createSimpleXYChart(RadialGrid radialGrid, MidpointGrid densityGrid) {
        XYSeriesCollection dataset = createDataset(radialGrid, densityGrid);

        String title = "Surface Density";
        String domainTitle = "r (AU)";
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
        final NumberAxis domainAxis = new LogarithmicAxis(domainTitle);
        final NumberAxis rangeAxis = new LogarithmicAxis(rangeTitle);
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(rangeAxis);
    }

    public static XYSeriesCollection createDataset(RadialGrid radialGrid,
            MidpointGrid densityGrid) {
        XYSeries series = new XYSeries("Surface Density");
        int intervalCount = radialGrid.getIntervalCount();
        for (int i = 0; i < intervalCount; ++i) {
            series.add(radialGrid.getMidpoint(i) / PhysicalConstants.auInCm,
                    densityGrid.getValue(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

}
