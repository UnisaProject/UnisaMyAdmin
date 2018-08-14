package za.ac.unisa.myadmin.student.services.impls;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.generic.dto.GenericCodeInfo;
import za.ac.unisa.myadmin.generic.dto.GenericMessageInfo;
import za.ac.unisa.myadmin.generic.services.GenericServicesConstants;
import za.ac.unisa.myadmin.generic.services.GenericService;
import za.ac.unisa.myadmin.student.services.jpa.models.GenericCodeEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.GenericMessageEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.GenericMessageEntityId;
import za.ac.unisa.myadmin.student.services.repositories.GenericCodeRepository;
import za.ac.unisa.myadmin.student.services.repositories.GenericMessageRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Adrian on 2018-07-27.
 */
public class GenericServiceImpl implements GenericService {

	private GenericCodeRepository genericCodeRepository;

	private GenericMessageRepository genericMessageRepository;

	public void setGenericMessageRepository(GenericMessageRepository genericMessageRepository) {
		this.genericMessageRepository = genericMessageRepository;
	}

	public void setGenericCodeRepository(GenericCodeRepository genericCodeRepository) {
		this.genericCodeRepository = genericCodeRepository;
	}

	@Override
	public List<GenericCodeInfo> getGenericCodesByCategoryOrdered(Integer genericCategoryCode, String orderBy) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		List<GenericCodeEntity> entities;
		try {
			switch (orderBy) {
				case GenericServicesConstants.UNISA_GENERIC_CODE_ORDERBY_CODE:
					entities = genericCodeRepository.findByGenericCategoryCodeOrderByCode(genericCategoryCode);
					break;
				case GenericServicesConstants.UNISA_GENERIC_CODE_ORDERBY_ENG:
					entities = genericCodeRepository.findByGenericCategoryCodeOrderByEnglishDescription(genericCategoryCode);
					break;
				case GenericServicesConstants.UNISA_GENERIC_CODE_ORDERBY_AFR:
					entities = genericCodeRepository.findByGenericCategoryCodeOrderByAfrikaansDescription(genericCategoryCode);
					break;
				case GenericServicesConstants.UNISA_GENERIC_CODE_ORDERBY_INUSE:
					entities = genericCodeRepository.findByGenericCategoryCodeOrderByInUse(genericCategoryCode);
					break;
				default:
					entities = genericCodeRepository.findByGenericCategoryCode(genericCategoryCode);
			}

			return entities.stream()
				.map(GenericCodeEntity::toDto)
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public GenericMessageInfo getGenericMessageById(String messageCode, String program) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		GenericMessageEntityId entityId = new GenericMessageEntityId(messageCode, program);
		Optional<GenericMessageEntity> entity = genericMessageRepository.findById(entityId);
		if (entity.isPresent()) {
			return entity.get().toDto();
		} else {
			return null;
		}
	}
}
