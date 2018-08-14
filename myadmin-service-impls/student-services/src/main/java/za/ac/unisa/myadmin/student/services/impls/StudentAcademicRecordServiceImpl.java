package za.ac.unisa.myadmin.student.services.impls;

import Srsra01h.Abean.Srsra01sLstStudentAcademRec;
import Srsrj11h.Abean.Srsrj11sPrtNormalDeclaration;
import org.apache.commons.lang3.StringUtils;
import za.ac.unisa.myadmin.common.dto.ErrorInfo;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.common.services.CommonServicesConstants;
import za.ac.unisa.myadmin.contact.services.ContactService;
import za.ac.unisa.myadmin.contact.services.dto.ContactInfo;
import za.ac.unisa.myadmin.generic.dto.GenericCodeInfo;
import za.ac.unisa.myadmin.generic.dto.GenericMessageInfo;
import za.ac.unisa.myadmin.generic.services.GenericServicesConstants;
import za.ac.unisa.myadmin.generic.services.GenericService;
import za.ac.unisa.myadmin.qualification.services.StudentAcademicRecordService;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;
import za.ac.unisa.myadmin.student.services.StudentServicesConstants;
import za.ac.unisa.myadmin.student.services.jpa.models.StudentAcademicRecordEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.StudentAcademicRecordEntityId;
import za.ac.unisa.myadmin.student.services.repositories.StudentAcademicRecordRepository;

