//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.09.09 at 05:48:13 PM CEST 
//


package rs.ac.uns.ftn.informatika.mt910;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MT910Zahtev" type="{http://www.informatika.ftn.uns.ac.rs/mt910}MT910"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mt910Zahtev"
})
@XmlRootElement(name = "Zahtev910")
public class Zahtev910 {

    @XmlElement(name = "MT910Zahtev", required = true)
    protected MT910 mt910Zahtev;

    /**
     * Gets the value of the mt910Zahtev property.
     * 
     * @return
     *     possible object is
     *     {@link MT910 }
     *     
     */
    public MT910 getMT910Zahtev() {
        return mt910Zahtev;
    }

    /**
     * Sets the value of the mt910Zahtev property.
     * 
     * @param value
     *     allowed object is
     *     {@link MT910 }
     *     
     */
    public void setMT910Zahtev(MT910 value) {
        this.mt910Zahtev = value;
    }

}