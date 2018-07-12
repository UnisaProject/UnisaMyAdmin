package za.ac.unisa.myadmin.exam.services.impls;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.ExamPeriodService;
import za.ac.unisa.myadmin.exam.services.dto.ExamPeriodInfo;
import za.ac.unisa.myadmin.exam.services.models.ExamPeriodEntity;
import za.ac.unisa.myadmin.exam.services.repositories.ExamPeriodRepository;

public class ExamPeriodServiceImpl implements ExamPeriodService {

	private ExamPeriodRepository examPeriodRepository;

	public void setExamPeriodRepository(ExamPeriodRepository examPeriodRepository) {
		this.examPeriodRepository = examPeriodRepository;
	}

	@Override
	public ExamPeriodInfo getExamPeriod(Integer code) throws DoesNotExistException, MissingParameterException,
			InvalidParameterException, OperationFailedException {
		Optional<ExamPeriodEntity> entity = examPeriodRepository.findById(code);
		if(entity.isPresent()) {
			return entity.get().toDto();
		}
		else {
			throw new DoesNotExistException(Integer.toString(code));
		}
	}

	@Override
	public List<ExamPeriodInfo> getExamPeriods() throws OperationFailedException {
		try {
			return examPeriodRepository.findAll()
					.stream()
					.map(ExamPeriodEntity::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamPeriodInfo> getExamPeriodsByCodes(List<Integer> codes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examPeriodRepository.findAllById(codes)
					.stream()
					.map(ExamPeriodEntity::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

}
