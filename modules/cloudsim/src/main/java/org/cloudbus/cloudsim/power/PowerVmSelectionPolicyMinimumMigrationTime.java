/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.cloudbus.cloudsim.power;

import java.util.List;
import java.util.Set;

import org.cloudbus.cloudsim.core.GuestEntity;
import org.cloudbus.cloudsim.selectionPolicies.SelectionPolicy;

/**
 * A VM selection policy that selects for migration the VM with Minimum Migration Time (MMT).
 * 
 * <br/>If you are using any algorithms, policies or workload included in the power package please cite
 * the following paper:<br/>
 * 
 * <ul>
 * <li><a href="http://dx.doi.org/10.1002/cpe.1867">Anton Beloglazov, and Rajkumar Buyya, "Optimal Online Deterministic Algorithms and Adaptive
 * Heuristics for Energy and Performance Efficient Dynamic Consolidation of Virtual Machines in
 * Cloud Data Centers", Concurrency and Computation: Practice and Experience (CCPE), Volume 24,
 * Issue 13, Pages: 1397-1420, John Wiley &amp; Sons, Ltd, New York, USA, 2012</a>
 * </ul>
 * 
 * @author Anton Beloglazov
 * @since CloudSim Toolkit 3.0
 */
public class PowerVmSelectionPolicyMinimumMigrationTime implements SelectionPolicy<GuestEntity> {
	// @TODO: Remo Andreoli: how does it compute the MMT???
	@Override
	public GuestEntity select(List<GuestEntity> candidates, Object obj, Set<GuestEntity> excludedCandidates) {
		if (candidates.isEmpty()) {
			return null;
		}

		GuestEntity selectedGuest = null;
		double minMetric = Double.MAX_VALUE;

		for (GuestEntity vm : candidates) {
			if (vm.isInMigration()) {
				continue;
			}
			double metric = vm.getRam();
			if (metric < minMetric) {
				minMetric = metric;
				selectedGuest = vm;
			}
		}
		return selectedGuest;
	}
}
