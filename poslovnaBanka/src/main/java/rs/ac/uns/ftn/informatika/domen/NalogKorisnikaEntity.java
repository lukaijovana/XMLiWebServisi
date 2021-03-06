package rs.ac.uns.ftn.informatika.domen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import rs.ac.uns.ftn.informatika.nalogzakorisnike.NalogKorisnika;

@Entity
public class NalogKorisnikaEntity {
	@Id
	@GeneratedValue
	private Long id;
    private String portKorisnika;
    private String portBanke;
    private String racun;
    private String naziv;
    private String pib;
    private double stanjeNaRacunu;
    private double rezervisanaSuma;
	public NalogKorisnikaEntity() {
	}
	
	
	public NalogKorisnikaEntity(NalogKorisnika nalog) {
		this.portKorisnika = nalog.getPortKorisnika();
		this.portBanke = nalog.getPortBnk();
		this.racun = nalog.getRacun();
		this.naziv = nalog.getNaziv();
		this.pib = nalog.getPIB();
		this.stanjeNaRacunu = nalog.getStanjeNaRacunu();
		this.rezervisanaSuma = nalog.getRezervisano();
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
	public String getPib() {
		return pib;
	}
	public void setPib(String pib) {
		this.pib = pib;
	}
	public double getStanjeNaRacunu() {
		return stanjeNaRacunu;
	}
	public void setStanjeNaRacunu(double stanjeNaRacunu) {
		this.stanjeNaRacunu = stanjeNaRacunu;
	}
	public double getRezervisanaSuma() {
		return rezervisanaSuma;
	}
	public void setRezervisanaSuma(double rezervisanaSuma) {
		this.rezervisanaSuma = rezervisanaSuma;
	}
    
    
}
