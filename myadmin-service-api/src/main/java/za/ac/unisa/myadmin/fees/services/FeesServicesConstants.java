package za.ac.unisa.myadmin.fees.services;

public class FeesServicesConstants {

	// Services part of the namespaces
	public static final String STUDY_FEE_QUOTE_SERVICE_NAME = StudyFeeQuotationService.class.getSimpleName();

	/**
	 * The semester type to use when querying for quotation dates
	 */
	public static final String STUDY_QUOTE_REGISTRATION_PERIOD_TYPE = "IQ";

	/**
	 * The semester period to use when querying for quotation dates
	 */
	public static final int STUDY_QUOTE_REGISTRATION_PERIOD_SEMESTER = 0;

	// StudyQuotation fee proxy default values
	public static final String STUDY_QUOTE_FEE_PROXY_STUDENT_MK_CORRESPONDENCE_LANGUAGE = "E";
	public static final String STUDY_QUOTE_FEE_PROXY_STUDENT_SURNAME = "A";
	public static final String STUDY_QUOTE_FEE_PROXY_STUDENT_INITIALS = "A";
	public static final int STUDY_QUOTE_FEE_PROXY_ANNUAL_RECORD_MK_STUDENT_NBR = 0;
	public static final String STUDY_QUOTE_FEE_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION = "P";
	public static final short STUDY_QUOTE_FEE_PROXY_ADDRESS_POSTAL_CODE = 1;
	public static final int STUDY_QUOTE_FEE_PROXY_USER_NUMBER = 9999;

}
