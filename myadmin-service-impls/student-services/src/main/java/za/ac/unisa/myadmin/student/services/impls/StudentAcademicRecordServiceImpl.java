package za.ac.unisa.myadmin.student.services.impls;

import Srasa01h.Abean.Srasa01sLstAcademicRecordSun;
import Srsra01h.Abean.Srsra01sLstStudentAcademRec;
import org.springframework.util.StringUtils;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.dto.StudentAcademicStudyUnitResultInfo;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;
import za.ac.unisa.myadmin.student.services.StudentAcademicRecordService;
import za.ac.unisa.myadmin.student.services.jpa.models.GenericCodeEntity;
import za.ac.unisa.myadmin.student.services.repositories.GenericCodeRepository;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class StudentAcademicRecordServiceImpl implements StudentAcademicRecordService {

	//TODO Replace with suitable authorization lookup possibly CAS info.
	private boolean isStudentUser = true;

	private GenericCodeRepository genericCodeRepository;

	public void setGenericCodeRepository(GenericCodeRepository genericCodeRepository) {
		this.genericCodeRepository = genericCodeRepository;
	}

	@Override
	public List<StudentAcademicQualificationRecordInfo> requestStudentAcademicQualificationResults(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		try {
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference
				.set(new OperationFailedException(e.getActionCommand()));

			final Srsra01sLstStudentAcademRec studentAcademicRecordProxy = new Srsra01sLstStudentAcademRec();
			studentAcademicRecordProxy.addExceptionListener(exceptionListener);
			studentAcademicRecordProxy.clear();

	        /* op.setTracing(Trace.MASK_ALL); */
			// op.setInIpAddressCsfStringsString15(request.getRemoteAddr());
			studentAcademicRecordProxy.setInWsWorkstationCode("internet");
			studentAcademicRecordProxy.setInWsFunctionNumber(135);
			studentAcademicRecordProxy.setInCsfClientServerCommunicationsClientVersionNumber((short) 3);
			studentAcademicRecordProxy.setInCsfClientServerCommunicationsClientRevisionNumber((short) 1);
			studentAcademicRecordProxy.setInCsfClientServerCommunicationsAction("D");
			studentAcademicRecordProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase("C");
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

			List<StudentAcademicQualificationRecordInfo> records = new ArrayList<StudentAcademicQualificationRecordInfo>();
			//HashMap list = new HashMap();
			// Get result type list from general code table
			HashMap<String, String> codeMap = new HashMap<String, String>();
			List<GenericCodeEntity> queryList = genericCodeRepository.findByGenericCategoryCodeOrderByCode(23);
			// Place results in map
			for (GenericCodeEntity entity : queryList) {
				codeMap.put(entity.getCode(), entity.getEnglishDescription());
			}
			for (int i = 0; i < count; i++) {
				StudentAcademicQualificationRecordInfo qualRecord = new StudentAcademicQualificationRecordInfo();
				qualRecord.setQualificationCode(studentAcademicRecordProxy.getOutGStudentAcademicRecordMkQualificationCode(i));
				qualRecord.setQualShortDescription(studentAcademicRecordProxy.getOutGWsQualificationShortDescription(i));
				//DateFormat strDate = new SimpleDateFormat("yyyy");
				if (studentAcademicRecordProxy.getOutGStudentAcademicRecordFirstRegistrationDate(i) != null) {
					qualRecord.setFirstRegistrationDate(studentAcademicRecordProxy.getOutGStudentAcademicRecordFirstRegistrationDate(i).getTime());
				} else {
					qualRecord.setFirstRegistrationDate(null);
				}
				qualRecord.setLastRegistrationYear(Integer.valueOf(studentAcademicRecordProxy.getOutGStudentAcademicRecordLastAcademicRegistrationYear(i)));
				qualRecord.setStatus(codeMap.get(studentAcademicRecordProxy.getOutGStudentAcademicRecordStatusCode(i)).toString());
				records.add(qualRecord);
			}
			if (records.isEmpty()) {
				throw new OperationFailedException("Academic record list is empty.");
			}
			//TODO This system logged events in sakai.
			return records;
		} catch (PropertyVetoException e) {
			throw new OperationFailedException("A technical error has happened,Please enter your details again.If this problem persists please log off myUnisa and Log On again");
		}
//        /* Write event log: Get user details, if not empty write event log with usagesession */
//			sessionManager = (SessionManager) ComponentManager.get(SessionManager.class);
//			userDirectoryService = (UserDirectoryService) ComponentManager.get(UserDirectoryService.class);
//			String currentUserID = "";
//			Session currentSession = sessionManager.getCurrentSession();
//			String userID = currentSession.getUserId();
//			eventTrackingService = (EventTrackingService) ComponentManager.get(EventTrackingService.class);
//			toolManager = (ToolManager) ComponentManager.get(ToolManager.class);
//			if (userID != null) {
//				currentUserID = currentSession.getUserEid();
//				usageSessionService = (UsageSessionService) ComponentManager.get(UsageSessionService.class);
//				UsageSession usageSession = usageSessionService.getSession();
//				//UsageSessionService.startSession(currentUserID,request.getRemoteAddr(),request.getHeader("user-agent"));
//				eventTrackingService.post(
//					eventTrackingService.newEvent(EventTrackingTypes.EVENT_ACADHISTORY_VIEW, toolManager.getCurrentPlacement().getContext(), false), usageSession);
//			} else {
//				eventTrackingService.post(
//					eventTrackingService.newEvent(EventTrackingTypes.EVENT_ACADHISTORY_VIEW, toolManager.getCurrentPlacement().getContext(), false));
//			}
//			return mapping.findForward("displayAcadHistory");

	}

	@Override
	public List<StudentAcademicStudyUnitResultInfo> requestStudentAcademicModuleResults(Integer studentNumber, boolean isCreditsOnly, String selectedQualificationCode) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference
				.set(new OperationFailedException(e.getActionCommand()));

			Srasa01sLstAcademicRecordSun academicStudyUnitRecordProxy = new Srasa01sLstAcademicRecordSun();
			academicStudyUnitRecordProxy.addExceptionListener(exceptionListener);
			academicStudyUnitRecordProxy.clear();
			academicStudyUnitRecordProxy.setInWsWorkstationCode("internet");
			academicStudyUnitRecordProxy.setInWsFunctionNumber(135);
			academicStudyUnitRecordProxy.setInCsfClientServerCommunicationsClientVersionNumber((short) 3);
			academicStudyUnitRecordProxy.setInCsfClientServerCommunicationsClientRevisionNumber((short) 1);
			academicStudyUnitRecordProxy.setInCsfClientServerCommunicationsAction("D");
			academicStudyUnitRecordProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase("C");
			academicStudyUnitRecordProxy.setInCreditsOnlyIefSuppliedFlag(isCreditsOnly ? "Y" : "N");

			if (!StringUtils.hasText(selectedQualificationCode)) {
				throw new MissingParameterException();
			}
			academicStudyUnitRecordProxy.setInStudentAcademicRecordMkQualificationCode(selectedQualificationCode);
			if (studentNumber == 0) {
				throw new MissingParameterException();
			}
			academicStudyUnitRecordProxy.setInStudentAcademicRecordMkStudentNr(studentNumber);
			academicStudyUnitRecordProxy.execute();
			if (exceptionReference.get() != null) {
				throw exceptionReference.get();
			}
			if (academicStudyUnitRecordProxy.getExitStateType() < 3) {
				throw new OperationFailedException(academicStudyUnitRecordProxy.getExitStateMsg());
			}
			int count = academicStudyUnitRecordProxy.getOutGroupCount();

	        /*
			 * Add error messages from proxy
	         */
			String errorMsg = academicStudyUnitRecordProxy.getOutCsfStringsString500();
			if (StringUtils.hasText(errorMsg)) {
				throw new OperationFailedException(errorMsg);
			}

			List<StudentAcademicStudyUnitResultInfo> records = new ArrayList<>();

			buildModules(count, records, academicStudyUnitRecordProxy);

	       /*
		   * If page down = Y read proxy again until list is completed
	       */
			String pageDown = academicStudyUnitRecordProxy.getOutCsfClientServerCommunicationsRgvScrollDownFlag();
			while ("Y".equalsIgnoreCase(pageDown)) {
				// put this code in because for some reason the program executes too quickly
				// and coolgen doesn't return the correct data
//				try {
//					Thread.sleep(Long.parseLong("1000"));
//				} catch (Exception e) {
//
//				}
				Srasa01sLstAcademicRecordSun opList = new Srasa01sLstAcademicRecordSun();
				final AtomicReference<OperationFailedException> exceptionReference1 = new AtomicReference<>();
				final ActionListener exceptionListener1 = e -> exceptionReference
					.set(new OperationFailedException(e.getActionCommand()));
				opList.addExceptionListener(exceptionListener1);
				opList.clear();
				opList.setInWsWorkstationCode("internet");
				opList.setInWsFunctionNumber(135);
				opList.setInCsfClientServerCommunicationsClientVersionNumber((short) 3);
				opList.setInCsfClientServerCommunicationsClientRevisionNumber((short) 1);
				opList.setInCsfClientServerCommunicationsAction("PD");
				opList.setInCsfClientServerCommunicationsClientDevelopmentPhase("C");
				opList.setInCreditsOnlyIefSuppliedFlag(isCreditsOnly ? "Y" : "N");
				opList.setInStudentAcademicRecordMkQualificationCode(selectedQualificationCode);
				opList.setInStudentAcademicRecordMkStudentNr(studentNumber);
				opList.execute();

				if (exceptionReference1.get() != null) {
					throw exceptionReference1.get();
				}
				if (opList.getExitStateType() < 3) {
					throw new OperationFailedException(opList.getExitStateMsg());
				}
				count = opList.getOutGroupCount();

		        /*
		         * Add error messages from proxy
		         */
				errorMsg = opList.getOutCsfStringsString500();
				if (StringUtils.hasText(errorMsg)) {
					throw new OperationFailedException(errorMsg);
				}
				buildModules(count, records, opList);
				pageDown = opList.getOutCsfClientServerCommunicationsRgvScrollDownFlag();
			}
			return records;
        /* Write event log: Get user details, if not empty write event log with usagesession */
//			String currentUserID = "";
//			sessionManager = (SessionManager) ComponentManager.get(SessionManager.class);
//			userDirectoryService = (UserDirectoryService) ComponentManager.get(UserDirectoryService.class);
//			Session currentSession = sessionManager.getCurrentSession();
//			String userID = currentSession.getUserId();
//			eventTrackingService = (EventTrackingService) ComponentManager.get(EventTrackingService.class);
//			toolManager = (ToolManager) ComponentManager.get(ToolManager.class);
//			if (userID != null) {
//				currentUserID = currentSession.getUserEid();
//				usageSessionService = (UsageSessionService) ComponentManager.get(UsageSessionService.class);
//				UsageSession usageSession = usageSessionService.getSession();
//				//UsageSessionService.startSession(currentUserID,request.getRemoteAddr(),request.getHeader("user-agent"));
//				eventTrackingService.post(
//					eventTrackingService.newEvent(EventTrackingTypes.EVENT_ACADHISTORY_VIEW, toolManager.getCurrentPlacement().getContext(), false), usageSession);
//			} else {
//				eventTrackingService.post(
//					eventTrackingService.newEvent(EventTrackingTypes.EVENT_ACADHISTORY_VIEW, toolManager.getCurrentPlacement().getContext(), false));
//
//			}

		} catch (PropertyVetoException ex) {
			throw new OperationFailedException("A technical error has happened,Please enter your details again.If this problem persists please log off myUnisa and Log On again");
		}
	}

	private void buildModules(int count, List<StudentAcademicStudyUnitResultInfo> records, Srasa01sLstAcademicRecordSun opList) {
		for (int i = 0; i < count; i++) {
			StudentAcademicStudyUnitResultInfo studyUnitRecord = new StudentAcademicStudyUnitResultInfo();
			if (opList.getOutGAcademicRecordStudyUnitResultDate(i) != null) {
				studyUnitRecord.setExamDate(opList.getOutGAcademicRecordStudyUnitResultDate(i).getTime());
			}
			studyUnitRecord.setStudyUnit(opList.getOutGAcademicRecordStudyUnitMkStudyUnitCode(i));
			studyUnitRecord.setStudyUnitDescription(opList.getOutGWsStudyUnitEngShortDescription(i));
			if (opList.getOutGAcademicRecordStudyUnitFinalMark(i) == 0) {
				studyUnitRecord.setMark("");
			} else {
				studyUnitRecord.setMark(Short.toString(opList.getOutGAcademicRecordStudyUnitFinalMark(i)));
			}
			studyUnitRecord.setResultTypeDescr(opList.getOutGStudyUnitResultTypeEngDescription(i));
			records.add(studyUnitRecord);
		}
	}
}
