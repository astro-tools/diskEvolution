package edu.asu.sese.diskEvolution.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.asu.sese.diskEvolution.util.PhysicalConstants;

public class PhysicalConstantsTest {

	@Test
	public void testAuInCmDefined() {
		assertEquals(1.496e13, PhysicalConstants.earthRadiusInCm, 1e10);
	}

}
