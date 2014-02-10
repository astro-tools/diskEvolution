package edu.asu.sese.diskEvolution.plot;

import java.awt.Color;
import java.awt.Font;

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

    public void updateData(GridInterface domainGrid, 
            GridInterface rangeGrid) {
        this.domainGrid = domainGrid;
        this.rangeGrid = rangeGrid;
        datasetCollection.removeAllSeries();
        XYSeries series = createDataSet();
        datasetCollection.addSeries(series);
        System.out.println("Data updated ");
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
        changeStyle(chart);
        
        chart.getPlot().setBackgroundPaint(Color.white);
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

    public void makeRangeAxisLogarithmic() {
        final XYPlot plot = chart.getXYPlot();
        String rangeLabelText = makeLabelWithUnit(rangeLabel, rangeUnit);
        final LogarithmicAxis rangeAxis = new LogarithmicAxis(rangeLabelText);
        rangeAxis.setLog10TickLabelsFlag(true);
        rangeAxis.setAllowNegativesFlag(true);
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

    public static void changeStyle(JFreeChart chart) {
        final StandardChartTheme chartTheme =
                (StandardChartTheme)
                org.jfree.chart.StandardChartTheme.createJFreeTheme();

        final Font font = new Font("Tahoma", Font.PLAIN, 11);
        final Color color=new Color(0,0,0);

        chartTheme.setExtraLargeFont(font);
        chartTheme.setLargeFont(font);
        chartTheme.setRegularFont(font);
        chartTheme.setSmallFont(font);

        chartTheme.setAxisLabelPaint(color);
        chartTheme.setLegendItemPaint(color);
        chartTheme.setItemLabelPaint(color);
        chartTheme.apply(chart);
    }

    
}
