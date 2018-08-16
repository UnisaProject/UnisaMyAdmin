package za.ac.unisa.myadmin.student.services.impls;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.generic.dto.MessageInfo;
import za.ac.unisa.myadmin.generic.services.MessageService;
import za.ac.unisa.myadmin.student.services.jpa.models.GenericMessageEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.GenericMessageEntityId;
import za.ac.unisa.myadmin.student.services.repositories.GenericMessageRepository;

import java.util.Optional;

/**
 * Created by Adrian on 2018-07-27.
 */
public class MessageServiceImpl implements MessageService {

	private GenericMessageRepository genericMessageRepository;

	public void setGenericMessageRepository(GenericMessageRepository genericMessageRepository) {
		this.genericMessageRepository = genericMessageRepository;
	}

	@Override
	public MessageInfo getMessageById(String messageCode, String program) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		GenericMessageEntityId entityId = new GenericMessageEntityId(messageCode, program);
		Optional<GenericMessageEntity> entity = genericMessageRepository.findById(entityId);
		if (entity.isPresent()) {
			return entity.get().toDto();
		} else {
			return null;
		}
	}
}
