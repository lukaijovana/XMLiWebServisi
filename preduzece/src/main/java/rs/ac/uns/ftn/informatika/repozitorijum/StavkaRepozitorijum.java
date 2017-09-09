package rs.ac.uns.ftn.informatika.repozitorijum;

import java.util.List;

import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.domen.StavkaFakture;
@org.springframework.stereotype.Repository
public interface StavkaRepozitorijum extends Repository<StavkaFakture, Long>{
	List<StavkaFakture> findAll();
	StavkaFakture findById(Long id);
	void delete(Long id);
	StavkaFakture save(StavkaFakture stavka);
}
