package rs.ac.uns.ftn.informatika.servis;

import java.util.List;
import rs.ac.uns.ftn.informatika.domen.Izvod;

public interface IzvodServis {
	List<Izvod> findAll();
	Izvod findById(Long id);
	void delete(Long id);
	Izvod save(Izvod izvod);
}
