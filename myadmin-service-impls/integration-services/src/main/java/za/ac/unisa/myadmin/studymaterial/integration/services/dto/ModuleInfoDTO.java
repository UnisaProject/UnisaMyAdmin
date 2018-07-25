package za.ac.unisa.myadmin.studymaterial.integration.services.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "moduleInfoDTO", propOrder = {
    "academicYear",
    "moduleCode",
    "semesterPeriod"
})
@XmlSeeAlso({
    ModuleInfoRequest.class
})
public class ModuleInfoDTO {

    protected Integer academicYear;
    protected String moduleCode;
    protected Integer semesterPeriod;

    /**
     * Gets the value of the academicYear property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getAcademicYear() {
        return academicYear;
    }

    /**
     * Sets the value of the academicYear property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setAcademicYear(Integer value) {
        this.academicYear = value;
    }

    /**
     * Gets the value of the moduleCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Sets the value of the moduleCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setModuleCode(String value) {
        this.moduleCode = value;
    }

    /**
     * Gets the value of the semesterPeriod property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSemesterPeriod() {
        return semesterPeriod;
    }

    /**
     * Sets the value of the semesterPeriod property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSemesterPeriod(Integer value) {
        this.semesterPeriod = value;
    }

}
