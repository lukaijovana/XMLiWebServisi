package rs.ac.uns.ftn.informatika.domen;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Entity
public class Faktura {
	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 50)
	private String idPoruke;
	@Column(length = 255)
	private String nazivDobavljaca;
	@Column(length = 255)
	private String adresaDobavljaca;
	@Column(length = 11)
	private String pibDobavljaca;
	@Column(length = 55)
	private String nazivKupca;
	@Column(length = 55)
	private String adresaKupca;
	@Column(length = 11)
	private String pibKupca;
	private int brojRacuna;
	private Date datumRacuna;
	@Column(columnDefinition="Decimal(15,2)")
	private double vrednostRobe;
	@Column(columnDefinition="Decimal(15,2)")
	private double vrednostUsluga;
	@Column(columnDefinition="Decimal(15,2)")
	private double ukupnoRobeIUsluge;
	@Column(columnDefinition="Decimal(15,2)")
	private double ukupanRabat;
	@Column(columnDefinition="Decimal(15,2)")
	private double ukupanPorez;
	@Column(length = 3)
	private String oznakaValute;
	@Column(columnDefinition="Decimal(15,2)")
	private double iznosZaUplatu;
	@Column(length = 18)
	private String uplataNaRacun;
	private Date datumValute;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<StavkaFakture> stavke;
	public Faktura() {
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
	public String getNazivDobavljaca() {
		return nazivDobavljaca;
	}
	public void setNazivDobavljaca(String nazivDobavljaca) {
		this.nazivDobavljaca = nazivDobavljaca;
	}
	public String getAdresaDobavljaca() {
		return adresaDobavljaca;
	}
	public void setAdresaDobavljaca(String adresaDobavljaca) {
		this.adresaDobavljaca = adresaDobavljaca;
	}
	public String getPibDobavljaca() {
		return pibDobavljaca;
	}
	public void setPibDobavljaca(String pibDobavljaca) {
		this.pibDobavljaca = pibDobavljaca;
	}
	public String getNazivKupca() {
		return nazivKupca;
	}
	public void setNazivKupca(String nazivKupca) {
		this.nazivKupca = nazivKupca;
	}
	public String getAdresaKupca() {
		return adresaKupca;
	}
	public void setAdresaKupca(String adresaKupca) {
		this.adresaKupca = adresaKupca;
	}
	public String getPibKupca() {
		return pibKupca;
	}
	public void setPibKupca(String pibKupca) {
		this.pibKupca = pibKupca;
	}
	public int getBrojRacuna() {
		return brojRacuna;
	}
	public void setBrojRacuna(int brojRacuna) {
		this.brojRacuna = brojRacuna;
	}
	public Date getDatumRacuna() {
		return datumRacuna;
	}
	public void setDatumRacuna(Date datumRacuna) {
		this.datumRacuna = datumRacuna;
	}
	public double getVrednostRobe() {
		return vrednostRobe;
	}
	public void setVrednostRobe(double vrednostRobe) {
		this.vrednostRobe = vrednostRobe;
	}
	public double getVrednostUsluga() {
		return vrednostUsluga;
	}
	public void setVrednostUsluga(double vrednostUsluga) {
		this.vrednostUsluga = vrednostUsluga;
	}
	public double getUkupnoRobeIUsluge() {
		return ukupnoRobeIUsluge;
	}
	public void setUkupnoRobeIUsluge(double ukupnoRobeIUsluge) {
		this.ukupnoRobeIUsluge = ukupnoRobeIUsluge;
	}
	public double getUkupanRabat() {
		return ukupanRabat;
	}
	public void setUkupanRabat(double ukupanRabat) {
		this.ukupanRabat = ukupanRabat;
	}
	public double getUkupanPorez() {
		return ukupanPorez;
	}
	public void setUkupanPorez(double ukupanPorez) {
		this.ukupanPorez = ukupanPorez;
	}
	public String getOznakaValute() {
		return oznakaValute;
	}
	public void setOznakaValute(String oznakaValute) {
		this.oznakaValute = oznakaValute;
	}
	public double getIznosZaUplatu() {
		return iznosZaUplatu;
	}
	public void setIznosZaUplatu(double iznosZaUplatu) {
		this.iznosZaUplatu = iznosZaUplatu;
	}
	public String getUplataNaRacun() {
		return uplataNaRacun;
	}
	public void setUplataNaRacun(String uplataNaRacun) {
		this.uplataNaRacun = uplataNaRacun;
	}
	public Date getDatumValute() {
		return datumValute;
	}
	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}
	public List<StavkaFakture> getStavke() {
		return stavke;
	}
	public void setStavke(List<StavkaFakture> stavke) {
		this.stavke = stavke;
	}
}
