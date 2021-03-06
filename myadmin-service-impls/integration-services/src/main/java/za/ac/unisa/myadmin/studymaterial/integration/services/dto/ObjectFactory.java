package za.ac.unisa.myadmin.studymaterial.integration.services.dto;

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

	private final static QName _Modules_QNAME = new QName("", "Modules");
	private final static QName _ModuleInfo_QNAME = new QName("", "ModuleInfo");
	private final static QName _StudyMaterial_QNAME = new QName("", "StudyMaterial");


	/**
	 * Create an instance of {@link StudyMaterialResponse }
	 *
	 */
	public StudyMaterialResponse createStudyMaterialResponse() {
		return new StudyMaterialResponse();
	}

	/**
	 * Create an instance of {@link ModuleInfoDTO }
	 *
	 */
	public ModuleInfoDTO createModuleInfoDTO() {
		return new ModuleInfoDTO();
	}

	/**
	 * Create an instance of {@link ModuleResponse }
	 *
	 */
	public ModuleResponse createModuleResponse() {
		return new ModuleResponse();
	}

	/**
	 * Create an instance of {@link ModuleInfoRequest }
	 *
	 */
	public ModuleInfoRequest createModuleInfoRequest() {
		return new ModuleInfoRequest();
	}


	/**
	 * Create an instance of {@link StudyMaterialDTO }
	 *
	 */
	public StudyMaterialDTO createStudyMaterialDTO() {
		return new StudyMaterialDTO();
	}


	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ModuleResponse }{@code >}}
	 *
	 */
	@XmlElementDecl(namespace = "", name = "Modules")
	public JAXBElement<ModuleResponse> createModules(ModuleResponse value) {
		return new JAXBElement<ModuleResponse>(_Modules_QNAME, ModuleResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ModuleInfoRequest }{@code >}}
	 *
	 */
	@XmlElementDecl(namespace = "", name = "ModuleInfo")
	public JAXBElement<ModuleInfoRequest> createModuleInfo(ModuleInfoRequest value) {
		return new JAXBElement<ModuleInfoRequest>(_ModuleInfo_QNAME, ModuleInfoRequest.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link StudyMaterialResponse }{@code >}}
	 *
	 */
	@XmlElementDecl(namespace = "", name = "StudyMaterial")
	public JAXBElement<StudyMaterialResponse> createStudyMaterial(StudyMaterialResponse value) {
		return new JAXBElement<StudyMaterialResponse>(_StudyMaterial_QNAME, StudyMaterialResponse.class, null, value);
	}

}
