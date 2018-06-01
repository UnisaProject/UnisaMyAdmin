package za.ac.unisa.myadmin.service.parceltracking.impl;

import Smsij01h.Abean.Smsij01sMntGenDespatchInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.parceltracking.ParcelTrackingInfo;
import za.ac.unisa.myadmin.parceltracking.ParcelTrackingService;
import za.ac.unisa.myadmin.parceltracking.StudentInfo;
import za.ac.unisa.myadmin.parceltracking.TrackAndTraceRecordInfo;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Adrian on 2018-05-30.
 */
@Service("ParcelTrackingService")
public class ParcelTrackingServiceImpl implements ParcelTrackingService {
	/**
	 * Get an instance of the StudyFee proxy
	 *
	 * @return
	 * @throws PropertyVetoException
	 */
	private Smsij01sMntGenDespatchInfo getProxyInstance() throws PropertyVetoException {
		Smsij01sMntGenDespatchInfo despatchProxy = new Smsij01sMntGenDespatchInfo();
		despatchProxy.clear();
		despatchProxy.setInCsfClientServerCommunicationsClientVersionNumber((short) 3);
		despatchProxy.setInCsfClientServerCommunicationsClientRevisionNumber((short) 1);
		despatchProxy.setInCsfClientServerCommunicationsAction("D");
		despatchProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase("C");
		despatchProxy.setInSecurityWsPrinterCode("MYUNISA");
		return despatchProxy;
	}

	@Override
	public ParcelTrackingInfo trackAndTraceParcel(Integer studentNumber) throws OperationFailedException {
		try {
			Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
			//LocalDate.now().get(ChronoField.YEAR);

			Smsij01sMntGenDespatchInfo despatchProxy = getProxyInstance();
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference.set(new OperationFailedException(e.getActionCommand()));
			despatchProxy.addExceptionListener(exceptionListener);
			//Populate fields
			despatchProxy.setInStudentAnnualRecordMkStudentNr(studentNumber);
			despatchProxy.setInStudentAnnualRecordMkAcademicYear(currentYear.shortValue());

			despatchProxy.execute();
			if (exceptionReference.get() != null) {
				throw exceptionReference.get();
			}
			return buildResponse(despatchProxy, currentYear);
		} catch (PropertyVetoException ex) {
			throw new OperationFailedException(ex);
		}
	}

	private ParcelTrackingInfo buildResponse(Smsij01sMntGenDespatchInfo despatchProxy, Integer currentYear) throws OperationFailedException {
		ParcelTrackingInfo parcelTrackingInfo = new ParcelTrackingInfo();
		String errorMessage = despatchProxy.getOutCsfStringsString500();
		if (StringUtils.hasText(errorMessage)) {
			throw new OperationFailedException(errorMessage);
		}
		StudentInfo student = new StudentInfo();
		student.setStudentNumber(String.valueOf(despatchProxy.getOutStudentAnnualRecordMkStudentNr()));
		student.setStudentName((String.valueOf(despatchProxy.getOutWsStudentMkTitle()) + " " + String.valueOf(despatchProxy.getOutWsStudentFirstNames() + " " + String.valueOf(despatchProxy.getOutWsStudentSurname()))));
		parcelTrackingInfo.setStudentInfo(student);

		List records = new ArrayList<>();

		for (int i = 0; i < despatchProxy.getOutLuGroupCount(); i++) {

			if (!("".equals(despatchProxy.getOutGWsTrackAndTraceNumber(i)))) {
				//DateFormat strDate = new SimpleDateFormat("dd-MM-yyyy");
				Calendar tmpDate = despatchProxy.getOutGWsTrackAndTraceDate(i);

				int day = tmpDate.get(Calendar.DAY_OF_MONTH);

				int month = tmpDate.get(Calendar.MONTH) + 1;

				String tempmonth;//converting month to string to change the month format
				String tempday;  //converting day to string to convert the day format.

				if (month < 10) {
					tempmonth = "0".concat(Integer.toString(month));

				} else {
					tempmonth = Integer.toString(month);

				}
				if (day < 10) {

					tempday = "0".concat(Integer.toString(day));

				} else {
					tempday = Integer.toString(day);
				}

				int year = tmpDate.get(Calendar.YEAR);
				if (currentYear == year || ((currentYear - 1) == year && month > 10)) {
					//set date in array list
					TrackAndTraceRecordInfo trackAndTraceRecord = new TrackAndTraceRecordInfo();
					if (despatchProxy.getOutGWsTrackAndTraceNumber(i).substring(0, 1).equals("C") ||
						despatchProxy.getOutGWsTrackAndTraceNumber(i).substring(0, 2).equals("RA") ||
						despatchProxy.getOutGWsTrackAndTraceNumber(i).equalsIgnoreCase("PARCEL POSTED") ||
						isInteger(despatchProxy.getOutGWsTrackAndTraceNumber(i).substring(0, 1))) {
						trackAndTraceRecord.setTrackTraceAgent("SAPO Safemail");
						trackAndTraceRecord.setTrackTraceNumber(despatchProxy.getOutGWsTrackAndTraceNumber(i));
						if (!despatchProxy.getOutGWsTrackAndTraceNumber(i).equalsIgnoreCase("PARCEL POSTED")) {
							trackAndTraceRecord.setTrackTraceNumber(despatchProxy.getOutGWsTrackAndTraceNumber(i));
						}

					} else {
						if (!despatchProxy.getOutGWsTrackAndTraceNumber(i).substring(0, 1).equals("S")) {
							trackAndTraceRecord.setTrackTraceAgent("Track n Trace");
							trackAndTraceRecord.setTrackTraceNumber(despatchProxy.getOutGWsTrackAndTraceNumber(i));
							trackAndTraceRecord.setTrackTraceDate(year + "-" + tempmonth + "-" + tempday);
						}
					}
					records.add(trackAndTraceRecord);
				}
			}
		}
		parcelTrackingInfo.setTraceRecordInfoList(records);
		parcelTrackingInfo.setStudentuser(true);

		//TODO Unisa tool logs some event.
		//eventTrackingService.post(
		//	eventTrackingService.newEvent(EventTrackingTypes.EVENT_TRACKANDTRACE_VIEW, toolManager.getCurrentPlacement().getContext(), false));
		return parcelTrackingInfo;
	}

	private boolean isInteger(String i) {
		try {
			Integer.parseInt(i);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}