package za.ac.unisa.myadmin.student.services.impls;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.contact.services.ContactService;
import za.ac.unisa.myadmin.contact.services.dto.ContactInfo;
import za.ac.unisa.myadmin.student.services.jpa.models.ContactEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.ContactEntityId;
import za.ac.unisa.myadmin.student.services.repositories.ContactRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Adrian on 2018-08-01.
 */
public class ContactServiceImpl implements ContactService {


	private ContactRepository contactRepository;

	public void setContactRepository(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@Override
	public ContactInfo getContactByReferenceAndType(Integer referenceNumber, Integer addressTypeCode) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		Optional<ContactEntity> entity = contactRepository.findById(new ContactEntityId(referenceNumber, addressTypeCode));
		if (entity.isPresent()) {
			return entity.get().toDto();
		} else {
			return null;
		}
	}

	@Override
	public List<ContactInfo> getContactsByReference(Integer referenceNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return contactRepository.findByReferenceNumber(referenceNumber)
				.stream()
				.map(ContactEntity::toDto)
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}
}
