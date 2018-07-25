package za.ac.unisa.myadmin.studymaterial.integration.services.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "studyMaterialDTO", propOrder = {
    "resource"
})
@XmlSeeAlso({
    StudyMaterialResponse.class
})
public class StudyMaterialDTO {

    @XmlElement(name = "Resource")
    protected List<ResourceDTO> resource;

    /**
     * Gets the value of the resource property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resource property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResource().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResourceDTO }
     *
     *
     */
    public List<ResourceDTO> getResource() {
        if (resource == null) {
            resource = new ArrayList<>();
        }
        return this.resource;
    }

}
