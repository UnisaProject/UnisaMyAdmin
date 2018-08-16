package za.ac.unisa.myadmin.student.services.impls;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.generic.dto.CodeInfo;
import za.ac.unisa.myadmin.generic.services.CodeService;
import za.ac.unisa.myadmin.generic.services.GenericServicesConstants;
import za.ac.unisa.myadmin.student.services.jpa.models.GenericCodeEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.GenericCodeEntityId;
import za.ac.unisa.myadmin.student.services.repositories.GenericCodeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Adrian on 2018-07-27.
 */
public class CodeServiceImpl implements CodeService {

	private GenericCodeRepository genericCodeRepository;

	public void setGenericCodeRepository(GenericCodeRepository genericCodeRepository) {
		this.genericCodeRepository = genericCodeRepository;
	}


	@Override
	public CodeInfo getCodeByCodeAndCategory(String code, Integer categoryCode) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		Optional<GenericCodeEntity> entity = genericCodeRepository.findById(new GenericCodeEntityId(code, categoryCode));
		if (entity.isPresent()) {
			return entity.get().toDto();
		} else {
			return null;
		}
	}

	@Override
	public List<CodeInfo> getCodesByCategoryOrdered(Integer genericCategoryCode, String orderBy) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
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

}
