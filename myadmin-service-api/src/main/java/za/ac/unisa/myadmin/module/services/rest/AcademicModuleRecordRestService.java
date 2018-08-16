package za.ac.unisa.myadmin.module.services.rest;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.dto.AcademicModuleRecordInfo;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by Adrian on 2018-06-26.
 */
public interface AcademicModuleRecordRestService {

	@GET
	@Path("/academicmodules")
	@Produces("application/json")
	@Consumes("application/json")
	public List<AcademicModuleRecordInfo> getAcademicModules(@QueryParam("studentNumber") Integer studentNumber, @QueryParam("isCreditsOnly") boolean isCreditsOnly, @QueryParam("selectedQualificationCode") String selectedQualificationCode)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;


}
