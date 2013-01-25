package edu.asu.sese.diskEvolution.view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

public class DiskView {

    private JPanel panel = new JPanel();
    
    public DiskView() {
        panel.setPreferredSize(new Dimension(300, 200));
    }
    
    public Component getComponent() {
        return panel;
    }
    

}
