package edu.asu.sese.diskEvolution;

import static org.junit.Assert.*;

import org.junit.Test;

public class PhysicalConstantsTest {

	@Test
	public void testAuInCmDefined() {
		assertEquals(1.496e13, PhysicalConstants.auInCm, 1e10);
	}

}
