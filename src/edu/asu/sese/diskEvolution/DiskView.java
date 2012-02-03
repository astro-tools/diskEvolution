package edu.asu.sese.diskEvolution;

import java.awt.BorderLayout;
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
        BorderLayout layout = new BorderLayout();
        layout.setHgap(5);
        layout.setVgap(5);
        content.setLayout(layout);
        
        content.add(new JLabel("Σ(r)"), BorderLayout.WEST);
        content.add(new JLabel("r", JLabel.CENTER), BorderLayout.PAGE_END);
        
        plot = new RadialPlotView(radialGrid, densityGrid);
        content.add(plot, BorderLayout.CENTER);
        
        this.setContentPane(content);
        this.pack();
    }

}
