package rs.ac.uns.ftn.informatika.repozitorijum;

import java.util.List;

import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.domen.Faktura;
@org.springframework.stereotype.Repository
public interface FakturaRepozitorijum extends Repository<Faktura, Long>{
	public List<Faktura> findAll();
	public Faktura findById(Long id);
	public void delete(Long id);
	public Faktura save(Faktura faktura);
}
