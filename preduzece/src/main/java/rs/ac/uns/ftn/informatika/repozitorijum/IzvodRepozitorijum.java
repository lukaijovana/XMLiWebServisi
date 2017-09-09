package rs.ac.uns.ftn.informatika.repozitorijum;

import java.util.List;

import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.domen.Izvod;
@org.springframework.stereotype.Repository
public interface IzvodRepozitorijum extends Repository<Izvod, Long>{
	public List<Izvod> findAll();
	public Izvod findById(Long id);
	public void delete(Long id);
	public Izvod save(Izvod izvod);
}
