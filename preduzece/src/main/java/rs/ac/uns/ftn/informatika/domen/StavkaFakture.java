package rs.ac.uns.ftn.informatika.domen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
@Entity
public class StavkaFakture {
	@Id
	@GeneratedValue
	private Long id;
	private int redniBroj;
	private String nazivRobe;
	private double kolicina;
	private String jedinicaMere;
	private double jedinicnaCena;
	private double procenatRabata;
	private double iznosRabata;
	private double vrednost;
	private double umanjenoZaRabat;
	private double ukupanPorez;
	public StavkaFakture() {
	}
	public int getRedniBroj() {
		return redniBroj;
	}
	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}
	public String getNazivRobe() {
		return nazivRobe;
	}
	public void setNazivRobe(String nazivRobe) {
		this.nazivRobe = nazivRobe;
	}
	public double getKolicina() {
		return kolicina;
	}
	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}
	public String getJedinicaMere() {
		return jedinicaMere;
	}
	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}
	public double getJedinicnaCena() {
		return jedinicnaCena;
	}
	public void setJedinicnaCena(double jedinicnaCena) {
		this.jedinicnaCena = jedinicnaCena;
	}
	public double getProcenatRabata() {
		return procenatRabata;
	}
	public void setProcenatRabata(double procenatRabata) {
		this.procenatRabata = procenatRabata;
	}
	public double getIznosRabata() {
		return iznosRabata;
	}
	public void setIznosRabata(double iznosRabata) {
		this.iznosRabata = iznosRabata;
	}
	public double getUmanjenoZaRabat() {
		return umanjenoZaRabat;
	}
	public void setUmanjenoZaRabat(double umanjenoZaRabat) {
		this.umanjenoZaRabat = umanjenoZaRabat;
	}
	public double getUkupanPorez() {
		return ukupanPorez;
	}
	public void setUkupanPorez(double ukupanPorez) {
		this.ukupanPorez = ukupanPorez;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getVrednost() {
		return vrednost;
	}
	public void setVrednost(double vrednost) {
		this.vrednost = vrednost;
	}
	
	
}
