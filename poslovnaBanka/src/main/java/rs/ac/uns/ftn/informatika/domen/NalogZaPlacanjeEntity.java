package rs.ac.uns.ftn.informatika.domen;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import rs.ac.uns.ftn.informatika.nalogzaplacanje.Nalog;
@Entity
public class NalogZaPlacanjeEntity {
	@Id
	@GeneratedValue
	private Long id;
    protected String idPoruke;
    protected String duznik;
    protected String svrhaPlacanja;
    protected String primalac;
    protected Date datumNaloga;
    protected Date datumValute;
    protected String racunDuznika;
    protected BigInteger modelZaduzenja;
    protected String pozivNaBrojZaduzenja;
    protected String racunPrimaoca;
    protected BigInteger modelOdobrenja;
    protected String pozivNaBrojOdobrenja;
    protected BigDecimal iznos;
    protected String oznakaValute;
    protected boolean hitno;
    protected boolean clear;
    
   
	public NalogZaPlacanjeEntity() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdPoruke() {
		return idPoruke;
	}
	public void setIdPoruke(String idPoruke) {
		this.idPoruke = idPoruke;
	}
	public String getDuznik() {
		return duznik;
	}
	public void setDuznik(String duznik) {
		this.duznik = duznik;
	}
	public String getSvrhaPlacanja() {
		return svrhaPlacanja;
	}
	public void setSvrhaPlacanja(String svrhaPlacanja) {
		this.svrhaPlacanja = svrhaPlacanja;
	}
	public String getPrimalac() {
		return primalac;
	}
	public void setPrimalac(String primalac) {
		this.primalac = primalac;
	}
	public Date getDatumNaloga() {
		return datumNaloga;
	}
	public void setDatumNaloga(Date datumNaloga) {
		this.datumNaloga = datumNaloga;
	}
	public Date getDatumValute() {
		return datumValute;
	}
	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}
	public String getRacunDuznika() {
		return racunDuznika;
	}
	public void setRacunDuznika(String racunDuznika) {
		this.racunDuznika = racunDuznika;
	}
	public BigInteger getModelZaduzenja() {
		return modelZaduzenja;
	}
	public void setModelZaduzenja(BigInteger modelZaduzenja) {
		this.modelZaduzenja = modelZaduzenja;
	}
	public String getPozivNaBrojZaduzenja() {
		return pozivNaBrojZaduzenja;
	}
	public void setPozivNaBrojZaduzenja(String pozivNaBrojZaduzenja) {
		this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
	}
	public String getRacunPrimaoca() {
		return racunPrimaoca;
	}
	public void setRacunPrimaoca(String racunPrimaoca) {
		this.racunPrimaoca = racunPrimaoca;
	}
	public BigInteger getModelOdobrenja() {
		return modelOdobrenja;
	}
	public void setModelOdobrenja(BigInteger modelOdobrenja) {
		this.modelOdobrenja = modelOdobrenja;
	}
	public String getPozivNaBrojOdobrenja() {
		return pozivNaBrojOdobrenja;
	}
	public void setPozivNaBrojOdobrenja(String pozivNaBrojOdobrenja) {
		this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
	}
	public BigDecimal getIznos() {
		return iznos;
	}
	public void setIznos(BigDecimal iznos) {
		this.iznos = iznos;
	}
	public String getOznakaValute() {
		return oznakaValute;
	}
	public void setOznakaValute(String oznakaValute) {
		this.oznakaValute = oznakaValute;
	}
	public boolean isHitno() {
		return hitno;
	}
	public void setHitno(boolean hitno) {
		this.hitno = hitno;
	}

	public boolean isClear() {
		return clear;
	}

	public void setClear(boolean clear) {
		this.clear = clear;
	}
    
    
    
}
