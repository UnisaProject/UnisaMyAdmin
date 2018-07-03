package za.ac.unisa.myadmin.student.services.impls;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import za.ac.unisa.myadmin.student.services.dto.ModuleInfoRequest;
import za.ac.unisa.myadmin.student.services.dto.ResourceDTO;
import za.ac.unisa.myadmin.student.services.dto.StudyMaterialDetailInfo;
import za.ac.unisa.myadmin.student.services.dto.StudyMaterialResponse;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

//import test.jaxrs2.StudyMaterialCodesConverter;
@Service("SCMClientRestService")
public class SCMClientRestServiceImpl {

	private static Log logger = LogFactory.getLog(SCMClientRestServiceImpl.class);
	//private EmailService emailService;

	public List<StudyMaterialDetailInfo> getStudyMaterialList(String course, Integer academicYear, String semester) throws Exception {
		final String uri = "http://appsdev.int.unisa.ac.za/sharedservices/courseMaterial";
		if (semester.equals("S1")) {
			semester = "1";
		} else if (semester.equals("S2")) {
			semester = "2";
		} else if (semester.equals("Y1")) {
			semester = "0";
		} else if (semester.equals("Y2")) {
			semester = "6";
		}

		ModuleInfoRequest request = new ModuleInfoRequest();
		request.setAcademicYear(academicYear);
		request.setModuleCode(course);
		request.setSemesterPeriod(Integer.parseInt(semester));

		StudyMaterialResponse responseString = null;
		//Response response = null;

		//try {
		RestTemplate restTemplate = new RestTemplate();
		responseString = restTemplate.postForObject(uri, request, StudyMaterialResponse.class);
//			String serverpath = ServerConfigurationService.getServerUrl();
//			ResteasyClient client = new ResteasyClientBuilder().build();
//
//			if (serverpath.contains("myqa") || serverpath.contains("localhost") || serverpath.contains("mydev")) {
//
//				ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine(createAllTrustingClient());
//				client = new ResteasyClientBuilder().httpEngine(engine).build();
//				//ResteasyWebTarget target = client.target("http://lmkn-ealb01sv.int.unisa.ac.za/sharedservices/courseMaterial");
//			}
//
//			//client = new ResteasyClientBuilder().build();
//			ResteasyWebTarget target = client.target(ServerConfig.currSCMURL());
//			response = target.request().post(Entity.entity(request, MediaType.APPLICATION_XML));
//			repsonseString = response.readEntity(StudyMaterialResponse.class);

		//	} catch (Exception e) {

//			if (restTemplate.getStatus() != 200) {
//				logger.error("Failed : HTTP error code : " + response.getStatus());
//				throw new RuntimeException("Please note that UNISA study materials are not currently available for viewing. Access to the materials should be restored within an hour. UNISA apologizes for any inconvenience caused.");
//			}
//
//			try {
//				throw e;
//			} catch (Exception ex) {
//				ex.printStackTrace();
//				throw ex;
//			}
//
//		} finally {
//			if (response != null) {
//				response.close();
//			}
//		}
		Collections.sort(responseString.getResource());

		List<StudyMaterialDetailInfo> studyMaterialDTOList = new ArrayList<StudyMaterialDetailInfo>();

		for (ResourceDTO resourceDTO : responseString.getResource()) {
			StudyMaterialDetailInfo studymaterialDetails = new StudyMaterialDetailInfo();
			studymaterialDetails.setCourseCode(resourceDTO.getModule());
			studymaterialDetails.setAcademicYear(resourceDTO.getYear());
			studymaterialDetails.setSemister(resourceDTO.getPeriod());
			studymaterialDetails.setShortDescription(resourceDTO.getShortDescription());
			studymaterialDetails.setFilesize(resourceDTO.getFileSize());

			if (resourceDTO.getDocumentType().equalsIgnoreCase("mo")) {
				logger.debug("TPstudymaterial SCMWebService:getStudyMaterialList DocumentType is  " + resourceDTO.getDocumentType());
			} else {

				String itemDisplayName = setItemDisplayName(resourceDTO.getDocumentType(), resourceDTO.getUnitNumber(),
					getlanguage(resourceDTO.getShortDescription()), studymaterialDetails.getCourseCode());

				studymaterialDetails.setDiscription(itemDisplayName);

				studymaterialDetails.setImplementationDate(getDBDateFormat(toDate(resourceDTO.getDateAvailable())));
				StudyMaterialCodesConverter codesConverter = new StudyMaterialCodesConverter();
				String filename = getfileName(resourceDTO.getShortDescription());


				//long fizeSize = getFileSize(filename,studymaterialDetails.getCourseCode(),resourceDTO.getDocumentType());


				if (resourceDTO.getFileSize().equalsIgnoreCase("unavailable")) {
					//send an email to fix the files

					//sendEmailToFixFiles(course,academicYear,semister);
					sendEmailToFixFiles(resourceDTO.getDept(), course, resourceDTO.getShortDescription(), resourceDTO.getBarcode());

				} else if (isDateBeforeSysDate(toDate(resourceDTO.getDateAvailable()))) {

					studyMaterialDTOList.add(studymaterialDetails);
				}


			}

		}

		logger.debug("TPstudymaterial SCMWebService:getStudyMaterialList size " + studyMaterialDTOList.size());

		//response.close();


		return studyMaterialDTOList;
	}


