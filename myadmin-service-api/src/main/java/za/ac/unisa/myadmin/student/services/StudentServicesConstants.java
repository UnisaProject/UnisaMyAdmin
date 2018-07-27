package za.ac.unisa.myadmin.student.services;

import za.ac.unisa.myadmin.module.services.AcademicModuleRecordService;
import za.ac.unisa.myadmin.module.services.rest.AcademicModuleRecordRestService;
import za.ac.unisa.myadmin.qualification.services.StudentAcademicRecordService;

public class StudentServicesConstants {

	// Services part of the namespaces
	public static final String STUDENT_SERVICE_NAME = StudentService.class.getSimpleName();
	public static final String STUDENT_ANNUAL_SERVICE_NAME = StudentAnnualService.class.getSimpleName();

	//Academic Record
	public static final String STUDENT_ACADEMIC_RECORD_SERVICE_NAME = StudentAcademicRecordService.class.getSimpleName();
	public static final String STUDENT_ACADEMIC_MODULE_RECORD_SERVICE_NAME = AcademicModuleRecordService.class.getSimpleName();

	// StudyMaterial proxy default values
	public static final int STUDY_MATERIAL_PROXY_USER_NUMBER = 9999;
	public static final String STUDY_MATERIAL_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION = "D";

}
