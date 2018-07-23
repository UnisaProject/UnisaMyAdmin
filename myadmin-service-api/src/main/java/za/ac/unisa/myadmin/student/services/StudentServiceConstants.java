package za.ac.unisa.myadmin.student.services;

public class StudentServiceConstants {

	/**
	 * The semester type to use when querying for quotation dates
	 */
	public static final String STUDY_QUOTE_REGISTRATION_PERIOD_TYPE = "IQ";

	/**
	 * The semester period to use when querying for quotation dates
	 */
	public static final int STUDY_QUOTE_REGISTRATION_PERIOD_SEMESTER = 0;

	//Common proxy defaults
	public static final short PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_VERSION = 3;
	public static final short PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_REVISION = 1;
	public static final String PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_DEVELOPMENT_PHASE = "C";
	public static final String PROXY_WORKSTATION_CODE = "internet";

	// StudyQuotation fee proxy default values
	public static final String STUDY_QUOTE_FEE_PROXY_STUDENT_MK_CORRESPONDENCE_LANGUAGE = "E";
	public static final String STUDY_QUOTE_FEE_PROXY_STUDENT_SURNAME = "A";
	public static final String STUDY_QUOTE_FEE_PROXY_STUDENT_INITIALS = "A";
	public static final int STUDY_QUOTE_FEE_PROXY_ANNUAL_RECORD_MK_STUDENT_NBR = 0;
	public static final String STUDY_QUOTE_FEE_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION = "P";
	public static final short STUDY_QUOTE_FEE_PROXY_ADDRESS_POSTAL_CODE = 1;
	public static final int STUDY_QUOTE_FEE_PROXY_USER_NUMBER = 9999;

	// Parcel Tracking proxy defaults
	public static final String PARCEL_TRACKING_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION = "D";
	public static final String PARCEL_TRACKING_PROXY_SECURITY_PRINTER_CODE = "MYUNISA";

	// CreditCard Payment proxy defaults.
	public static final String CREDIT_CARD_PAYMENT_STUDENT_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION = "DS";
	public static final String CREDIT_CARD_PAYMENT_QUAL_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION = "GD";
	public static final String CREDIT_CARD_PAYMENT_PAYMENT_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION = "A";
	public static final int CREDIT_CARD_PAYMENT_PROXY_USER_NUMBER = 99998;


}
