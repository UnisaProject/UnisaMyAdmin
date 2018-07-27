package za.ac.unisa.myadmin.generic.services;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.generic.dto.GenericCodeInfo;
import za.ac.unisa.myadmin.generic.dto.GenericMessageInfo;

import java.util.List;

/**
 * Created by Adrian on 2018-07-27.
 */
public interface UnisaGenericService {

	public List<GenericCodeInfo> getGenericCodesByCategoryOrdered(Integer genericCategoryCode, String orderBy) throws OperationFailedException;

	public GenericMessageInfo getGenericMessageById(String messageCode, String program) throws DoesNotExistException;
}
