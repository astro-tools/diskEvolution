package edu.asu.sese.diskEvolution.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.model.MidpointGrid;
import edu.asu.sese.diskEvolution.model.RadialGrid;

public class DiskView extends JFrame {
    private static final long serialVersionUID = 1L;
    RadialPlotView plot;
    private MidpointGrid densityGrid;
    private RadialGrid radialGrid;

    public DiskView(RadialGrid radialGrid, MidpointGrid densityGrid) {
        this.radialGrid = radialGrid;
        this.densityGrid = densityGrid;

        setFrameContents();

        setTitle("DiskEvolution");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setFrameContents() {
        JPanel content = new JPanel();
        BorderLayout layout = new BorderLayout();
        content.setLayout(layout);

        plot = new RadialPlotView(radialGrid, densityGrid);
        content.add(plot, BorderLayout.CENTER);

        this.setContentPane(content);
        this.pack();
    }

}
