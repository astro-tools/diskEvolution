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
<<<<<<< HEAD
        double density0 = 1e3;
		double radius0 = PhysicalConstants.auInCm;
		double exponent = 1.5;
		densityGrid.initialize(density0, radius0, exponent);
=======
        densityGrid.initializeWithPowerLaw(1e3, PhysicalConstants.auInCm, -1.5);
        
        viscosityGrid = new ViscosityGrid(radialGrid);
        viscosityGrid.initializeWithPowerLaw(1e12, PhysicalConstants.auInCm, 1.0)
>>>>>>> 1b2a2282f4778ce418d87b484ddde7464b974ab5
        
        System.out.print("Running DiskSimulation");
        
        DiskView view = new DiskView(radialGrid, densityGrid);
        view.setVisible(true);
    }

}
