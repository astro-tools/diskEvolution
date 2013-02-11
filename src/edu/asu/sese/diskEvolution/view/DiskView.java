package edu.asu.sese.diskEvolution.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Vector;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

import edu.asu.sese.diskEvolution.model.Snapshot;
import edu.asu.sese.diskEvolution.model.SnapshotCollection;

public class DiskView {

    private JPanel panel = new JPanel();
	private Vector<DiskViewGraph> graphList = new Vector<DiskViewGraph>();
	private SnapshotCollection snapshotCollection;
    
    public DiskView(SnapshotCollection snapshotCollection) {
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
		DiskViewGraph graph = graphList.get(selected);
		if (graph == null){
			graph = createGraph(selected);
		}
		System.out.println(graph);
		panel.removeAll();
		panel.setBackground(new Color(6*selected, 6*selected, 6*selected));
		
		ChartPanel chartPanel = graph.getComponent();
        panel.add(chartPanel, BorderLayout.CENTER);
		chartPanel.revalidate();
		panel.repaint();
	}

	private DiskViewGraph createGraph(int selected) {
		Snapshot snapshot = snapshotCollection.getSnapshot(selected);
		DiskViewGraph graph = new DiskViewGraph(snapshot.getDensityGrid());
		System.out.println("Create a graph");
		if (graphList.size() <= selected) {
			graphList.setSize(selected + 1);
		}
		graphList.set(selected, graph);
		return graph;
				
	}
    
}

