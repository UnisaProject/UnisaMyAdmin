package za.ac.unisa.myadmin.parceltracking;

import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;

public interface ParcelTrackingService {

	/**
	 * Processes a request to get tracking info from parcel proxy
	 *
	 * @param userId The student number for tracking
	 * @return A tracking info for the requested student
	 * @throws OperationFailedException
	 */
	ParcelTrackingInfo trackAndTraceParcel(Integer userId) throws OperationFailedException;
}
