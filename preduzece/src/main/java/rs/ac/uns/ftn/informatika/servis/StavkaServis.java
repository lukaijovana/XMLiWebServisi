package rs.ac.uns.ftn.informatika.servis;

import java.util.List;

import rs.ac.uns.ftn.informatika.domen.StavkaFakture;

public interface StavkaServis {
	List<StavkaFakture> findAll();
	StavkaFakture findById(Long id);
	void delete(Long id);
	StavkaFakture save(StavkaFakture stavka);
}
