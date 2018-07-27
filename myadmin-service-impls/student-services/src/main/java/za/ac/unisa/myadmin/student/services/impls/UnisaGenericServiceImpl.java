package za.ac.unisa.myadmin.student.services.impls;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.generic.dto.GenericCodeInfo;
import za.ac.unisa.myadmin.generic.dto.GenericMessageInfo;
import za.ac.unisa.myadmin.generic.services.UnisaGenericService;
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
public class UnisaGenericServiceImpl implements UnisaGenericService {

	private GenericCodeRepository genericCodeRepository;

	private GenericMessageRepository genericMessageRepository;

	public void setGenericMessageRepository(GenericMessageRepository genericMessageRepository) {
		this.genericMessageRepository = genericMessageRepository;
	}

	public void setGenericCodeRepository(GenericCodeRepository genericCodeRepository) {
		this.genericCodeRepository = genericCodeRepository;
	}

	@Override
	public List<GenericCodeInfo> getGenericCodesByCategoryOrdered(Integer genericCategoryCode, String orderBy) throws OperationFailedException {
		List<GenericCodeEntity> entity;
		try {
			switch (orderBy) {
				case "code":
					entity = genericCodeRepository.findByGenericCategoryCodeOrderByCode(genericCategoryCode);
					break;
				case "english":
					entity = genericCodeRepository.findByGenericCategoryCodeOrderByEnglishDescription(genericCategoryCode);
					break;
				case "afrikaans":
					entity = genericCodeRepository.findByGenericCategoryCodeOrderByAfrikaansDescription(genericCategoryCode);
					break;
				case "inuse":
					entity = genericCodeRepository.findByGenericCategoryCodeOrderByInUse(genericCategoryCode);
					break;
				default:
					entity = genericCodeRepository.findByGenericCategoryCode(genericCategoryCode);
			}

			return entity.stream()
				.map(GenericCodeEntity::toDto)
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public GenericMessageInfo getGenericMessageById(String messageCode, String program) throws DoesNotExistException {
		GenericMessageEntityId entityId = new GenericMessageEntityId(messageCode, program);
		Optional<GenericMessageEntity> entity = genericMessageRepository.findById(entityId);
		if (entity.isPresent()) {
			return entity.get().toDto();
		} else {
			return null;
		}
	}
}
