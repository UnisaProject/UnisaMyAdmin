package za.ac.unisa.myadmin.exam.services.decorators;

import org.apache.commons.lang3.StringUtils;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperMaterialInfo;
import za.ac.unisa.myadmin.services.base.decorators.ExamPaperMaterialServiceDecorator;

import java.util.List;

public class ExamPaperMaterialServiceValidationDecorator extends ExamPaperMaterialServiceDecorator {

	@Override
	public List<ExamPaperMaterialInfo> getExamPapersByModuleCode(String courseCode) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		if(StringUtils.isEmpty(courseCode)){
			throw new MissingParameterException("A course code must be provided");
		}
		return getNextDecorator().getExamPapersByModuleCode(courseCode);
	}
}