import javax.ws.rs.core.Response;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class StudentAcademicRecordServiceImpl implements StudentAcademicRecordService {

	private boolean testEmailFlag = false;

	private String testEmailAddress;

	private GenericService genericService;

	private ContactService contactService;

	private StudentAcademicRecordRepository academicRecordRepository;

	public void setTestEmailFlag(boolean testEmailFlag) {
		this.testEmailFlag = testEmailFlag;
	}

	public void setTestEmailAddress(String testEmailAddress) {
		this.testEmailAddress = testEmailAddress;
	}

	public void setGenericService(GenericService genericService) {
		this.genericService = genericService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	public void setAcademicRecordRepository(StudentAcademicRecordRepository academicRecordRepository) {
		this.academicRecordRepository = academicRecordRepository;
	}

	@Override
	public List<StudentAcademicQualificationRecordInfo> requestStudentAcademicQualificationResults(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		try {
			List<StudentAcademicQualificationRecordInfo> returnResults = new ArrayList<>();
			// Get result type list from general code table
			Map<String, String> codeMap;
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference
				.set(new OperationFailedException(e.getActionCommand()));

			final Srsra01sLstStudentAcademRec studentAcademicRecordProxy = new Srsra01sLstStudentAcademRec();
			studentAcademicRecordProxy.addExceptionListener(exceptionListener);
			studentAcademicRecordProxy.clear();

			studentAcademicRecordProxy.setInWsWorkstationCode(CommonServicesConstants.PROXY_WORKSTATION_CODE);
			studentAcademicRecordProxy.setInWsFunctionNumber(StudentServicesConstants.ACADEMIC_RECORD_PROXY_FUNCTION_NUMBER);
			studentAcademicRecordProxy.setInCsfClientServerCommunicationsClientVersionNumber(CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_VERSION);
			studentAcademicRecordProxy.setInCsfClientServerCommunicationsClientRevisionNumber(CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_REVISION);
			studentAcademicRecordProxy.setInCsfClientServerCommunicationsAction(StudentServicesConstants.ACADEMIC_RECORD_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION);
			studentAcademicRecordProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase(CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_DEVELOPMENT_PHASE);
			studentAcademicRecordProxy.setInWsStudentNr(studentNumber);
			studentAcademicRecordProxy.execute();
			if (exceptionReference.get() != null) throw new OperationFailedException(exceptionReference.get());
			if (studentAcademicRecordProxy.getExitStateType() < 3)
				throw new OperationFailedException(studentAcademicRecordProxy.getExitStateMsg());
			int count = studentAcademicRecordProxy.getOutGroupCount();

			String errorMsg = studentAcademicRecordProxy.getOutCsfStringsString500();
			if (StringUtils.isNotBlank(errorMsg)) {
				throw new OperationFailedException(errorMsg);
			}
			List<GenericCodeInfo> genericCodeList = genericService.getGenericCodesByCategoryOrdered(GenericServicesConstants.UNISA_GENERIC_CODE_QUALIFICATION_STATUS, GenericServicesConstants.UNISA_GENERIC_CODE_ORDERBY_CODE);
			// Place codes in map
			codeMap = genericCodeList.stream().collect(
				Collectors.toMap(GenericCodeInfo::getCode, GenericCodeInfo::getEnglishDescription));

			for (int i = 0; i < count; i++) {
				StudentAcademicQualificationRecordInfo qualRecord = new StudentAcademicQualificationRecordInfo();
				qualRecord.setQualificationCode(studentAcademicRecordProxy.getOutGStudentAcademicRecordMkQualificationCode(i));
				qualRecord.setQualShortDescription(studentAcademicRecordProxy.getOutGWsQualificationShortDescription(i));
				if (studentAcademicRecordProxy.getOutGStudentAcademicRecordFirstRegistrationDate(i) != null) {
					qualRecord.setFirstRegistrationDate(studentAcademicRecordProxy.getOutGStudentAcademicRecordFirstRegistrationDate(i).getTime());
				} else {
					qualRecord.setFirstRegistrationDate(null);
				}
				qualRecord.setLastRegistrationYear(Integer.valueOf(studentAcademicRecordProxy.getOutGStudentAcademicRecordLastAcademicRegistrationYear(i)));
				qualRecord.setStatus(codeMap.get(studentAcademicRecordProxy.getOutGStudentAcademicRecordStatusCode(i)).toString());
				returnResults.add(qualRecord);
			}
			//TODO This system logged events in sakai.
			return returnResults;
		} catch (PropertyVetoException e) {
			throw new OperationFailedException("A technical error has happened,Please enter your details again.If this problem persists please log off myUnisa and Log On again");
		}
	}

	@Override
	public ErrorInfo requestStudentAcademicRecordEmail(Integer studentNumber, String academicQualificationCode, boolean isAttachMarks) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		String proxyEmailAddress;
		String functionType;
		//Get student email address
		ContactInfo studentContactInfo = contactService.getContactInfoForReferenceAndType(studentNumber, StudentServicesConstants.ACADEMIC_RECORD_ADDRESS_CAT_UNISA_STUDENT);
		if (studentContactInfo == null || StringUtils.isBlank(studentContactInfo.getEmailAddress()) || !studentContactInfo.getEmailAddress().toLowerCase().contains("mylife.unisa.ac.za")) {
			GenericMessageInfo genMessageEnt = genericService.getGenericMessageById(GenericServicesConstants.UNISA_MESSAGECODE_EMAIL_NOMYLIFE, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
			if (genMessageEnt != null) {
				throw new InvalidParameterException(genMessageEnt.getMessage());
			}
		}
		proxyEmailAddress = studentContactInfo.getEmailAddress();
		//Check email type
		if (testEmailFlag) {
			if (StringUtils.isBlank(testEmailAddress)) {
				throw new OperationFailedException("Misconfigured application: Test email address property not configured.");
			}
			proxyEmailAddress = testEmailAddress;
		}
		StudentAcademicQualificationRecordInfo academicQualificationRecordInfo = this.getQualificationResultByStudentNumberAndQualCode(studentNumber, academicQualificationCode);
		if (academicQualificationRecordInfo.getGraduationCeremonyDate().toInstant().isBefore(Instant.now())) {
			//ceremony Date is in the passed use F140
			functionType = StudentServicesConstants.ACADEMIC_RECORD_FUNCTION_TYPE_NORMAL;
		} else {
			//ceremony Date is in the future with date specified F130
			functionType = StudentServicesConstants.ACADEMIC_RECORD_FUNCTION_TYPE_PRELIMINARY;
		}
		//Call java email proxy to send results
		try {
			return executeEmailAcademicRecordProxy(studentNumber, proxyEmailAddress, academicQualificationCode, isAttachMarks, functionType);
		} catch (PropertyVetoException e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<StudentAcademicQualificationRecordInfo> getQualificationResultsByStudentNumber(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		try {
			return academicRecordRepository.findByStudentNumber(studentNumber)
				.stream()
				.map(StudentAcademicRecordEntity::toDto)
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public StudentAcademicQualificationRecordInfo getQualificationResultByStudentNumberAndQualCode(Integer studentNumber, String qualCode) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		StudentAcademicRecordEntityId entityId = new StudentAcademicRecordEntityId(studentNumber, qualCode);
		Optional<StudentAcademicRecordEntity> entity = academicRecordRepository.findById(entityId);
		if (entity.isPresent()) {
			return entity.get().toDto();
		} else {
			throw new DoesNotExistException(entityId.toString());
		}
	}

	/**
	 * Proxy for sending academic record via email.
	 *
	 * @param studentNumber
	 * @param emailAddress
	 * @param academicQualificationCode
	 * @param isAttachMarks
	 * @param functionType
	 * @return
	 * @throws PropertyVetoException
	 * @throws OperationFailedException
	 */
	private ErrorInfo executeEmailAcademicRecordProxy(Integer studentNumber, String emailAddress, String academicQualificationCode, boolean isAttachMarks, String functionType) throws PropertyVetoException, OperationFailedException {
		final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
		final ActionListener exceptionListener = e -> exceptionReference
			.set(new OperationFailedException(e.getActionCommand()));

		// Get a reference to the proxy
		final Srsrj11sPrtNormalDeclaration emailProxy = constructEmailAcademicRecordProxy(studentNumber, emailAddress, academicQualificationCode, isAttachMarks, functionType);
		emailProxy.addExceptionListener(exceptionListener);
		emailProxy.execute();
		if (exceptionReference.get() != null) {
			throw exceptionReference.get();
		}
		if (emailProxy.getExitStateType() < 3) {
			throw new OperationFailedException(emailProxy.getExitStateMsg());
		}
		String errorMsg = emailProxy.getOutErrmsgCsfStringsString500();
		ErrorInfo messageInfo = new ErrorInfo(errorMsg, Response.Status.OK.getStatusCode());
		return messageInfo;
	}

	private Srsrj11sPrtNormalDeclaration constructEmailAcademicRecordProxy(Integer studentNumber, String emailAddress, String academicQualificationCode, boolean isAttachMarks, String functionType) throws PropertyVetoException {
		Srsrj11sPrtNormalDeclaration emailProxy = new Srsrj11sPrtNormalDeclaration();
		emailProxy.clear();
		emailProxy.setInWsUserNumber(StudentServicesConstants.ACADEMIC_RECORD_EMAIL_PROXY_USER_NUMBER); //Web User
		emailProxy.setInWsWorkstationCode(CommonServicesConstants.PROXY_WORKSTATION_CODE);
		emailProxy.setInWizfuncReportingControlPrinterCode(StudentServicesConstants.ACADEMIC_RECORD_EMAIL_PROXY_REPORTING_CONTROL_PRINTER_CODE);//use printer to send through system code
		emailProxy.setInActionCsfClientServerCommunicationsAction(StudentServicesConstants.ACADEMIC_RECORD_EMAIL_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION);
		emailProxy.setInWsServiceFeesReasonCode("F");
		emailProxy.setInWsProvSubjDeclarationOldDegree("N");
		emailProxy.setInWsProvSubjDeclarationCoursesFlag("N");
		emailProxy.setInPrintAcademicSupplementIefSuppliedFlag("Y");
		emailProxy.setInWsProvSubjDeclarationCoverLetter("Y");
		if (!isAttachMarks) {
			emailProxy.setInWsProvSubjDeclarationMarksFlag("N");
			emailProxy.setInNqfCreditsIefSuppliedFlag("N");
			emailProxy.setInNqfLevelIefSuppliedFlag("N");
		} else {
			emailProxy.setInWsProvSubjDeclarationMarksFlag("Y");
			emailProxy.setInNqfCreditsIefSuppliedFlag("Y");
			emailProxy.setInNqfLevelIefSuppliedFlag("Y");
		}
		emailProxy.setInStudentAnnualRecordMkStudentNr(studentNumber);
		emailProxy.setInEmailFromWsAddressEmailAddress(StudentServicesConstants.ACADEMIC_RECORD_EMAIL_PROXY_FROM_EMAIL_ADDRESS);
		emailProxy.setInFunctionTypeCsfStringsString6(functionType);
		emailProxy.setInEmailAddressWsAddressEmailAddress(emailAddress);
		emailProxy.setInSendByEmailIefSuppliedFlag("Y");
		emailProxy.setInSendSmsIefSuppliedFlag("N");
		emailProxy.setInWsQualificationCode(academicQualificationCode);
		return emailProxy;
	}

}
