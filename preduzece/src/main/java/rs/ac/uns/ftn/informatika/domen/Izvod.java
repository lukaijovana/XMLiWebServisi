package rs.ac.uns.ftn.informatika.domen;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Izvod {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 18)
	private String brojRacuna;
	private Date datumNaloga;
	private int brojPreseka;
	@Column(columnDefinition="Decimal(15,2)")
	private double prethodnoStanje;
	private int brojPromenaUKorist;
	@Column(columnDefinition="Decimal(15,2)")
	private double ukupnoUKorist;
	private int brojPromenaNaTeret;
	@Column(columnDefinition="Decimal(15,2)")
	private double ukupnoNaTeret;
	@Column(columnDefinition="Decimal(15,2)")
	private double novoStanje;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<StavkaIzvoda> stavke;

	public Izvod() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public Date getDatumNaloga() {
		return datumNaloga;
	}

	public void setDatumNaloga(Date datumNaloga) {
		this.datumNaloga = datumNaloga;
	}

	public int getBrojPreseka() {
		return brojPreseka;
	}

	public void setBrojPreseka(int brojPreseka) {
		this.brojPreseka = brojPreseka;
	}

	public double getPrethodnoStanje() {
		return prethodnoStanje;
	}

	public void setPrethodnoStanje(double prethodnoStanje) {
		this.prethodnoStanje = prethodnoStanje;
	}

	public int getBrojPromenaUKorist() {
		return brojPromenaUKorist;
	}

	public void setBrojPromenaUKorist(int brojPromenaUKorist) {
		this.brojPromenaUKorist = brojPromenaUKorist;
	}

	public double getUkupnoUKorist() {
		return ukupnoUKorist;
	}

	public void setUkupnoUKorist(double ukupnoUKorist) {
		this.ukupnoUKorist = ukupnoUKorist;
	}

	public int getBrojPromenaNaTeret() {
		return brojPromenaNaTeret;
	}

	public void setBrojPromenaNaTeret(int brojPromenaNaTeret) {
		this.brojPromenaNaTeret = brojPromenaNaTeret;
	}

	public double getUkupnoNaTeret() {
		return ukupnoNaTeret;
	}

	public void setUkupnoNaTeret(double ukupnoNaTeret) {
		this.ukupnoNaTeret = ukupnoNaTeret;
	}

	public double getNovoStanje() {
		return novoStanje;
	}

	public void setNovoStanje(double novoStanje) {
		this.novoStanje = novoStanje;
	}

	public List<StavkaIzvoda> getStavke() {
		return stavke;
	}

	public void setStavke(List<StavkaIzvoda> stavke) {
		this.stavke = stavke;
	}
	
	
}
