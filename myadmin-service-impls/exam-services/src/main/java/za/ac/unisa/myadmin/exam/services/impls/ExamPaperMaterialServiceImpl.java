package za.ac.unisa.myadmin.exam.services.impls;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.integration.services.ExamPaperMaterialWebServiceClient;
import za.ac.unisa.myadmin.exam.services.ExamPaperMaterialService;
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperMaterialInfo;
import za.ac.unisa.myadmin.services.base.decorators.ExamPaperMaterialServiceDecorator;

import java.util.List;

public class ExamPaperMaterialServiceImpl extends ExamPaperMaterialServiceDecorator implements ExamPaperMaterialService {

	private ExamPaperMaterialWebServiceClient webServiceClient;

	public void setWebServiceClient(ExamPaperMaterialWebServiceClient webServiceClient){
		this.webServiceClient = webServiceClient;
	}

	@Override
	public List<ExamPaperMaterialInfo> getExamPapersByModuleCode(String moduleCode) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return webServiceClient.getExamPapersByModuleCode(moduleCode);
	}
}
