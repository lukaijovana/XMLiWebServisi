package rs.ac.uns.ftn.informatika.servis;

import java.util.List;

import rs.ac.uns.ftn.informatika.domen.StavkaIzvoda;

public interface StavkaIzvodaServis {
	List<StavkaIzvoda> findAll();
	StavkaIzvoda findById(Long id);
	void delete(Long id);
	StavkaIzvoda save(StavkaIzvoda stavkaIzvoda);
}
