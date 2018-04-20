package za.ac.unisa.myadmin.service.exam.period.impl;

import java.util.List;
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
		try {
			ExamPeriodEntity entity = examPeriodRepository.findOne(code);
			return entity.toDto();
		} catch (EntityNotFoundException e) {
			throw new DoesNotExistException(Integer.toString(code));
		}
	}

	@Override
	public List<ExamPeriodInfo> getExamPeriods() throws OperationFailedException {
		try {
			return examPeriodRepository.findAll().stream().map(entity -> entity.toDto()).collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamPeriodInfo> getExamPeriodByCodes(List<Integer> codes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examPeriodRepository.findAll(codes).stream().map(entity -> entity.toDto())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

}
