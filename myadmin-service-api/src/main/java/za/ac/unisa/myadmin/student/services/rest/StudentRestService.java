package za.ac.unisa.myadmin.student.services.rest;

import java.util.Date;
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
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

@Path("/")
public interface StudentRestService {

	@GET
	@Path("/students/{studentNumber}")
	@Produces("application/json")
	@Consumes("application/json")
	public StudentInfo getStudentByNumber(@PathParam("studentNumber") Integer studentNumber)
			throws MissingParameterException, InvalidParameterException, OperationFailedException,
			DoesNotExistException;

	@GET
	@Path("/students")
	@Produces("application/json")
	@Consumes("application/json")
	public List<StudentInfo> searchStudents(@QueryParam("surname") String surname,
			@QueryParam("firstName") String firstName, @QueryParam("dateOfBirth") Date dateOfBirth,
			@QueryParam("identityNumber") String identityNumber, @QueryParam("passportNumber") String passportNumber,
			@Context UriInfo uriInfo) throws MissingParameterException, InvalidParameterException,
			OperationFailedException, DoesNotExistException;
}
