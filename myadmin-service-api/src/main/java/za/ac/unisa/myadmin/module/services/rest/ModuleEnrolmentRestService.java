package za.ac.unisa.myadmin.module.services.rest;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.dto.ModuleEnrolmentInfo;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/")
public interface ModuleEnrolmentRestService {

	@POST
	@Path("/moduleEnrolment/courselist")
	@Produces("application/json")
	@Consumes("application/json")
	public List<ModuleEnrolmentInfo> requestStudentModuleEnrolments(StudentInfo student)
			throws OperationFailedException, MissingParameterException, InvalidParameterException,
			DoesNotExistException;

}
