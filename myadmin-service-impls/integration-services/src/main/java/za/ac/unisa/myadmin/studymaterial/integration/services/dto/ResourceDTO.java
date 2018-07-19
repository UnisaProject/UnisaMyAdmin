//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.27 at 11:09:57 AM CAT 
//


package za.ac.unisa.myadmin.studymaterial.integration.services.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for resourceDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="resourceDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="barcode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="shortDescription" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="fullDescription" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="unitNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="documentType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="module" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="period" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="year" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dateAvailable" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="fileSize" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dept" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="lecturer" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="category" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resourceDTO")
public class ResourceDTO implements Comparable<ResourceDTO>{

    @XmlAttribute
    protected String barcode;
    @XmlAttribute
    protected String shortDescription;
    @XmlAttribute
    protected String fullDescription;
    @XmlAttribute
    protected String unitNumber;
    @XmlAttribute
    protected String documentType;
    @XmlAttribute
    protected String module;
    @XmlAttribute
    protected String period;
    @XmlAttribute
    protected String year;
    @XmlAttribute
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateAvailable;
    @XmlAttribute
    protected String fileSize;
    @XmlAttribute
    protected String dept;
    @XmlAttribute
    protected String lecturer;
    @XmlAttribute
    protected String category;
    
	@Override
	public int compareTo(ResourceDTO o) {
		 if (toDate(this.getDateAvailable()) == null) {
			  return 0;
		  }else if (toDate(o.getDateAvailable()) == null) {
			  return 0;
		  }
		 if (toDate(o.getDateAvailable()).compareTo(toDate(this.getDateAvailable())) == 0) {
			// System.out.println("value-->"+toDate(o.getDateAvailable()).compareTo(toDate(this.getDateAvailable())));
			  try { 
			    if  ((Integer.parseInt(o.getUnitNumber()) - Integer.parseInt(this.getUnitNumber())) == 0){
				    return 0;
			    } else {
				   return (Integer.parseInt(o.getUnitNumber()) - Integer.parseInt(this.getUnitNumber()));
			      } 
			  }catch(Exception e){
				  return 0;
			  }
			 
			 //return toDate(o.getDateAvailable()).compareTo(toDate(this.getDateAvailable()));
		 }
		// System.out.println("value-->"+toDate(this.getDateAvailable()).compareTo(toDate(o.getDateAvailable())));
		 return toDate(o.getDateAvailable()).compareTo(toDate(this.getDateAvailable()));
	}
	
	public static Date toDate(XMLGregorianCalendar calendar){
        if(calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }



    /**
     * Gets the value of the barcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Sets the value of the barcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarcode(String value) {
        this.barcode = value;
    }

    /**
     * Gets the value of the shortDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets the value of the shortDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortDescription(String value) {
        this.shortDescription = value;
    }

    /**
     * Gets the value of the fullDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullDescription() {
        return fullDescription;
    }

    /**
     * Sets the value of the fullDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullDescription(String value) {
        this.fullDescription = value;
    }

    /**
     * Gets the value of the unitNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitNumber() {
        return unitNumber;
    }

    /**
     * Sets the value of the unitNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitNumber(String value) {
        this.unitNumber = value;
    }

    /**
     * Gets the value of the documentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * Sets the value of the documentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentType(String value) {
        this.documentType = value;
    }

    /**
     * Gets the value of the module property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModule() {
        return module;
    }

    /**
     * Sets the value of the module property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModule(String value) {
        this.module = value;
    }

    /**
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriod(String value) {
        this.period = value;
    }

    /**
     * Gets the value of the year property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYear(String value) {
        this.year = value;
    }

    /**
     * Gets the value of the dateAvailable property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateAvailable() {
        return dateAvailable;
    }

    /**
     * Sets the value of the dateAvailable property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateAvailable(XMLGregorianCalendar value) {
        this.dateAvailable = value;
    }

    /**
     * Gets the value of the fileSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * Sets the value of the fileSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileSize(String value) {
        this.fileSize = value;
    }

    /**
     * Gets the value of the dept property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDept() {
        return dept;
    }

    /**
     * Sets the value of the dept property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDept(String value) {
        this.dept = value;
    }

    /**
     * Gets the value of the lecturer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLecturer() {
        return lecturer;
    }

    /**
     * Sets the value of the lecturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLecturer(String value) {
        this.lecturer = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

}
