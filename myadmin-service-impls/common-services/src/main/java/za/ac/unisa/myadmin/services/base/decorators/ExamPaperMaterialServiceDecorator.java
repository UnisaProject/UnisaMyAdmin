package za.ac.unisa.myadmin.services.base.decorators;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.ExamPaperMaterialService;
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperMaterialInfo;

import java.util.List;

public class ExamPaperMaterialServiceDecorator implements ExamPaperMaterialService {

	private ExamPaperMaterialService nextDecorator;

	public ExamPaperMaterialService getNextDecorator() throws OperationFailedException {
		if (null == nextDecorator) {
			throw new OperationFailedException("Misconfigured application: nextDecorator is null");
		}
		return nextDecorator;
	}

	public void setNextDecorator(ExamPaperMaterialService nextDecorator) {
		this.nextDecorator = nextDecorator;
	}


	@Override
	public List<ExamPaperMaterialInfo> getExamPapersByCourseCode(String courseCode) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExamPapersByCourseCode(courseCode);
	}
}
