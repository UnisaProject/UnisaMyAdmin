package za.ac.unisa.myadmin.generic.services;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.generic.dto.EmailLogInfo;

/**
 * Created by Adrian on 2018-07-30.
 */
public interface EmailLogService {

	/**
	 * Gets latest email request by date sent.
	 * @param recipient
	 * @param program
	 * @param emailType
	 * @param reqSystem
	 * @param reqProgram
	 * @param emailBody
	 * @return
	 * @throws MissingParameterException
	 * @throws InvalidParameterException
	 * @throws OperationFailedException
	 * @throws DoesNotExistException
	 */
	EmailLogInfo getLastestEmailRequestForAcademicRecord(String recipient, String program, String emailType, String reqSystem, String reqProgram, String emailBody) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;
}