package za.ac.unisa.myadmin.service.parceltracking.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.parceltracking.ParcelTrackingInfo;
import za.ac.unisa.myadmin.parceltracking.ParcelTrackingService;

@RestController
@RequestMapping({"/rest"})
public class ParcelTrackingRestService {

	@Autowired
	private ParcelTrackingService trackingService;

	@GetMapping(path = "/trackandtrace/{userId}")
	public ParcelTrackingInfo trackStudentParcel(@PathVariable(value = "userId", required = true) Integer userId) throws OperationFailedException {
		return trackingService.trackAndTraceParcel(userId);
	}

}
