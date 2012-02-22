package edu.asu.sese.diskEvolution;


public class DiskSimulation {

    private static RadialGrid radialGrid;
    private static DensityGrid densityGrid;
    private static ViscosityGrid viscosityGrid;

    public static void main(String[] args) {
        double rmin = 0.1 * PhysicalConstants.auInCm;
        double rmax = 200.0 * PhysicalConstants.auInCm;
        double deltar0 = 0.1 * PhysicalConstants.auInCm;
        int intervalCount = 50;
        radialGrid = new RadialGrid(rmin , rmax , deltar0 , intervalCount);
        
        densityGrid = new DensityGrid(radialGrid);
        double density0 = 1e3;
		double radius0 = PhysicalConstants.auInCm;
		double exponent = -1.5;
		densityGrid.initializeWithPowerLaw(density0, radius0, exponent);
        
        viscosityGrid = new ViscosityGrid(radialGrid);
        viscosityGrid.initializeWithPowerLaw(1e12, PhysicalConstants.auInCm, 1.0);
        
        System.out.print("Running DiskSimulation");
        
        DiskView view = new DiskView(radialGrid, densityGrid);
        view.setVisible(true);
    }

}
