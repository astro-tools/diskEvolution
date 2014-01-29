package edu.asu.sese.diskEvolution.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Vector;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

import edu.asu.sese.diskEvolution.model.ViscositySnapshotCollection;
import edu.asu.sese.diskEvolution.model.ViscositySnapshot;

public class ViscosityView {

    private JPanel panel = new JPanel();
	private Vector<ViscosityViewGraph> graphList = new Vector<ViscosityViewGraph>();
	private ViscositySnapshotCollection snapshotCollection;
    
    public ViscosityView(ViscositySnapshotCollection snapshotCollection) {
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
		ViscosityViewGraph graph = graphList.get(selected);
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

	private ViscosityViewGraph createGraph(int selected) {
		ViscositySnapshot snapshot = snapshotCollection.getSnapshot(selected);
		ViscosityViewGraph graph = new ViscosityViewGraph(snapshot.getViscosityGrid());
		System.out.println("Create a graph");
		if (graphList.size() <= selected) {
			graphList.setSize(selected + 1);
		}
		graphList.set(selected, graph);
		return graph;
				
	}
    
}

