package za.ac.unisa.myadmin.registration.services.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.registration.services.dto.RegistrationPeriodInfo;

@Path("/")
public interface RegistrationPeriodRestService {

	@GET
	@Path("/registrationperiods")
	@Produces("application/json")
	@Consumes("application/json")
	public List<RegistrationPeriodInfo> searchForRegistrationPeriods(
			@QueryParam("year") Integer year, @QueryParam("semester") Integer semester, @QueryParam("type") String type,
			@QueryParam("date") Date date, @Context UriInfo uriInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

}
