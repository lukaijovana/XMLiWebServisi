package bankApp;

import java.util.List;

import rs.ac.uns.ftn.informatika.domen.NalogKorisnikaEntity;

public interface KorisnickiNalogService {
	public List<NalogKorisnikaEntity> findAll();
	public NalogKorisnikaEntity findById(Long id);
	public void delete(Long id);
	public NalogKorisnikaEntity save(NalogKorisnikaEntity nalog);
}
