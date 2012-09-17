package edu.asu.sese.diskEvolution.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;

public class GraphicalApplication extends JFrame {
    private static final long serialVersionUID = 1L;
    private DiskSimulation simulation;
    private DiskView view;

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
        setupDiskView();
        setupInputView();
    }

    private void setupInputView() {
        JPanel panel = new ParametersView(simulation);
        add(panel, BorderLayout.LINE_START);        
    }

    private void setupDiskView() {
        view = new DiskView(simulation);
        add(view, BorderLayout.CENTER);
    }
    public void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        System.out.println(menuBar);
        JMenu toolsMenu = new JMenu("Tools");
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);
        
        JMenuItem aAction = new JMenuItem("a");
        JMenuItem bAction = new JMenuItem ("b");
        JMenuItem cAction = new JMenuItem("c");
        
        toolsMenu.add(aAction);
        toolsMenu.add(bAction);
        toolsMenu.add(cAction);        
    }
    
	}                                   
