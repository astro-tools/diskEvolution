package edu.asu.sese.diskEvolution.plot;

import java.awt.Color;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;

public class RadialPlotView extends ChartPanel {
    
    private static final long serialVersionUID = 1L;
    private static XYSeriesCollection dataset;
    private UnitInterface domainUnit;
    private UnitInterface rangeUnit;
    
    public RadialPlotView(GridInterface domainGrid, GridInterface rangeGrid, 
            String domainLabel, String rangeLabel, 
            UnitInterface domainUnit, UnitInterface rangeUnit) {
        super(createSimpleXYChart(domainGrid, rangeGrid, domainLabel, 
                rangeLabel, domainUnit, rangeUnit));
        this.domainUnit = domainUnit;
        this.rangeUnit = rangeUnit;
    }    

    public void setAxisLimits(double domainMin, double domainMax, 
            double rangeMin, double rangeMax) {
        XYPlot xyPlot = (XYPlot) getChart().getPlot();
        NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        domain.setRange(domainMin / domainUnit.getScale(),
                domainMax / domainUnit.getScale());
        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
        range.setRange(rangeMin / rangeUnit.getScale(),
                rangeMax / rangeUnit.getScale());
    }

    public void updateData(GridInterface radialGrid, 
            GridInterface densityGrid) {
        dataset.removeAllSeries();
        XYSeries series = createDataSeries(radialGrid, densityGrid, 
                domainUnit, rangeUnit);
        dataset.addSeries(series);
    }

    private static JFreeChart createSimpleXYChart(GridInterface domainGrid, 
            GridInterface rangeGrid, String domainLabel, String rangeLabel, 
            UnitInterface domainUnit, UnitInterface rangeUnit) {
        dataset = createDataset(domainGrid, rangeGrid, domainUnit, rangeUnit);

        String title = null;
        boolean showLegend = false;
        String domainLabelText = addUnitString(domainLabel, domainUnit);
        String rangeLabelText = addUnitString(rangeLabel, rangeUnit);
        boolean useTooltips = true;
        boolean generateURLs = false;
        JFreeChart chart = ChartFactory.createXYLineChart(title, 
                domainLabelText, rangeLabelText, 
                dataset, PlotOrientation.VERTICAL,
                showLegend, useTooltips, generateURLs);
        
        chart.getPlot().setBackgroundPaint(Color.white);
        
        convertToLogLogChart(domainLabelText, rangeLabel, chart);
        
        return chart;
    }

    private static String addUnitString(String label, UnitInterface unit) {
        String labelText = null;
        if (label!=null) {
            labelText = label;
            if (unit.getLabel() != "") {
                labelText += " (" + unit.getLabel() + ")";
            }
        }
        return labelText;
    }

    private static void convertToLogLogChart(String domainTitle,
            String rangeTitle, JFreeChart chart) {
        final XYPlot plot = chart.getXYPlot();
        final NumberAxis domainAxis = new NumberAxis(domainTitle);
        final LogarithmicAxis rangeAxis = new LogarithmicAxis(rangeTitle);
        rangeAxis.setLog10TickLabelsFlag(true);
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(rangeAxis);
    }

    private static XYSeriesCollection createDataset(
            GridInterface domainGrid, GridInterface rangeGrid, 
            UnitInterface domainUnit, UnitInterface rangeUnit) {
        XYSeries series = 
                createDataSeries(domainGrid, rangeGrid, domainUnit, rangeUnit);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    private static XYSeries createDataSeries(
            GridInterface domainGrid, GridInterface rangeGrid, 
            UnitInterface domainUnit, UnitInterface rangeUnit) {
        XYSeries series = new XYSeries("series name");
        int intervalCount = domainGrid.getCount();
        for (int i = 0; i < intervalCount; ++i) {
            series.add(domainGrid.getValue(i) / domainUnit.getScale(),
                    rangeGrid.getValue(i) / rangeUnit.getScale() + 1e-6);
        }
        return series;
    }

}
