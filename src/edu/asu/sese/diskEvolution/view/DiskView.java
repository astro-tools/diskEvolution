package edu.asu.sese.diskEvolution.view;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class DiskView {

    private JPanel panel = new JPanel();
	private List<DiskViewGraph> graphList = new ArrayList<DiskViewGraph>();
    
    public DiskView() {
        panel.setPreferredSize(new Dimension(300, 200));
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
		panel.add(graph.getComponent());
	}

	private DiskViewGraph createGraph(int selected) {
		DiskViewGraph graph = new DiskViewGraph(null);
		graphList.add(selected, graph);
		return graph;
				
	}
    

}

