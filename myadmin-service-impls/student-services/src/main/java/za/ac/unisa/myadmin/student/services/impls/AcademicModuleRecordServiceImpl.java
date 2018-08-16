package za.ac.unisa.myadmin.student.services.impls;

import Srasa01h.Abean.Srasa01sLstAcademicRecordSun;
import org.springframework.util.StringUtils;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.common.services.CommonServicesConstants;
import za.ac.unisa.myadmin.module.services.AcademicModuleRecordService;
import za.ac.unisa.myadmin.module.services.ModuleServicesConstants;
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
	public List<AcademicModuleRecordInfo> getAcademicModules(Integer studentNumber, boolean isCreditsOnly, String selectedQualificationCode) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			final Srasa01sLstAcademicRecordSun academicRecordModuleProxy = constructAcademicRecordModuleProxy(studentNumber, selectedQualificationCode, ModuleServicesConstants.ACADEMIC_MODULE_PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_ACTION, isCreditsOnly ? "Y" : "N");
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference
				.set(new OperationFailedException(e.getActionCommand()));
			academicRecordModuleProxy.addExceptionListener(exceptionListener);
			academicRecordModuleProxy.execute();
			if (exceptionReference.get() != null) {
				throw exceptionReference.get();
			}
			if (academicRecordModuleProxy.getExitStateType() < 3) {
				throw new OperationFailedException(academicRecordModuleProxy.getExitStateMsg());
			}
			/*
			 * Add error messages from proxy
	         */
			String errorMsg = academicRecordModuleProxy.getOutCsfStringsString500();
			if (StringUtils.hasText(errorMsg)) {
				throw new OperationFailedException(errorMsg);
			}

			List<AcademicModuleRecordInfo> records = new ArrayList<>();

			buildModules(records, academicRecordModuleProxy);

	       /*
		   * If page down = Y read proxy again until list is completed
	       */
			String pageDown = academicRecordModuleProxy.getOutCsfClientServerCommunicationsRgvScrollDownFlag();
			while ("Y".equalsIgnoreCase(pageDown)) {
				// put this code in because for some reason the program executes too quickly
				// and coolgen doesn't return the correct data
				try {
					Thread.sleep(Long.parseLong("1000"));
				} catch (Exception e) {

				}
				final Srasa01sLstAcademicRecordSun proxyLoop = constructAcademicRecordModuleProxy(studentNumber, selectedQualificationCode, ModuleServicesConstants.ACADEMIC_MODULE_PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_ACTION_PAGEDOWN, isCreditsOnly ? "Y" : "N");
				final AtomicReference<OperationFailedException> exceptionReferenceLoop = new AtomicReference<>();
				final ActionListener exceptionListenerLoop = e -> exceptionReferenceLoop
					.set(new OperationFailedException(e.getActionCommand()));
				proxyLoop.addExceptionListener(exceptionListenerLoop);
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
		} catch (PropertyVetoException ex) {
			throw new OperationFailedException("A technical error has happened,Please enter your details again.If this problem persists please log off myUnisa and Log On again");
		}
	}

	private Srasa01sLstAcademicRecordSun constructAcademicRecordModuleProxy(Integer studentNumber, String selectedQualificationCode, String communicationAction, String isCreditsOnlyFlag) throws PropertyVetoException {
		Srasa01sLstAcademicRecordSun academicRecordModuleProxy = new Srasa01sLstAcademicRecordSun();
		academicRecordModuleProxy.clear();
		academicRecordModuleProxy.setInWsWorkstationCode(CommonServicesConstants.PROXY_WORKSTATION_CODE);
		academicRecordModuleProxy.setInWsFunctionNumber(ModuleServicesConstants.ACADEMIC_MODULE_PROXY_IN_FUNCTION_NUMBER);
		academicRecordModuleProxy.setInCsfClientServerCommunicationsClientVersionNumber(CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_VERSION);
		academicRecordModuleProxy.setInCsfClientServerCommunicationsClientRevisionNumber(CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_REVISION);
		academicRecordModuleProxy.setInCsfClientServerCommunicationsAction(communicationAction);
		academicRecordModuleProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase(CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_DEVELOPMENT_PHASE);
		academicRecordModuleProxy.setInCreditsOnlyIefSuppliedFlag(isCreditsOnlyFlag);
		academicRecordModuleProxy.setInStudentAcademicRecordMkQualificationCode(selectedQualificationCode);
		academicRecordModuleProxy.setInStudentAcademicRecordMkStudentNr(studentNumber);
		return academicRecordModuleProxy;
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
