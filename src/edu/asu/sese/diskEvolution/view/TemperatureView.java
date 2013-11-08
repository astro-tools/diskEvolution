package edu.asu.sese.diskEvolution.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class TemperatureView extends JPanel{
	private static final long serialVersionUID = 1L;


	public TemperatureView(){
		setupPanels();
		//all the methods like make the temperature chart, labels and Field etc
	}
	
	
	private void setupPanels() {
	    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));	
		}

}