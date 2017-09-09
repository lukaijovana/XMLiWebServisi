package rs.ac.uns.ftn.informatika.servis;

import java.util.List;

import rs.ac.uns.ftn.informatika.domen.Partner;

public interface PartnerServis {
	public List<Partner> findAll();
	public Partner findById(Long id);
	public void delete(Long id);
	public Partner save(Partner partner);
	public Partner findByPib(String pib);
}
