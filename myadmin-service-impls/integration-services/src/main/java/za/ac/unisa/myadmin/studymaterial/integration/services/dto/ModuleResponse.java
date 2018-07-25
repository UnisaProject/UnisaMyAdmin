package za.ac.unisa.myadmin.studymaterial.integration.services.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "moduleResponse", propOrder = {
    "moduleInfo"
})
public class ModuleResponse {

    @XmlElement(name = "ModuleInfo")
    protected List<ModuleInfoDTO> moduleInfo;

    /**
     * Gets the value of the moduleInfo property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the moduleInfo property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModuleInfo().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ModuleInfoDTO }
     *
     *
     */
    public List<ModuleInfoDTO> getModuleInfo() {
        if (moduleInfo == null) {
            moduleInfo = new ArrayList<ModuleInfoDTO>();
        }
        return this.moduleInfo;
    }

}
