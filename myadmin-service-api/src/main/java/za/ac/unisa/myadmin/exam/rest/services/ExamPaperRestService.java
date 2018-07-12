package za.ac.unisa.myadmin.exam.rest.services;

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
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperInfo;

public interface ExamPaperRestService {

	@GET
	@Path("/exampapers")
	@Produces("application/json")
	@Consumes("application/json")
	public List<ExamPaperInfo> searchForExamPeriodDates(@QueryParam("year") Integer year,
			@QueryParam("courseCode") List<String> courseCodes, @Context UriInfo uriInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

}
