//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.09.09 at 05:48:52 PM CEST 
//


package rs.ac.uns.ftn.informatika.bankarskinalog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Bankarski_nalog complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Bankarski_nalog">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Sifra_banke" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Bnk_port" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SWIFT">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="8"/>
 *               &lt;minLength value="8"/>
 *               &lt;pattern value="[A-Z]{4}[A-Z]{2}[A-Z0-9]{2}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Racun" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Stanje_racuna" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bankarski_nalog", propOrder = {
    "sifraBanke",
    "bnkPort",
    "swift",
    "racun",
    "stanjeRacuna"
})
public class BankarskiNalog {

    @XmlElement(name = "Sifra_banke", required = true)
    protected String sifraBanke;
    @XmlElement(name = "Bnk_port", required = true)
    protected String bnkPort;
    @XmlElement(name = "SWIFT", required = true)
    protected String swift;
    @XmlElement(name = "Racun", required = true)
    protected String racun;
    @XmlElement(name = "Stanje_racuna")
    protected double stanjeRacuna;

    /**
     * Gets the value of the sifraBanke property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifraBanke() {
        return sifraBanke;
    }

    /**
     * Sets the value of the sifraBanke property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifraBanke(String value) {
        this.sifraBanke = value;
    }

    /**
     * Gets the value of the bnkPort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBnkPort() {
        return bnkPort;
    }

    /**
     * Sets the value of the bnkPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBnkPort(String value) {
        this.bnkPort = value;
    }

    /**
     * Gets the value of the swift property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSWIFT() {
        return swift;
    }

    /**
     * Sets the value of the swift property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSWIFT(String value) {
        this.swift = value;
    }

    /**
     * Gets the value of the racun property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRacun() {
        return racun;
    }

    /**
     * Sets the value of the racun property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRacun(String value) {
        this.racun = value;
    }

    /**
     * Gets the value of the stanjeRacuna property.
     * 
     */
    public double getStanjeRacuna() {
        return stanjeRacuna;
    }

    /**
     * Sets the value of the stanjeRacuna property.
     * 
     */
    public void setStanjeRacuna(double value) {
        this.stanjeRacuna = value;
    }

}
