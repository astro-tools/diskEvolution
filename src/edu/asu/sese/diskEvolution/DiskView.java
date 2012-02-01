package edu.asu.sese.diskEvolution;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DiskView extends JFrame {
    private static final long serialVersionUID = 1L;
    RadialPlotView plot;
    private DensityGrid densityGrid;
    private RadialGrid radialGrid;

    public DiskView(RadialGrid radialGrid, DensityGrid densityGrid) {
        this.radialGrid = radialGrid;
        this.densityGrid = densityGrid;
        
        setFrameContents();        

        setTitle("DiskEvolution");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setFrameContents() {
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        
        content.add(new JLabel("Σ(r)"));
        
        plot = new RadialPlotView(radialGrid, densityGrid);
        content.add(plot);
        
        this.setContentPane(content);
        this.pack();
    }

}
