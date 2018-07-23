package za.ac.unisa.myadmin.exam.services.rest;

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
import za.ac.unisa.myadmin.exam.services.dto.ExaminationInfo;

@Path("/")
public interface ExaminationRestService {

	@GET
	@Path("/examinations")
	@Produces("application/json")
	@Consumes("application/json")
	public List<ExaminationInfo> searchForExaminations(@QueryParam("year") Integer year,
			@QueryParam("examPeriodCode") Integer examPeriodCode, @QueryParam("courseCode") List<String> courseCodes,
			@Context UriInfo uriInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

}
