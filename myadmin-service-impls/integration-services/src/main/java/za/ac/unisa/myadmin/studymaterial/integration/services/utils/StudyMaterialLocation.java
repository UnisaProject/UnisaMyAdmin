package za.ac.unisa.myadmin.studymaterial.integration.services.utils;

import java.io.File;

/**
 * Comes directly from unisa
 * sourcecode.
 */
public class StudyMaterialLocation {

	public static String getMaterialFilePath(String module, String itemShortDescription)
			throws Exception {
		String filename = getfileName(itemShortDescription);
		String documentType = getType(itemShortDescription);
		if (documentType != null) {
			documentType = StudyMaterialLocation.getStudyMaterialTypeDirectoryName(documentType);
		}
		boolean collectionExistFlag = isCollectionStudyMaterial(documentType, module, filename);

		return getFilePath(module, documentType, filename, collectionExistFlag);
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

	private static String getType(String itemShortDesdc) {
		String s[] = itemShortDesdc.split("_");
		// if (ServerConfig.isTestEnvironment()) {
		return s[2].toUpperCase();
		// } else {
		// return s[2];
		// }
	}

	private static String getFilePath(String module, String documentType, String fileName, boolean collectionExistFlag) {
		String filefullPath;
		if (collectionExistFlag) {
			filefullPath = getUploadDirPathForCollection(documentType, module);
			filefullPath = filefullPath + fileName;
		} else {
			filefullPath = getUploadDirPathForSingle(documentType, module);
			filefullPath = filefullPath + fileName;
		}
		return filefullPath;
	}

	public static String getUploadDirPathForCollection(String type, String module) {
		return "c://collect/" + module + "/" + type + "/";
		//return ServerConfig.studyMaterialPath + "collect/" + module + "/" + type + "/";
	}

	public static String getUploadDirPathForSingle(String type, String module) {
		return "c://" + module + "/" + type + "/";
		//return ServerConfig.studyMaterialPath + module + "/" + type + "/";
	}

	public static boolean isCollectionStudyMaterial(String type, String module, String inputFilename) {

		String collection = getUploadDirPathForCollection(type, module);
		File file = new File(collection + inputFilename);
		return file.exists() ? Boolean.TRUE : Boolean.FALSE;
	}

	public static String getStudyMaterialTypeDirectoryName(String annType) {

		String dirType = null;

		if ((annType.equalsIgnoreCase("TL"))
			|| (annType.equalsIgnoreCase("TW"))
			|| (annType.equalsIgnoreCase("TP"))) {
			dirType = "tl";
		} else if ((annType.equalsIgnoreCase("GD"))
			|| (annType.equalsIgnoreCase("SW"))
			|| (annType.equalsIgnoreCase("SP"))) {
			dirType = "sg";
		} else if (annType.equalsIgnoreCase("MA")) {
			dirType = "ma";
		} else if (annType.equalsIgnoreCase("WB")) {
			dirType = "wb";
		} else if (annType.equalsIgnoreCase("QB")) {
			dirType = "qb";
		} else if (annType.equalsIgnoreCase("MO")) {
			dirType = "mo";
		} else if (annType.equalsIgnoreCase("LB")) {
			dirType = "lb";
		} else if (annType.equalsIgnoreCase("BL")) {
			dirType = "bl";
		} else if (annType.equalsIgnoreCase("BB")) {
			dirType = "bb";
		} else if (annType.equalsIgnoreCase("HL")) {
			dirType = "hl";
		} else if (annType.equalsIgnoreCase("RE")) {
			dirType = "re";
		} else if (annType.equalsIgnoreCase("MG")) {
			dirType = "mg";
		}

		return dirType;
	}

}


