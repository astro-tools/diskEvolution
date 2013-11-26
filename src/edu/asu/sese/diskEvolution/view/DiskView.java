package edu.asu.sese.diskEvolution.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Vector;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

import edu.asu.sese.diskEvolution.model.DensitySnapshot;
import edu.asu.sese.diskEvolution.model.DensitySnapshotCollection;

public class DiskView {

    private JPanel panel = new JPanel();
	private Vector<DiskViewGraph> graphList = new Vector<DiskViewGraph>();
	private DensitySnapshotCollection snapshotCollection;
    
    public DiskView(DensitySnapshotCollection snapshotCollection) {
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
		
		ChartPanel chartPanel = graph.getComponent();
        panel.add(chartPanel, BorderLayout.CENTER);
		chartPanel.revalidate();
		panel.repaint();
	}

	private DiskViewGraph createGraph(int selected) {
		DensitySnapshot snapshot = snapshotCollection.getSnapshot(selected);
		DiskViewGraph graph = new DiskViewGraph(snapshot.getDensityGrid());
		System.out.println("Create a graph");
		if (graphList.size() <= selected) {
			graphList.setSize(selected + 1);
		}
		graphList.set(selected, graph);
		return graph;
				
	}
    
}

