package edu.asu.sese.diskEvolution.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;

public class GraphicalApplication extends JFrame {
    private static final long serialVersionUID = 1L;
    private DiskSimulation simulation;
    private InitialConditionsView initialConditionsPanel;
    private JTabbedPane tabPane;
    private SimulationRunnerView runnerPanel;
    
    public GraphicalApplication(DiskSimulation simulation) {
        this.simulation = simulation;
        setupMenuBar();
        setupWindowLayout();
        pack();
        setSize(640, 480);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setupWindowLayout() {
        BorderLayout layoutManager = new BorderLayout();
        setLayout(layoutManager);
        tabPane = new JTabbedPane();
        initialConditionsPanel = new InitialConditionsView(simulation);
        tabPane.add("Initial Conditions", initialConditionsPanel);
        runnerPanel = new SimulationRunnerView();
        tabPane.add("Run Simulation", runnerPanel);
        runnerPanel = new ResultsView();
        tabPane.add("Results", runnerPanel);
        add(tabPane, BorderLayout.CENTER);
    }
    
    public void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        System.out.println(menuBar);
        JMenu toolsMenu = new JMenu("Tools");
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);
        
        JMenuItem evolveAction = new JMenuItem("Evolve");
        toolsMenu.add(evolveAction);               
    }
}                                   

    