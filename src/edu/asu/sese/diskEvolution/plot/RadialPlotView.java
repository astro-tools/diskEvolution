package edu.asu.sese.diskEvolution.plot;

import java.awt.Color;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;

import edu.asu.sese.diskEvolution.util.MidpointGrid;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class RadialPlotView extends ChartPanel {
    
    private static final long serialVersionUID = 1L;
    private static XYSeriesCollection dataset;
    
    public RadialPlotView(RadialGrid domainGrid, MidpointGrid rangeGrid, 
            String domainLabel, String rangeLabel) {
        super(createSimpleXYChart(domainGrid, rangeGrid, domainLabel, rangeLabel));
    }    

    public void setAxisLimits(double domainMin, double domainMax, double rangeMin, double rangeMax) {
        XYPlot xyPlot = (XYPlot) getChart().getPlot();
        NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        domain.setRange(domainMin / PhysicalConstants.earthRadiusInCm,
                domainMax / PhysicalConstants.earthRadiusInCm);
        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
        range.setRange(rangeMin, rangeMax);
    }

    public void updateData(RadialGrid radialGrid, MidpointGrid densityGrid) {
        dataset.removeAllSeries();
        XYSeries series = createDataSeries(radialGrid, densityGrid);
        dataset.addSeries(series);
    }

    public static JFreeChart createSimpleXYChart(RadialGrid radialGrid, 
            MidpointGrid densityGrid, String domainLabel, String rangeLabel) {
        dataset = createDataset(radialGrid, densityGrid);

        String title = null;
        boolean showLegend = false;
        boolean useTooltips = true;
        boolean generateURLs = false;
        JFreeChart chart = ChartFactory.createXYLineChart(title, domainLabel,
                rangeLabel, dataset, PlotOrientation.VERTICAL,
                showLegend, useTooltips, generateURLs);
        
        chart.getPlot().setBackgroundPaint(Color.white);
        
        convertToLogLogChart(domainLabel, rangeLabel, chart);
        
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

}
