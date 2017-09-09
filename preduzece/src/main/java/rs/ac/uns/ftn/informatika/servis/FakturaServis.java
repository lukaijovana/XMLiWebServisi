package rs.ac.uns.ftn.informatika.servis;

import java.util.List;

import rs.ac.uns.ftn.informatika.domen.Faktura;

public interface FakturaServis {
	List<Faktura> findAll();
	Faktura findById(Long id);
	void delete(Long id);
	Faktura save(Faktura faktura);
}
