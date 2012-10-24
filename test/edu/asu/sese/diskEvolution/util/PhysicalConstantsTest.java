package edu.asu.sese.diskEvolution.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.asu.sese.diskEvolution.util.PhysicalConstants;

public class PhysicalConstantsTest {

	@Test
	public void testAuInCmDefined() {
		assertEquals(1.496e13, PhysicalConstants.auInCm, 1e10);
	}
	
	@Test
	public void testEarthRadiudInCmDefined() {
		assertEquals(6.371e8, PhysicalConstants.earthRadiusInCm, 1e10);
	}
}

