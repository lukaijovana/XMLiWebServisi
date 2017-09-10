package bankApp;

import java.util.List;

import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.domen.NalogKorisnikaEntity;
import rs.ac.uns.ftn.informatika.nalogzakorisnike.NalogKorisnika;

public interface KorisnickiNalogRepository extends Repository<NalogKorisnikaEntity, Long>{
	public List<NalogKorisnikaEntity> findAll();
	public NalogKorisnikaEntity findById(Long id);
	public void delete(Long id);
	public NalogKorisnikaEntity save(NalogKorisnikaEntity nalog);
}
