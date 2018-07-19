package za.ac.unisa.myadmin.exam.services.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.dto.ExamPeriodInfo;

@Path("/")
public interface ExamPeriodRestService {

	@GET
	@Path("/examperiods/{code}")
	@Produces("application/json")
	@Consumes("application/json")
	public ExamPeriodInfo getExamPeriod(@PathParam("code") Integer code) throws DoesNotExistException,
			MissingParameterException, InvalidParameterException, OperationFailedException;

	@GET
	@Path("/examperiods")
	@Produces("application/json")
	@Consumes("application/json")
	public List<ExamPeriodInfo> searchForExamPeriods(@QueryParam("code") List<Integer> codes, @Context UriInfo uriInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

}
