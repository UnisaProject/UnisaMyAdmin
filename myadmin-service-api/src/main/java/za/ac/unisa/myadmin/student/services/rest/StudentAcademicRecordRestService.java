package za.ac.unisa.myadmin.student.services.rest;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.dto.StudentAcademicStudyUnitResultInfo;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;


@Path("/")
public interface StudentAcademicRecordRestService {

	@GET
	@Path("/studentacademicqualifications")
	@Produces("application/json")
	@Consumes("application/json")
	public List<StudentAcademicQualificationRecordInfo> requestStudentAcademicQualificationResults(@QueryParam("studentNumber") Integer studentNumber)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	@GET
	@Path("/studentacademicqualification/modules")
	@Produces("application/json")
	@Consumes("application/json")
	public List<StudentAcademicStudyUnitResultInfo> requestStudentAcademicModuleResults(@QueryParam("studentNumber") Integer studentNumber, @QueryParam("isCreditsOnly") boolean isCreditsOnly, @QueryParam("selectedQualificationCode") String selectedQualificationCode)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;


}
