package za.ac.unisa.myadmin.student.services.rest;

import za.ac.unisa.myadmin.common.dto.ErrorInfo;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.qualification.services.dto.AcademicRecordEmailRequestInfo;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/")
public interface StudentAcademicRecordRestService {

	@GET
	@Path("/academicrecord/qualifications")
	@Produces(MediaType.APPLICATION_JSON )
	@Consumes(MediaType.APPLICATION_JSON )
	public List<StudentAcademicQualificationRecordInfo> getStudentAcademicQualificationResults(@QueryParam("studentNumber") Integer studentNumber)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	@POST
	@Path("/academicrecord/email")
	@Produces(MediaType.APPLICATION_JSON )
	@Consumes(MediaType.APPLICATION_JSON )
	public ErrorInfo sendStudentAcademicRecordEmail(AcademicRecordEmailRequestInfo emailRequestInfo)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

}
