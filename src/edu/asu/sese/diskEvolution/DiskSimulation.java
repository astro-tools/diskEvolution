package edu.asu.sese.diskEvolution;


public class DiskSimulation {

    private static RadialGrid radialGrid;
    private static DensityGrid densityGrid;

    public static void main(String[] args) {
        double rmin = 0.1 * PhysicalConstants.auInCm;
        double rmax = 200.0 * PhysicalConstants.auInCm;
        double deltar0 = 0.1 * PhysicalConstants.auInCm;
        int intervalCount = 50;
        radialGrid = new RadialGrid(rmin , rmax , deltar0 , intervalCount);
        densityGrid = new DensityGrid(radialGrid);
        densityGrid.initializeWithPowerLaw(1e3, PhysicalConstants.auInCm, -1.5);
        
        System.out.print("Running DiskSimulation");
        
        DiskView view = new DiskView(radialGrid, densityGrid);
        view.setVisible(true);
    }

}
