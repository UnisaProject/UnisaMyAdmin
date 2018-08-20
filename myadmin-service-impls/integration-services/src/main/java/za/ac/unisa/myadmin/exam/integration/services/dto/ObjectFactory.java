package za.ac.unisa.myadmin.exam.integration.services.dto;

import za.ac.unisa.myadmin.studymaterial.integration.services.dto.*;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _ExamPapers_QNAME = new QName("", "exampapers");


	/**
	 * Create an instance of {@link ModuleInfoRequest }
	 *
	 */
	public ExamPaperResponse createExamPaperResponse() {
		return new ExamPaperResponse();
	}


	/**
	 * Create an instance of {@link StudyMaterialDTO }
	 *
	 */
	public ExamPaperDto createExamPaperDto() {
		return new ExamPaperDto();
	}


	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ExamPaperResponse }{@code >}}
	 *
	 */
	@XmlElementDecl(namespace = "", name = "exampapers")
	public JAXBElement<ExamPaperResponse> createModules(ExamPaperResponse value) {
		return new JAXBElement<ExamPaperResponse>(_ExamPapers_QNAME, ExamPaperResponse.class, null, value);
	}

}
