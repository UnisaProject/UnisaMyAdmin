package za.ac.unisa.myadmin.exam.service.impls;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.dto.ExamPeriodInfo;
import za.ac.unisa.myadmin.exam.service.ExamPeriodService;
import za.ac.unisa.myadmin.exam.service.models.ExamPeriodEntity;
import za.ac.unisa.myadmin.exam.service.repositories.ExamPeriodRepository;

@Service("ExamPeriodService")
public class ExamPeriodServiceImpl implements ExamPeriodService {

	@Autowired
	private ExamPeriodRepository examPeriodRepository;

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
