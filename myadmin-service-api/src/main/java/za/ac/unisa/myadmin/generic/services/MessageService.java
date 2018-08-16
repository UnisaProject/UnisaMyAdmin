package za.ac.unisa.myadmin.generic.services;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.generic.dto.CodeInfo;
import za.ac.unisa.myadmin.generic.dto.MessageInfo;

import java.util.List;

/**
 * MessageService for reading from GenericMessageEntity
 * Not related to emailing etc.
 * Created by Adrian on 2018-07-27.
 */
public interface MessageService {

	MessageInfo getMessageById(String messageCode, String program) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;
}
