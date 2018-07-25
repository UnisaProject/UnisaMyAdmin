package za.ac.unisa.myadmin.studymaterial.services.rest;

import za.ac.unisa.myadmin.studymaterial.services.dto.StudyMaterialDetailInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/")
public interface StudyMaterialRestService {

	@GET
	@Path("/studymaterial/viewmaterial")
	@Produces("application/json")
	@Consumes("application/json")
	public List<StudyMaterialDetailInfo> getModuleStudyMaterials(@QueryParam("moduleCode") String moduleCode,
																 @QueryParam("academicYear") Integer academicYear,
																 @QueryParam("semesterCode") String semesterCode,
																 @Context UriInfo uriInfo) throws Exception;
}
