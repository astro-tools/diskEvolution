package edu.asu.sese.diskEvolution.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Vector;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

import edu.asu.sese.diskEvolution.model.TemperatureSnapshotCollection;
import edu.asu.sese.diskEvolution.model.TemperatureSnapshot;

public class TemperatureView {

    private JPanel panel = new JPanel();
	private Vector<TemperatureViewGraph> graphList = new Vector<TemperatureViewGraph>();
	private TemperatureSnapshotCollection snapshotCollection;
    
    public TemperatureView(TemperatureSnapshotCollection snapshotCollection) {
    	this.snapshotCollection = snapshotCollection;
        panel.setLayout(new BorderLayout());
    }
    
    public Component getComponent() {
        return panel;
    }

	public void showGraph(int selected) {
		System.out.println("Show Graph " + selected);
		if (graphList.size() <= selected){
			createGraph(selected);
		}
		TemperatureViewGraph graph = graphList.get(selected);
		if (graph == null){
			graph = createGraph(selected);
		}
		System.out.println(graph);
		panel.removeAll();
		
		ChartPanel chartPanel = graph.getComponent();
        panel.add(chartPanel, BorderLayout.CENTER);
		chartPanel.revalidate();
		panel.repaint();
	}

	private TemperatureViewGraph createGraph(int selected) {
		TemperatureSnapshot snapshot = snapshotCollection.getSnapshot(selected);
		TemperatureViewGraph graph = new TemperatureViewGraph(snapshot.getTemperatureGrid());
		System.out.println("Create a graph");
		if (graphList.size() <= selected) {
			graphList.setSize(selected + 1);
		}
		graphList.set(selected, graph);
		return graph;
				
	}
    
}

