package za.ac.unisa.myadmin.exam.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

public interface ExamAdmissionRestService {

	@GET
	@Path("/examadmissions")
	@Produces("application/json")
	@Consumes("application/json")
	public List<String> searchForExamAdmissions(@QueryParam("year") Integer year,
			@QueryParam("examPeriodCode") Integer examPeriodCode, @QueryParam("examType") Integer examType,
			@Context UriInfo uriInfo)
			throws Exception;

}
