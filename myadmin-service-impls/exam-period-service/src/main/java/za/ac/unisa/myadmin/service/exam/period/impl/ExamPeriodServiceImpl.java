package za.ac.unisa.myadmin.service.exam.period.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.period.ExamPeriodInfo;
import za.ac.unisa.myadmin.exam.period.ExamPeriodService;
import za.ac.unisa.myadmin.service.exam.period.dao.ExamPeriodRepository;
import za.ac.unisa.myadmin.service.exam.period.model.ExamPeriodEntity;

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
	public List<ExamPeriodInfo> getExamPeriodByCodes(List<Integer> codes)
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
