package edu.asu.sese.diskEvolution.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class GraphicalApplication extends JFrame {
    private static final long serialVersionUID = 1L;

    public GraphicalApplication() {
        setupMenuBar();
        pack();
        setVisible(true);
    }

    public void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        System.out.println(menuBar);
        JMenu toolsMenu = new JMenu("Tools");
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);
    }
    
}
