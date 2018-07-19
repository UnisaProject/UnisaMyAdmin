package za.ac.unisa.myadmin.tracking.services.rest.impl;

import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.services.base.decorators.TrackingServiceDecorator;
import za.ac.unisa.myadmin.tracking.services.dto.PackageInfo;
import za.ac.unisa.myadmin.tracking.services.rest.TrackingRestService;

public class TrackingRestServiceImpl extends TrackingServiceDecorator implements TrackingRestService {

	@Override
	public PackageInfo trackPackageByStudent(Integer studentNumber) throws OperationFailedException {
		return getNextDecorator().trackPackageByStudent(studentNumber);
	}

}
