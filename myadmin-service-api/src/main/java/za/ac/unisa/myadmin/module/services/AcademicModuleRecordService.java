package za.ac.unisa.myadmin.module.services;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.dto.AcademicModuleRecordInfo;

import java.util.List;

/**
 * Created by Adrian on 2018-06-26.
 */
public interface AcademicModuleRecordService {

	public List<AcademicModuleRecordInfo> getAcademicModules(Integer studentNumber, boolean isCreditsOnly, String selectedQualificationCode) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;



}
