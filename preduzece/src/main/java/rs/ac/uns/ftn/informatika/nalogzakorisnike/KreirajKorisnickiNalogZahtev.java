//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.09.09 at 05:47:43 PM CEST 
//


package rs.ac.uns.ftn.informatika.nalogzakorisnike;

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
 *         &lt;element name="Korisnicki_Nalog" type="{http://www.informatika.ftn.uns.ac.rs/nalogZaKorisnike}Korisnicki_nalog"/>
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
    "korisnickiNalog"
})
@XmlRootElement(name = "kreirajKorisnickiNalogZahtev")
public class KreirajKorisnickiNalogZahtev {

    @XmlElement(name = "Korisnicki_Nalog", required = true)
    protected KorisnickiNalog korisnickiNalog;

    /**
     * Gets the value of the korisnickiNalog property.
     * 
     * @return
     *     possible object is
     *     {@link KorisnickiNalog }
     *     
     */
    public KorisnickiNalog getKorisnickiNalog() {
        return korisnickiNalog;
    }

    /**
     * Sets the value of the korisnickiNalog property.
     * 
     * @param value
     *     allowed object is
     *     {@link KorisnickiNalog }
     *     
     */
    public void setKorisnickiNalog(KorisnickiNalog value) {
        this.korisnickiNalog = value;
    }

}
