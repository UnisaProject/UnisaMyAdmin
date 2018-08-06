package za.ac.unisa.myadmin.exam.integration.services.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 *     <exampapers>
 *     <exampaper>
 *         <coursecode>EDPHOD8</coursecode>
 *         <examyear>2015</examyear>
 *         <period>10</period>
 *         <language>A</language>
 *         <papernumber>1</papernumber>
 *         <filesize>396473</filesize>
 *         <link>&lt;a href="/rcmclient/default.aspx?ID=18078492&amp;NAME=EDPHOD8-2015-10-A-1"&gt;</link>
 *     </exampaper>
 *     </exampapers>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "exampapers", propOrder = {
	"examPapers"
})
public class ExamPaperResponse {

	@XmlElement(name = "exampaper")
	private List<ExamPaperDto> examPapers;

	/**
	 * Getter for property 'examPapers'.
	 *
	 * @return Value for property 'examPapers'.
	 */
	public List<ExamPaperDto> getExamPapers() {
		return examPapers;
	}

	/**
	 * Setter for property 'examPapers'.
	 *
	 * @param examPapers Value to set for property 'examPapers'.
	 */
	public void setExamPapers(List<ExamPaperDto> examPapers) {
		this.examPapers = examPapers;
	}
}
