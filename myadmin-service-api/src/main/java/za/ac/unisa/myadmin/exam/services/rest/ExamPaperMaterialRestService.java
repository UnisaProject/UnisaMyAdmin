package za.ac.unisa.myadmin.exam.services.rest;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperMaterialInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public interface ExamPaperMaterialRestService {

	@GET
	@Path("/exampapermaterial")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<ExamPaperMaterialInfo> getExamPapers(@QueryParam("courseCode") String courseCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException,
			DoesNotExistException;
}
