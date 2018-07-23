package za.ac.unisa.myadmin.tracking.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.tracking.services.dto.PackageInfo;

@Path("/")
public interface TrackingRestService {

	@GET
	@Path("/track/{studentNumber}")
	@Produces("application/json")
	@Consumes("application/json")
	public PackageInfo trackPackageByStudent(@PathParam("studentNumber") Integer studentNumber)
			throws OperationFailedException;

}
