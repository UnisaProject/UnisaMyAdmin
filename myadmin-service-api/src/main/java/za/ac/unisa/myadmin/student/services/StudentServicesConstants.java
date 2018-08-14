package za.ac.unisa.myadmin.student.services;

import za.ac.unisa.myadmin.qualification.services.StudentAcademicRecordService;

public class StudentServicesConstants {

	// Services part of the namespaces
	public static final String STUDENT_SERVICE_NAME = StudentService.class.getSimpleName();
	public static final String STUDENT_ANNUAL_SERVICE_NAME = StudentAnnualService.class.getSimpleName();

	//Academic Record
	public static final String STUDENT_ACADEMIC_RECORD_SERVICE_NAME = StudentAcademicRecordService.class.getSimpleName();

	// StudyMaterial proxy default values
	public static final int STUDY_MATERIAL_PROXY_USER_NUMBER = 9999;
	public static final String STUDY_MATERIAL_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION = "D";

	//Academic Record proxy defaults
	public static final int ACADEMIC_RECORD_PROXY_FUNCTION_NUMBER = 135;
	public static final String ACADEMIC_RECORD_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION = "D";

	//Academic Record email proxy defaults
	public static final int ACADEMIC_RECORD_EMAIL_PROXY_USER_NUMBER = 99998;
	public static final String ACADEMIC_RECORD_EMAIL_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION = "A";
	public static final String ACADEMIC_RECORD_EMAIL_PROXY_REPORTING_CONTROL_PRINTER_CODE = "MYUNISA";
	public static final String ACADEMIC_RECORD_EMAIL_PROXY_FROM_EMAIL_ADDRESS = "noreply@unisa.ac.za";

	//Academic Record Constants
	//Proxy package
	public static final String ACADEMIC_RECORD_EMAIL_LOG_DEFAULT_PROGRAM = "SRSRJ11H";
	public static final String ACADEMIC_RECORD_EMAIL_LOG_REQUIRED_SYSTEM = "MYUNISA";
	public static final String ACADEMIC_RECORD_EMAIL_LOG_REQUIRED_PROGRAM = "ACADEMIC RECORD";
	public static final String ACADEMIC_RECORD_EMAIL_LOG_DECLARE_NO_MARKS = "SUBJDECLNM";
	public static final String ACADEMIC_RECORD_EMAIL_LOG_DECLARE_WITH_MARKS = "SUBJDECLWM";
	public static final int ACADEMIC_RECORD_ADDRESS_CAT_UNISA_STUDENT = 1;
	public static final String ACADEMIC_RECORD_FUNCTION_TYPE_NORMAL = "NORMAL";
	public static final String ACADEMIC_RECORD_FUNCTION_TYPE_PRELIMINARY = "PRELIM";
}
