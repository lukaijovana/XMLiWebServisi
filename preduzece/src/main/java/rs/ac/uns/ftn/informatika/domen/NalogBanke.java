package rs.ac.uns.ftn.informatika.domen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class NalogBanke {
	@Id
	@GeneratedValue
	private Long id;
	private String portKorisnika;
	private String portBanke;
	private String racun;
	private String naziv;
	private String adresa;
	private String pib;
	
	public NalogBanke() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPortKorisnika() {
		return portKorisnika;
	}
	public void setPortKorisnika(String portKorisnika) {
		this.portKorisnika = portKorisnika;
	}
	public String getPortBanke() {
		return portBanke;
	}
	public void setPortBanke(String portBanke) {
		this.portBanke = portBanke;
	}
	public String getRacun() {
		return racun;
	}
	public void setRacun(String racun) {
		this.racun = racun;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}
	
	
	
}
