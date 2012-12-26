package edu.asu.sese.diskEvolution.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.controller.SimulationRunner;
import edu.asu.sese.diskEvolution.util.GridFactory;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.Unit;

public class SimulationRunnerView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private SimulationRunner runner;
	private GridFactory gridFactory;

	private ScalarInputView timeStepInputView;
	private ScalarInputView rminView;
	private ScalarInputView rmaxView;
	private ScalarInputView deltar0View;
	private ScalarInputView intervalCountView;
    private ScalarInputView durationView;
    private ScalarInputView snapshotIntervalView;
    private ScalarOutputView iterationCountView;
    private ScalarOutputView imageCountView;
    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JButton startButton;
    private JButton pauseButton;

	public SimulationRunnerView(SimulationRunner runner) {
	    this.runner = runner;
	    gridFactory = runner.getGridFactory();
		ActionListener listener = createListener();
		setupPanels();
		setupLabelsAndFields(listener);
        setFieldValuesFromData();
	}
	
	private void setFieldValuesFromData() {
	    double timeStep = runner.getSimulationTimeStep();
	    timeStepInputView.setValue(timeStep);
	    
	    double totalDuration = runner.getTotalDuration();
	    durationView.setValue(totalDuration);
	    
	    double iterationCount = Math.ceil(totalDuration / timeStep);
	    iterationCountView.setValue(iterationCount);
	    
	    double snapshotInterval = runner.getSnapshotInterval();
	    snapshotIntervalView.setValue(snapshotInterval);
	    
	    double imageCount = Math.ceil(totalDuration / snapshotInterval);
	    imageCountView.setValue(imageCount);
	 
	    rminView.setValue(gridFactory.getRmin());
	    rmaxView.setValue(gridFactory.getRmax());
	    intervalCountView.setValue(gridFactory.getIntervalCount());
	    deltar0View.setValue(gridFactory.getDeltar0());
	}

	private void setupPanels() {
	    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	    inputPanel = new JPanel();
	    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.LINE_AXIS));
	    add(inputPanel);
	    add(Box.createVerticalGlue());
	    buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        add(buttonPanel);
    }

    private void setupLabelsAndFields(ActionListener listener) {
	    setupLoopParameters(listener);
	    setupGridParameters(listener);
	    setupExecutionButtons();
	}

    private void setupLoopParameters(ActionListener listener) {
        ScalarListView list = new ScalarListView();
        list.setAlignmentX(LEFT_ALIGNMENT);
        inputPanel.add(list);
        list.addLabel("Loop parameters:");
        
        Unit year = new Unit("yr", "yr", PhysicalConstants.year);
        Unit hour = new Unit("h", "h", PhysicalConstants.hour);
        Unit noUnit = new Unit("", "", 1.0);
    
        timeStepInputView = new ScalarInputView("Δ t", hour);
        list.add(timeStepInputView);
        
        durationView = new ScalarInputView("T<sub>tot</sub>", year);
        list.add(durationView);

        iterationCountView = 
                new ScalarOutputView("N<sub>iter</sub>", noUnit, "#");
        list.add(iterationCountView);
        
        snapshotIntervalView = new ScalarInputView("Δ T<sub>image</sub>", year);
        list.add(snapshotIntervalView);

        imageCountView = 
                new ScalarOutputView("N<sub>image</sub>", noUnit, "#");
        list.add(imageCountView);

        list.addActionListener(listener);
    }

    private void setupGridParameters(ActionListener listener) {
        ScalarListView list = new ScalarListView();
        list.setAlignmentX(LEFT_ALIGNMENT);
        inputPanel.add(list);
        list.addLabel("Grid parameters:");

        Unit earthRadius = new Unit("R⊕", "R<sub>⊕</sub>",
	            PhysicalConstants.earthRadiusInCm);
        
	    Unit noUnit = new Unit("", "", 1.0);

	    rminView = new ScalarInputView("R<sub>min</sub>", earthRadius);
	    list.add(rminView);
	    list.addActionListener(listener);
	   
	    rmaxView = new ScalarInputView("R<sub>max</sub>", earthRadius);
	    list.add(rmaxView);
	    list.addActionListener(listener);
	    
	    deltar0View = new ScalarInputView("Δ R<sub>0</sub>", earthRadius);
	    list.add(deltar0View);
	    list.addActionListener(listener);
	    
	    intervalCountView = new ScalarInputView("N<sub>interval</sub>", noUnit);
	    list.add(intervalCountView);
	    list.addActionListener(listener);
    }

    private void setupExecutionButtons() {
        buttonPanel.add(Box.createHorizontalGlue());
        pauseButton = new JButton("Pause");
        pauseButton.setEnabled(false);
        buttonPanel.add(pauseButton);
        startButton = new JButton("Run Simulation");
        buttonPanel.add(startButton);
    }

    public ActionListener createListener() {
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				updateParameterValuesFromFields();
			}
		};
		return listener;
	}

	protected void updateParameterValuesFromFields()  {
	    try {
            double timeStep = timeStepInputView.getValue();
            runner.setSimulationTimeStep(timeStep);
        } catch (NumberFormatException exception) {
        }
	    try {
	        double duration = durationView.getValue();
	        runner.setTotalDuration(duration);
	    } catch (NumberFormatException exception) {
	    }
	    try {
	        double snapshotInterval = snapshotIntervalView.getValue();
	        runner.setSnapshotIntervalView(snapshotInterval);
	    } catch (NumberFormatException exception) {
	    }
	    try {
	        double rmin = rminView.getValue();
	        gridFactory.setRmin(rmin);
	    } catch (NumberFormatException exception) {
	    }
	    try {
	        double rmax = rmaxView.getValue();
	        gridFactory.setRmax(rmax);
	    } catch (NumberFormatException exception) {
	    }
	    try {
	        double deltar0 = deltar0View.getValue();
	        gridFactory.setDeltar0(deltar0);
	    } catch (NumberFormatException exception) {
        }
	    try {
	        int intervalCount = (int)intervalCountView.getValue();
	        gridFactory.setIntervalCount(intervalCount);
	    } catch (NumberFormatException exception) {
        }
	    
        setFieldValuesFromData();
	}

}
