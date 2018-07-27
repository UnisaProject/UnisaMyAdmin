package za.ac.unisa.myadmin.student.services.impls;

import Srsra01h.Abean.Srsra01sLstStudentAcademRec;
import org.springframework.util.StringUtils;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.generic.dto.GenericCodeInfo;
import za.ac.unisa.myadmin.generic.services.UnisaGenericService;
import za.ac.unisa.myadmin.qualification.services.StudentAcademicRecordService;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class StudentAcademicRecordServiceImpl implements StudentAcademicRecordService {

	//TODO Replace with suitable authorization lookup possibly CAS info.
	private boolean isStudentUser = true;

	private UnisaGenericService genericService;

//	private GenericCodeRepository genericCodeRepository;

	public void setGenericService(UnisaGenericService genericService) {
		this.genericService = genericService;
	}

//	public void setGenericCodeRepository(GenericCodeRepository genericCodeRepository) {
//		this.genericCodeRepository = genericCodeRepository;
//	}

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
			List<GenericCodeInfo> queryList = genericService.getGenericCodesByCategoryOrdered(23, "code");
			// Place results in map
			for (GenericCodeInfo entity : queryList) {
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

}
