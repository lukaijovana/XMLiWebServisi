package rs.ac.uns.ftn.informatika.domen;

import java.util.Date;

public class Zahtev {

	private Date datum;
	private String racun;
	private int rbr;
	
	public Zahtev() {
		super();
	}
	
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public String getRacun() {
		return racun;
	}
	public void setRacun(String racun) {
		this.racun = racun;
	}
	public int getRbr() {
		return rbr;
	}
	public void setRbr(int rbr) {
		this.rbr = rbr;
	}
	
	
}
