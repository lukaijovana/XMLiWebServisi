package rs.ac.uns.ftn.informatika.domen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Partner {
	@Id
	@GeneratedValue
	private Long id;
	private String port;
	private String pib;
	private String adresa;
	private String naziv;
	private String racun;
	
	public Partner() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getPib() {
		return pib;
	}
	public void setPib(String pib) {
		this.pib = pib;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getRacun() {
		return racun;
	}
	public void setRacun(String racun) {
		this.racun = racun;
	}
	
	
}
