package za.ac.unisa.myadmin.service.studyquotation.date;

import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;

public interface StudyQuotationDateService {

	int getValidQuotationYear() throws OperationFailedException;
}
