package za.ac.unisa.myadmin.exam.services.decorators;

import java.time.Year;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.ExamAdmissionService;
import za.ac.unisa.myadmin.exam.services.dto.ExamAdmissionInfo;
import za.ac.unisa.myadmin.services.base.decorators.ExamAdmissionServiceDecorator;

/**
 * This decorator calculate the current, previous and next year values and
 * filters out the ExamAdmission records where the year value not equals any of
 * the calculated years.
 * 
 * @author Jannie Louwrens
 *
 */
public class ExamAdmissionServiceYearCalculationDecorator extends ExamAdmissionServiceDecorator
		implements ExamAdmissionService {

	@Override
	public List<ExamAdmissionInfo> getExamAdmissions() throws OperationFailedException {
		List<ExamAdmissionInfo> examAdmissions = getNextDecorator().getExamAdmissions();

		Set<Integer> years = calculateCurrentYears();

		return examAdmissions.stream().filter(examAdmission -> years.contains(examAdmission.getYear()))
				.collect(Collectors.toList());
	}

	private Set<Integer> calculateCurrentYears() {
		Set<Integer> years = new HashSet<>();
		int currentYear = Year.now(ZoneId.systemDefault()).getValue();
		years.add(currentYear);
		years.add(currentYear - 1);
		years.add(currentYear + 1);

		return years;
	}

}
