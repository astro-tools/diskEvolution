package edu.asu.sese.diskEvolution.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.model.Snapshot;
import edu.asu.sese.diskEvolution.model.SnapshotCollection;

public class DiskView {

    private JPanel panel = new JPanel();
	private List<DiskViewGraph> graphList = new ArrayList<DiskViewGraph>();
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
		panel.removeAll();
		panel.add(graph.getComponent(), BorderLayout.CENTER);
		panel.repaint();
	}

	private DiskViewGraph createGraph(int selected) {
		Snapshot snapshot = snapshotCollection.getSnapshot(selected);
		DiskViewGraph graph = new DiskViewGraph(snapshot.getDensityGrid());
		graphList.add(selected, graph);
		return graph;
				
	}
    
	

}