	public void sendEmailToFixFiles(String dept, String course, String shortDesrciption, String barcode) throws Exception {

		String emailAddress = "unavailableSM@unisa.ac.za";
//
//		emailService = (EmailService) ComponentManager.get(EmailService.class);
//		String subject = dept + " " + course + " " + shortDesrciption + " " + barcode;
//		String body = "";
//
//		String tmpEmailFrom = ServerConfigurationService.getString("noReplyEmailFrom");
//		InternetAddress toEmail = new InternetAddress(emailAddress);
//		InternetAddress iaTo[] = new InternetAddress[1];
//		iaTo[0] = toEmail;
//		InternetAddress iaHeaderTo[] = new InternetAddress[1];
//		iaHeaderTo[0] = toEmail;
//		InternetAddress iaReplyTo[] = new InternetAddress[1];
//		iaReplyTo[0] = new InternetAddress(tmpEmailFrom);
//		List contentList = new ArrayList();
//		contentList.add("Content-Type: text/html");
//
//		emailService.sendMail(iaReplyTo[0], iaTo, subject, body, iaHeaderTo, iaReplyTo, contentList);
	}

	public static String getFilepath(String filename, String module, String docType) {
		//String filePath = ServerConfig.getPathToMaterialOnServer() + module + "/" + docType + "/" + filename;
		String filePath = "c://" + module + "/" + docType + "/" + filename;

		File pdfFile = new File(filePath);
		if (!pdfFile.exists()) {
			//filePath = ServerConfig.getPathToMaterialOnServer() + "collect" + "/" + module + "/" + docType + "/" + filename;
			filePath = "c://" + "collect" + "/" + module + "/" + docType + "/" + filename;
		}

		return filePath;
	}

	public static Date toDate(XMLGregorianCalendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}

	public Date today() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	public boolean isDateBeforeSysDate(Date dateStr) {//yyyy-mm-dd
		Date today = today();
		//  Date availableDate=getDateFromStr(dateStr);
		if (dateStr.after(today)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isAvailableDateValid(Date dateAvailable) {
		//DateUtil dateutil = new DateUtil();
		return isDateBeforeSysDate(dateAvailable);
	}

/*public Date  getDateFromStr(String dateStr){
		Calendar cal = Calendar.getInstance();
		String yearStr=dateStr.substring(0,4);
		String monthStr=dateStr.substring(5,7);
		String dayStr=dateStr.substring(8);
		int year=Integer.parseInt(yearStr);
		int month=Integer.parseInt(monthStr);
		month--;
		int day=Integer.parseInt(dayStr);
		cal.clear();
		cal.set(year,month,day);
		return  cal.getTime();
	}*/

	public static String getDBDateFormat(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String d = format.format(date);
		//System.out.println(""+d);
		Date d1 = null;
		try {
			d1 = format.parse(d);
			//   System.out.println("d1-->"+d1.toString());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return d;

	}

	public static String setItemDisplayName(String documentType, String unitNumber, String language, String module) {

		StudyMaterialCodesConverter codesConverter = new StudyMaterialCodesConverter();
		String itemDisplayName = codesConverter.convertCode(documentType) + "  " + unitNumber + " (" +
			codesConverter.convertCode(language) + ")" + " for " + module;
		return itemDisplayName;
	}

	public static long getFileSize(String filename, String module, String docType) {

		if (docType != null) {
			docType = StudyMaterialLocation.getStudyMaterialTypeDirectoryName(docType);
		}
		long fileSize = 0;

		try {
			String filePath = getFilepath(filename, module, docType);
			File pdfFile = new File(filePath);
			fileSize = (pdfFile.length() / 1024);
		} catch (Exception ex) {
			return 0;
		}
		return fileSize;
	}

	public static String getlanguage(String itemShortDesdc) {

		int lastIndexOff_ = itemShortDesdc.lastIndexOf("_") + 1;
		return itemShortDesdc.substring(lastIndexOff_).trim().toUpperCase();
	}


	public static String getfileName(String itemShortDesdc) {

		String s[] = itemShortDesdc.split("_");
		String modCode = s[0];
		String yr = s[1];
		String type = s[2];
		String nr = s[3];
		String period = s[4];
		String lang = s[5];
		String filename = nr + "_" + yr + "_" + period + "_" + lang.toLowerCase() + ".pdf";

		return filename;
	}

	public static String getModule(String itemShortDesdc) {
		String s[] = itemShortDesdc.split("_");
		return s[0];
	}

//	private static DefaultHttpClient createAllTrustingClient()
//		throws GeneralSecurityException {
//
//		SchemeRegistry registry = new SchemeRegistry();
//
//		registry.register(new Scheme("http", 80, PlainSocketFactory
//			.getSocketFactory()));
//
//		TrustStrategy trustStrategy = new TrustStrategy() {
//			public boolean isTrusted(X509Certificate[] chain, String authType)
//				throws CertificateException {
//				return true;
//			}
//		};
//		SSLSocketFactory factory = new SSLSocketFactory(trustStrategy,
//			SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//		registry.register(new Scheme("https", 443, factory));
//
//		ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(
//			registry);
//		mgr.setMaxTotal(1000);
//		mgr.setDefaultMaxPerRoute(1000);
//
//		DefaultHttpClient client = new DefaultHttpClient(mgr,
//			new DefaultHttpClient().getParams());
//		return client;
//	}

}
