package edu.asu.sese.diskEvolution.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import edu.asu.sese.diskEvolution.controller.Application;
import edu.asu.sese.diskEvolution.controller.SimulationRunner;

public class GraphicalApplication extends JFrame {
    private static final long serialVersionUID = 1L;
    private Application simulation;
    private JTabbedPane tabPane;
    private InitialConditionsView initialConditionsPanel;
    private SimulationRunnerView runnerPanel;
    private ResultsView resultsPanel;
    private VolatileView tracerPanel;
    private ResultsViewII resultsPanelII;
    private ResultsViewIII resultsPanelIII;
    
    public GraphicalApplication(Application simulation) {
        this.simulation = simulation;
/*        setupMenuBar();
*/        setupWindowLayout();
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
        SimulationRunner runner = simulation.getRunner();
        runnerPanel = new SimulationRunnerView(runner);
        tabPane.add("Run Simulation", runnerPanel);
        resultsPanel = new ResultsView(runner);
        tabPane.add("Surface Density", resultsPanel);
        tracerPanel = new VolatileView(runner);
        tabPane.add("Volatiles Density", resultsPanelIII);
        add(tabPane, BorderLayout.CENTER);
        resultsPanelII = new ResultsViewII(runner);
        tabPane.add("Temperature", resultsPanelII);
        add(tabPane, BorderLayout.CENTER);
        resultsPanelIII = new ResultsViewIII(runner);
        tabPane.add("Viscosity", resultsPanelIII);
        add(tabPane, BorderLayout.CENTER);
    }
    
/*    public void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu toolsMenu = new JMenu("Tools");
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);       
    }*/
}                                   

    