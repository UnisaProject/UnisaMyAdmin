package za.ac.unisa.myadmin.module.services.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.dto.ModuleEnrolmentInfo;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;
import za.ac.unisa.myadmin.studymaterial.services.dto.StudyMaterialDetailInfo;

@Path("/")
public interface ModuleEnrolmentRestService {

	@POST
	@Path("/studymaterial/courselist")
	@Produces("application/json")
	@Consumes("application/json")
	public List<ModuleEnrolmentInfo> requestStudentModuleEnrolments(StudentInfo student)
			throws OperationFailedException, MissingParameterException, InvalidParameterException,
			DoesNotExistException;

	@GET
	@Path("/studymaterial/viewmaterial")
	@Produces("application/json")
	@Consumes("application/json")
	public List<StudyMaterialDetailInfo> getModuleStudyMaterials(@QueryParam("moduleCode") String moduleCode,
			@QueryParam("academicYear") Integer academicYear, @QueryParam("semesterCode") String semesterCode,
			@Context UriInfo uriInfo) throws Exception;

	@POST
	@Path("/studymaterial/download")
	@Produces("application/pdf")
	@Consumes("application/json")
	public Response generateActivatedMaterialsPDFReport(StudyMaterialDetailInfo materialInfo)
			throws OperationFailedException;
}
