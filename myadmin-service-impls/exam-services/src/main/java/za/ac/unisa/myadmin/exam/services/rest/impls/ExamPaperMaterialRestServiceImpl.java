package za.ac.unisa.myadmin.exam.services.rest.impls;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperMaterialInfo;
import za.ac.unisa.myadmin.exam.services.rest.ExamPaperMaterialRestService;
import za.ac.unisa.myadmin.services.base.decorators.ExamPaperMaterialServiceDecorator;

import java.util.List;

public class ExamPaperMaterialRestServiceImpl extends ExamPaperMaterialServiceDecorator implements ExamPaperMaterialRestService {

	@Override
	public List<ExamPaperMaterialInfo> getExamPapers(String courseCode) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().getExamPapersByCourseCode(courseCode);
	}
}
