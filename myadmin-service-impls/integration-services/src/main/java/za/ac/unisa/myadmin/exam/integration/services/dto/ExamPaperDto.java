package za.ac.unisa.myadmin.exam.integration.services.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "exampaper", propOrder = {
	"academicYear",
	"moduleCode",
	"semesterPeriod"
})
public class ExamPaperDto {

	private String courseCode;
	private int year;
	private int period;
	private String language;
	private int paper;
	private long fileSize;
	private String link;
}
