package za.ac.unisa.myadmin.exam.services;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExamServicesConstants {
	
	// Services part of the namespaces
	public static final String EXAM_ADMISSION_SERVICE_NAME = ExamAdmissionService.class.getSimpleName();
	public static final String EXAMINATION_SERVICE_NAME = ExaminationService.class.getSimpleName();
	public static final String EXAM_PAPER_SERVICE_NAME = ExamPaperService.class.getSimpleName();
	public static final String EXAM_PERIOD_SERVICE_NAME = ExamPeriodService.class.getSimpleName();

	// ExamPeriodService types
	public static final Set<Integer> EXAM_PERIOD_OBSOLETE_CODES = Stream.of(8, 13).collect(Collectors.toSet());

	// ExaminationService types
	public static final String EXAM_TYPE_FINAL = "Final Examination Timetable";
	public static final String EXAM_TYPE_PROVISIONAL = "Provisional Examination Timetable";

}
