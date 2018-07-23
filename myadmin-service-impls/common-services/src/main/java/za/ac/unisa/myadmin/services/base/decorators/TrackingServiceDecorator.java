package za.ac.unisa.myadmin.services.base.decorators;

import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.tracking.services.TrackingService;
import za.ac.unisa.myadmin.tracking.services.dto.PackageInfo;

public class TrackingServiceDecorator implements TrackingService {

	private TrackingService nextDecorator;

	public TrackingService getNextDecorator() throws OperationFailedException {
		if (null == nextDecorator) {
			throw new OperationFailedException("Misconfigured application: nextDecorator is null");
		}
		return nextDecorator;
	}

	public void setNextDecorator(TrackingService nextDecorator) {
		this.nextDecorator = nextDecorator;
	}

	@Override
	public PackageInfo trackPackageByStudent(Integer studentNumber) throws OperationFailedException {
		return getNextDecorator().trackPackageByStudent(studentNumber);
	}

}
