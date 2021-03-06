package rs.ac.uns.ftn.informatika.repozitorijum;

import java.util.List;

import org.springframework.data.repository.Repository;
import rs.ac.uns.ftn.informatika.domen.StavkaIzvoda;
@org.springframework.stereotype.Repository
public interface StavkaIzvodaRepozitorijum extends Repository<StavkaIzvoda, Long>{
	public List<StavkaIzvoda> findAll();
	public StavkaIzvoda findById(Long id);
	public void delete(Long id);
	public StavkaIzvoda save(StavkaIzvoda stavkaIzvoda);
}