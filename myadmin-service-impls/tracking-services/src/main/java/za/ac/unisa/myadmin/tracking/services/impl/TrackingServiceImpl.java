package za.ac.unisa.myadmin.tracking.services.impl;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang3.StringUtils;

import Smsij01h.Abean.Smsij01sMntGenDespatchInfo;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.common.services.CommonServicesConstants;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;
import za.ac.unisa.myadmin.tracking.services.TrackingService;
import za.ac.unisa.myadmin.tracking.services.TrackingServicesConstants;
import za.ac.unisa.myadmin.tracking.services.dto.PackageInfo;
import za.ac.unisa.myadmin.tracking.services.dto.TrackingInfo;

/**
 * Created by Adrian on 2018-05-30.
 */
public class TrackingServiceImpl implements TrackingService {

	@Override
	public PackageInfo trackPackageByStudent(Integer studentNumber) throws OperationFailedException {
		try {
			Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);

			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference
					.set(new OperationFailedException(e.getActionCommand()));

			Smsij01sMntGenDespatchInfo despatchProxy = getProxyInstance();
			despatchProxy.addExceptionListener(exceptionListener);
			// Populate fields
			despatchProxy.setInStudentAnnualRecordMkStudentNr(studentNumber);
			despatchProxy.setInStudentAnnualRecordMkAcademicYear(currentYear.shortValue());

			despatchProxy.execute();
			if (exceptionReference.get() != null) {
				throw exceptionReference.get();
			}

			return assembleTrackingResult(despatchProxy, currentYear);
		} catch (PropertyVetoException ex) {
			throw new OperationFailedException(ex);
		}
	}

	/**
	 * Get an instance of the despatch proxy
	 *
	 * @return
	 * @throws PropertyVetoException
	 */
	private Smsij01sMntGenDespatchInfo getProxyInstance() throws PropertyVetoException {
		Smsij01sMntGenDespatchInfo despatchProxy = new Smsij01sMntGenDespatchInfo();
		despatchProxy.clear();
		despatchProxy.setInCsfClientServerCommunicationsClientVersionNumber(
				CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_VERSION);
		despatchProxy.setInCsfClientServerCommunicationsClientRevisionNumber(
				CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_REVISION);
		despatchProxy.setInCsfClientServerCommunicationsAction(
				TrackingServicesConstants.TRACKING_SERVICE_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION);
		despatchProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase(
				CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_DEVELOPMENT_PHASE);
		despatchProxy
				.setInSecurityWsPrinterCode(TrackingServicesConstants.TRACKING_SERVICE_PROXY_SECURITY_PRINTER_CODE);
		return despatchProxy;
	}

	private PackageInfo assembleTrackingResult(Smsij01sMntGenDespatchInfo despatchProxy, Integer currentYear)
			throws OperationFailedException {
		PackageInfo parcelTrackingInfo = new PackageInfo();

		String errorMessage = despatchProxy.getOutCsfStringsString500();
		if (StringUtils.isNotBlank(errorMessage)) {
			throw new OperationFailedException(errorMessage);
		}
		StudentInfo student = new StudentInfo();
		student.setStudentNumber(despatchProxy.getOutStudentAnnualRecordMkStudentNr());
		student.setStudentName((String.valueOf(despatchProxy.getOutWsStudentMkTitle()) + " "
				+ String.valueOf(despatchProxy.getOutWsStudentFirstNames() + " "
						+ String.valueOf(despatchProxy.getOutWsStudentSurname()))));
		parcelTrackingInfo.setStudentInfo(student);

		List<TrackingInfo> trackings = new ArrayList<>();

		for (int i = 0; i < despatchProxy.getOutLuGroupCount(); i++) {
			if (StringUtils.isNotBlank(despatchProxy.getOutGWsTrackAndTraceNumber(i))) {
				// DateFormat strDate = new SimpleDateFormat("dd-MM-yyyy");
				Calendar tmpDate = despatchProxy.getOutGWsTrackAndTraceDate(i);
				int day = tmpDate.get(Calendar.DAY_OF_MONTH);
				int month = tmpDate.get(Calendar.MONTH) + 1;

				String tempmonth;// converting month to string to change the month format
				String tempday; // converting day to string to convert the day format.

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
					// set date in array list
					TrackingInfo trackAndTraceRecord = new TrackingInfo();
					if (despatchProxy.getOutGWsTrackAndTraceNumber(i).substring(0, 1)
							.equals(TrackingServicesConstants.TRACKING_CODE_C)
							|| despatchProxy.getOutGWsTrackAndTraceNumber(i).substring(0, 2)
									.equals(TrackingServicesConstants.TRACKING_CODE_RA)
							|| despatchProxy.getOutGWsTrackAndTraceNumber(i)
									.equalsIgnoreCase(TrackingServicesConstants.TRACKING_CODE_PARCEL_POSTED)
							|| isInteger(despatchProxy.getOutGWsTrackAndTraceNumber(i).substring(0, 1))) {
						trackAndTraceRecord.setTrackTraceAgent(TrackingServicesConstants.TRACKING_AGENT_SAPO);
						trackAndTraceRecord.setTrackTraceNumber(despatchProxy.getOutGWsTrackAndTraceNumber(i));
						if (!despatchProxy.getOutGWsTrackAndTraceNumber(i)
								.equalsIgnoreCase(TrackingServicesConstants.TRACKING_CODE_PARCEL_POSTED)) {
							trackAndTraceRecord.setTrackTraceNumber(despatchProxy.getOutGWsTrackAndTraceNumber(i));
						}

					} else {
						if (!despatchProxy.getOutGWsTrackAndTraceNumber(i).substring(0, 1)
								.equals(TrackingServicesConstants.TRACKING_CODE_S)) {
							trackAndTraceRecord.setTrackTraceAgent(TrackingServicesConstants.TRACKING_AGENT_TRACKING);
							trackAndTraceRecord.setTrackTraceNumber(despatchProxy.getOutGWsTrackAndTraceNumber(i));
							trackAndTraceRecord.setTrackTraceDate(year + "-" + tempmonth + "-" + tempday);
						}
					}
					trackings.add(trackAndTraceRecord);
				}
			}
		}
		parcelTrackingInfo.setTrackings(trackings);
		parcelTrackingInfo.setStudentuser(true);

		// TODO Unisa tool logs some event.
		// eventTrackingService.post(
		// eventTrackingService.newEvent(EventTrackingTypes.EVENT_TRACKANDTRACE_VIEW,
		// toolManager.getCurrentPlacement().getContext(), false));
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