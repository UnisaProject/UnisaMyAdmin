package za.ac.unisa.myadmin.student.services.impls;

import Srasa01h.Abean.Srasa01sLstAcademicRecordSun;
import org.springframework.util.StringUtils;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.AcademicModuleRecordService;
import za.ac.unisa.myadmin.module.services.dto.AcademicModuleRecordInfo;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Adrian on 2018-07-27.
 */
public class AcademicModuleRecordServiceImpl implements AcademicModuleRecordService {

	@Override
	public List<AcademicModuleRecordInfo> requestStudentAcademicModuleResults(Integer studentNumber, boolean isCreditsOnly, String selectedQualificationCode) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference
				.set(new OperationFailedException(e.getActionCommand()));
			final Srasa01sLstAcademicRecordSun academicStudyUnitRecordProxy = new Srasa01sLstAcademicRecordSun();
			academicStudyUnitRecordProxy.addExceptionListener(exceptionListener);
			academicStudyUnitRecordProxy.clear();
			academicStudyUnitRecordProxy.setInWsWorkstationCode("internet");
			academicStudyUnitRecordProxy.setInWsFunctionNumber(135);
			academicStudyUnitRecordProxy.setInCsfClientServerCommunicationsClientVersionNumber((short) 3);
			academicStudyUnitRecordProxy.setInCsfClientServerCommunicationsClientRevisionNumber((short) 1);
			academicStudyUnitRecordProxy.setInCsfClientServerCommunicationsAction("D");
			academicStudyUnitRecordProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase("C");
			academicStudyUnitRecordProxy.setInCreditsOnlyIefSuppliedFlag(isCreditsOnly ? "Y" : "N");
			academicStudyUnitRecordProxy.setInStudentAcademicRecordMkQualificationCode(selectedQualificationCode);
			academicStudyUnitRecordProxy.setInStudentAcademicRecordMkStudentNr(studentNumber);
			academicStudyUnitRecordProxy.execute();
			if (exceptionReference.get() != null) {
				throw exceptionReference.get();
			}
			if (academicStudyUnitRecordProxy.getExitStateType() < 3) {
				throw new OperationFailedException(academicStudyUnitRecordProxy.getExitStateMsg());
			}
			/*
			 * Add error messages from proxy
	         */
			String errorMsg = academicStudyUnitRecordProxy.getOutCsfStringsString500();
			if (StringUtils.hasText(errorMsg)) {
				throw new OperationFailedException(errorMsg);
			}

			List<AcademicModuleRecordInfo> records = new ArrayList<>();

			buildModules(records, academicStudyUnitRecordProxy);

	       /*
		   * If page down = Y read proxy again until list is completed
	       */
			String pageDown = academicStudyUnitRecordProxy.getOutCsfClientServerCommunicationsRgvScrollDownFlag();
			while ("Y".equalsIgnoreCase(pageDown)) {
				// put this code in because for some reason the program executes too quickly
				// and coolgen doesn't return the correct data
				try {
					Thread.sleep(Long.parseLong("1000"));
				} catch (Exception e) {

				}
				final Srasa01sLstAcademicRecordSun proxyLoop = new Srasa01sLstAcademicRecordSun();
				final AtomicReference<OperationFailedException> exceptionReferenceLoop = new AtomicReference<>();
				final ActionListener exceptionListenerLoop = e -> exceptionReferenceLoop
					.set(new OperationFailedException(e.getActionCommand()));
				proxyLoop.addExceptionListener(exceptionListenerLoop);
				proxyLoop.clear();
				proxyLoop.setInWsWorkstationCode("internet");
				proxyLoop.setInWsFunctionNumber(135);
				proxyLoop.setInCsfClientServerCommunicationsClientVersionNumber((short) 3);
				proxyLoop.setInCsfClientServerCommunicationsClientRevisionNumber((short) 1);
				proxyLoop.setInCsfClientServerCommunicationsAction("PD");
				proxyLoop.setInCsfClientServerCommunicationsClientDevelopmentPhase("C");
				proxyLoop.setInCreditsOnlyIefSuppliedFlag(isCreditsOnly ? "Y" : "N");
				proxyLoop.setInStudentAcademicRecordMkQualificationCode(selectedQualificationCode);
				proxyLoop.setInStudentAcademicRecordMkStudentNr(studentNumber);
				proxyLoop.execute();

				if (exceptionReferenceLoop.get() != null) {
					throw exceptionReferenceLoop.get();
				}
				if (proxyLoop.getExitStateType() < 3) {
					throw new OperationFailedException(proxyLoop.getExitStateMsg());
				}

		        /*
				 * Add error messages from proxy
		         */
				errorMsg = proxyLoop.getOutCsfStringsString500();
				if (StringUtils.hasText(errorMsg)) {
					throw new OperationFailedException(errorMsg);
				}
				buildModules(records, proxyLoop);
				pageDown = proxyLoop.getOutCsfClientServerCommunicationsRgvScrollDownFlag();
			}
			//TODO This system logged events in sakai.
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

	private void buildModules(List<AcademicModuleRecordInfo> records, Srasa01sLstAcademicRecordSun opList) {
		for (int i = 0; i <= (opList.getOutGroupCount() - 1); i++) {
			AcademicModuleRecordInfo studyUnitRecord = new AcademicModuleRecordInfo();
			studyUnitRecord.setStudentNumber(opList.getOutStudentAcademicRecordMkStudentNr());
			studyUnitRecord.setQualificationCode(opList.getOutStudentAcademicRecordMkQualificationCode());
			studyUnitRecord.setAcademicYear(Integer.valueOf(opList.getOutGAcademicRecordStudyUnitMkAcademicYear(i)));
			studyUnitRecord.setAcademicPeriod(Integer.valueOf(opList.getOutGAcademicRecordStudyUnitMkAcademicPeriod(i)));
			if (opList.getOutGAcademicRecordStudyUnitResultDate(i) != null) {
				studyUnitRecord.setExamDate(opList.getOutGAcademicRecordStudyUnitResultDate(i).getTime());
				studyUnitRecord.setResultDate(opList.getOutGAcademicRecordStudyUnitResultDate(i).getTime());
			}
			studyUnitRecord.setStudyUnitCode(opList.getOutGAcademicRecordStudyUnitMkStudyUnitCode(i));
			studyUnitRecord.setStudyUnitDescription(opList.getOutGWsStudyUnitEngShortDescription(i));
			if (opList.getOutGAcademicRecordStudyUnitFinalMark(i) == 0) {
				studyUnitRecord.setMark("");
			} else {
				studyUnitRecord.setMark(Short.toString(opList.getOutGAcademicRecordStudyUnitFinalMark(i)));
			}
			studyUnitRecord.setResultTypeDescription(opList.getOutGStudyUnitResultTypeEngDescription(i));
			records.add(studyUnitRecord);
		}
	}
}
