package za.ac.unisa.myadmin.tracking.services;

import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.tracking.services.dto.PackageInfo;

public interface TrackingService {

	/**
	 * Processes a request to get tracking info from parcel proxy
	 *
	 * @param studentNumber
	 *            The student number for tracking
	 * @return A tracking info for the requested student
	 * @throws OperationFailedException
	 */
	public PackageInfo trackPackageByStudent(Integer studentNumber) throws OperationFailedException;
}
