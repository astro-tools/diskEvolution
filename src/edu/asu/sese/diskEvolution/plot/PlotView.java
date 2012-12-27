package edu.asu.sese.diskEvolution.plot;

import java.awt.Color;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;

public class PlotView {
    private ChartPanel chartPanel;
    private XYSeriesCollection datasetCollection;
    private GridInterface domainGrid;
    private GridInterface rangeGrid;
    private String domainLabel;
    private String rangeLabel;
    private UnitInterface domainUnit;
    private UnitInterface rangeUnit;
    private JFreeChart chart;
    
    public PlotView(GridInterface domainGrid, GridInterface rangeGrid, 
            String domainLabel, String rangeLabel, 
            UnitInterface domainUnit, UnitInterface rangeUnit) {
        this.domainGrid = domainGrid;
        this.rangeGrid = rangeGrid;
        this.domainLabel = domainLabel;
        this.rangeLabel = rangeLabel;
        this.domainUnit = domainUnit;
        this.rangeUnit = rangeUnit;
        createSimpleXYChart();
        chartPanel = new ChartPanel(chart);
    }    

    public ChartPanel getChartPanel() {
        return chartPanel;
    }
        
    public void setAxisLimits(double domainMin, double domainMax, 
            double rangeMin, double rangeMax) {
        XYPlot xyPlot = (XYPlot) chartPanel.getChart().getPlot();
        NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        domain.setRange(domainMin / domainUnit.getScale(),
                domainMax / domainUnit.getScale());
        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
        range.setRange(rangeMin / rangeUnit.getScale(),
                rangeMax / rangeUnit.getScale());
    }

    public void updateData(GridInterface radialGrid, 
            GridInterface densityGrid) {
        datasetCollection.removeAllSeries();
        XYSeries series = createDataSet();
        datasetCollection.addSeries(series);
    }

    private void createSimpleXYChart() {
        datasetCollection = createDatasetCollection();

        String title = null;
        boolean showLegend = false;
        String domainLabelText = makeLabelWithUnit(domainLabel, domainUnit);
        String rangeLabelText = makeLabelWithUnit(rangeLabel, rangeUnit);
        boolean useTooltips = true;
        boolean generateURLs = false;
        chart = ChartFactory.createXYLineChart(title, 
                domainLabelText, rangeLabelText, 
                datasetCollection, PlotOrientation.VERTICAL,
                showLegend, useTooltips, generateURLs);
        
        chart.getPlot().setBackgroundPaint(Color.white);
        
        convertToLogLogChart();;
    }

    private static String makeLabelWithUnit(String label, UnitInterface unit) {
        String labelText = null;
        if (label!=null) {
            labelText = label;
            if (unit.getLabel() != "") {
                labelText += " (" + unit.getLabel() + ")";
            }
        }
        return labelText;
    }

    private void convertToLogLogChart() {
        final XYPlot plot = chart.getXYPlot();
        String domainLabelText = makeLabelWithUnit(domainLabel, domainUnit);
        final NumberAxis domainAxis = new NumberAxis(domainLabelText);
        String rangeLabelText = makeLabelWithUnit(rangeLabel, rangeUnit);
        final LogarithmicAxis rangeAxis = new LogarithmicAxis(rangeLabelText);
        rangeAxis.setLog10TickLabelsFlag(true);
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(rangeAxis);
    }

    private XYSeriesCollection createDatasetCollection() {
        XYSeries series = createDataSet();
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    private  XYSeries createDataSet() {
        XYSeries series = new XYSeries("series name");
        int intervalCount = domainGrid.getCount();
        for (int i = 0; i < intervalCount; ++i) {
            series.add(domainGrid.getValue(i) / domainUnit.getScale(),
                    rangeGrid.getValue(i) / rangeUnit.getScale() + 1e-6);
        }
        return series;
    }

}
