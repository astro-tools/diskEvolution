package edu.asu.sese.diskEvolution.model;

import static org.junit.Assert.*;

import org.junit.*;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;
import edu.asu.sese.diskEvolution.util.GridFactory;

public class SnapshotCollectionTest {
    
    private SnapshotCollection collection;
    private DiskSimulation simulation;

    @Before
    public void setup() {
        GridFactory factory = new GridFactory();
        InitialConditions initialConditions = new InitialConditions();
        simulation = new DiskSimulation(factory, initialConditions);
        collection = new SnapshotCollection(simulation);
    }
        
    @Test
    public void testTakeSnapshot() {
        collection.takeSnapshot();
        assertEquals(1, collection.getSnapshotCount());
        Snapshot snapshot = collection.getSnapshot(0);
        assertNotNull(snapshot);
    }

}
