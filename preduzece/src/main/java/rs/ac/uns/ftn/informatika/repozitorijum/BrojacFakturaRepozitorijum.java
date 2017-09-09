package rs.ac.uns.ftn.informatika.repozitorijum;

import java.util.List;

import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.domen.BrojacFaktura;
@org.springframework.stereotype.Repository
public interface BrojacFakturaRepozitorijum extends Repository<BrojacFaktura, Long>{
	public List<BrojacFaktura> findAll();
	public BrojacFaktura findById(Long id);
	public void delete(Long id);
	public BrojacFaktura save(BrojacFaktura counter);
}