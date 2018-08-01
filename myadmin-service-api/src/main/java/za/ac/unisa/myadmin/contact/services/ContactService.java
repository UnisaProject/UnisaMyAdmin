package za.ac.unisa.myadmin.contact.services;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.contact.services.dto.ContactInfo;

import java.util.List;

/**
 * Created by Adrian on 2018-08-01.
 */
public interface ContactService {

	ContactInfo getContactInfoForReferenceAndType(Integer referenceNumber, Integer addressTypeCode) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	List<ContactInfo> getContactsByReferenceNumber(Integer referenceNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException;
}
