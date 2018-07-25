package za.ac.unisa.myadmin.studymaterial.integration.services.utils;

import java.util.HashMap;

public class StudyMaterialCodesConverter {

	private static final HashMap<String, String> CODES_MAP = new HashMap<>();

	static {
		CODES_MAP.put("bb", "Business Calculations");
		CODES_MAP.put("bl", "Booklet");
		CODES_MAP.put("hl", "H Law Documents");
		CODES_MAP.put("lb", "Logbook");
		CODES_MAP.put("ma", "Florida Manual");
		CODES_MAP.put("mg", "Mentor Guide");
		CODES_MAP.put("mo", "Module");
		CODES_MAP.put("qb", "Question Bank");
		CODES_MAP.put("re", "Reader");
		CODES_MAP.put("sg", "Study Guide");
		CODES_MAP.put("sw", "Study Guide (MS Word)");
		CODES_MAP.put("tl", "Tutorial Letter");
		CODES_MAP.put("tw", "Tutorial Letter (MS Word)");
		CODES_MAP.put("tp", "Tutorial Letter");
		CODES_MAP.put("wb", "Workbook");
		CODES_MAP.put("a", "Afr");
		CODES_MAP.put("e", "Eng");
		CODES_MAP.put("b", "Both");
		CODES_MAP.put("qp", "Examination Question Paper");
		CODES_MAP.put("dv", "DV");
		CODES_MAP.put("cm", "CM");
	}
	private StudyMaterialCodesConverter(){
	}

	public static String convertCode(String studyMaterialCode) {
		if (studyMaterialCode != null){
			if(studyMaterialCode.equalsIgnoreCase("GD")) {
				studyMaterialCode = "sg";
			}
			studyMaterialCode = studyMaterialCode.toLowerCase();
		}

		return CODES_MAP.getOrDefault(studyMaterialCode, "Tutorial Letter");
	}
}
