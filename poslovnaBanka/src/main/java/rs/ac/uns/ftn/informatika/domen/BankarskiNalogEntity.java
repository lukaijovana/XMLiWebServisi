package rs.ac.uns.ftn.informatika.domen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import rs.ac.uns.ftn.informatika.bankarskinalog.BankarskiNalog;

@Entity
public class BankarskiNalogEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String sifraBanke;
	private String port;
	private String SWIFT;
	private String obracunskiRacun;
	private double stanjeNaRacunu = 1000000;
	public BankarskiNalogEntity() {
	}
	
	public BankarskiNalogEntity(BankarskiNalog nalog) {
		this.setObracunskiRacun(nalog.getRacun());
		this.setPort(nalog.getBnkPort());
		this.setSifraBanke(nalog.getSifraBanke());
		this.setStanjeNaRacunu(nalog.getStanjeRacuna());
		this.setSWIFT(nalog.getSWIFT());
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSifraBanke() {
		return sifraBanke;
	}
	public void setSifraBanke(String sifraBanke) {
		this.sifraBanke = sifraBanke;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getObracunskiRacun() {
		return obracunskiRacun;
	}
	public void setObracunskiRacun(String obracunskiRacun) {
		this.obracunskiRacun = obracunskiRacun;
	}
	public double getStanjeNaRacunu() {
		return stanjeNaRacunu;
	}
	public void setStanjeNaRacunu(double stanjeNaRacunu) {
		this.stanjeNaRacunu = stanjeNaRacunu;
	}
	public String getSWIFT() {
		return SWIFT;
	}
	public void setSWIFT(String sWIFT) {
		SWIFT = sWIFT;
	}
	
	
	

}
