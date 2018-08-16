package za.ac.unisa.myadmin.generic.services;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.generic.dto.CodeInfo;

import java.util.List;

/**
 * Created by Adrian on 2018-07-27.
 */
public interface CodeService {

	CodeInfo getCodeByCodeAndCategory(String code, Integer categoryCode) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	List<CodeInfo> getCodesByCategoryOrdered(Integer genericCategoryCode, String orderBy) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;
}
