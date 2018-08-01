package za.ac.unisa.myadmin.student.services.impls;

import Srsra01h.Abean.Srsra01sLstStudentAcademRec;
import org.springframework.util.StringUtils;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.common.services.CommonServicesConstants;
import za.ac.unisa.myadmin.contact.services.ContactService;
import za.ac.unisa.myadmin.contact.services.dto.ContactInfo;
import za.ac.unisa.myadmin.generic.dto.EmailLogInfo;
import za.ac.unisa.myadmin.generic.dto.GenericCodeInfo;
import za.ac.unisa.myadmin.generic.services.EmailLogService;
import za.ac.unisa.myadmin.generic.services.UnisaGenericService;
import za.ac.unisa.myadmin.qualification.services.StudentAcademicRecordService;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;
import za.ac.unisa.myadmin.student.services.jpa.models.StudentAcademicRecordEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.StudentAcademicRecordEntityId;
import za.ac.unisa.myadmin.student.services.repositories.StudentAcademicRecordRepository;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class StudentAcademicRecordServiceImpl implements StudentAcademicRecordService {

	public static final int ADDRESS_CAT_UNISA_STUDENT = 1;

	private UnisaGenericService genericService;

	private EmailLogService emailLogService;

	private ContactService contactService;

	private StudentAcademicRecordRepository academicRecordRepository;

	public void setGenericService(UnisaGenericService genericService) {
		this.genericService = genericService;
	}

	public void setEmailLogService(EmailLogService emailLogService) {
		this.emailLogService = emailLogService;
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
			HashMap<String, String> codeMap = new HashMap<>();
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference
				.set(new OperationFailedException(e.getActionCommand()));

			final Srsra01sLstStudentAcademRec studentAcademicRecordProxy = new Srsra01sLstStudentAcademRec();
			studentAcademicRecordProxy.addExceptionListener(exceptionListener);
			studentAcademicRecordProxy.clear();

			studentAcademicRecordProxy.setInWsWorkstationCode(CommonServicesConstants.PROXY_WORKSTATION_CODE);
			studentAcademicRecordProxy.setInWsFunctionNumber(135);
			studentAcademicRecordProxy.setInCsfClientServerCommunicationsClientVersionNumber(CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_VERSION);
			studentAcademicRecordProxy.setInCsfClientServerCommunicationsClientRevisionNumber(CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_REVISION);
			studentAcademicRecordProxy.setInCsfClientServerCommunicationsAction("D");
			studentAcademicRecordProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase(CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_DEVELOPMENT_PHASE);
			studentAcademicRecordProxy.setInWsStudentNr(studentNumber);
			studentAcademicRecordProxy.execute();
			if (exceptionReference.get() != null) throw new OperationFailedException(exceptionReference.get());
			if (studentAcademicRecordProxy.getExitStateType() < 3)
				throw new OperationFailedException(studentAcademicRecordProxy.getExitStateMsg());
			int count = studentAcademicRecordProxy.getOutGroupCount();

			String errorMsg = studentAcademicRecordProxy.getOutCsfStringsString500();
			if (StringUtils.hasText(errorMsg)) {
				throw new OperationFailedException(errorMsg);
			}
			// Do we really need to query a db for these??
			List<GenericCodeInfo> queryList = genericService.getGenericCodesByCategoryOrdered(23, "code");
			// Place codes in map
			for (GenericCodeInfo entity : queryList) {
				codeMap.put(entity.getCode(), entity.getEnglishDescription());
			}
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
	public List<StudentAcademicQualificationRecordInfo> requestStudentAcademicRecordEmail(Integer studentNumber, String academicQualificationCode, boolean isAttachMarks) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		String recipient, program, emailType, reqSystem, regProgram, emailBody;
		recipient = String.valueOf(studentNumber);
		program = "SRSRJ11H";
		reqSystem = "MYUNISA";
		regProgram = "ACADEMIC RECORD";
		emailBody = academicQualificationCode;
		//
		emailType = "SUBJDECLNM";
		if (isAttachMarks) {
			emailType = "SUBJDECLWM";
		}
		EmailLogInfo emailLogInfo = emailLogService.getLastestEmailRequestForAcademicRecord(recipient, program, emailType, reqSystem, regProgram, emailBody);
		if (emailLogInfo != null) {
		//Allowed to email per hour. Check some business rules around time.

		}

		//get email
		ContactInfo studentContactInfo = contactService.getContactInfoForReferenceAndType(studentNumber, ADDRESS_CAT_UNISA_STUDENT);
		//Call java proxy to send


		return null;
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

}
