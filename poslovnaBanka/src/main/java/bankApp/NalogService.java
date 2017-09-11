package bankApp;

import java.util.List;

import rs.ac.uns.ftn.informatika.domen.NalogZaPrenosEntity;

public interface NalogService {
	public List<NalogZaPrenosEntity> findAll();
	public NalogZaPrenosEntity findById(Long id);
	public void delete(Long id);
	public NalogZaPrenosEntity save(NalogZaPrenosEntity nalog);
	public NalogZaPrenosEntity findByIdPoruke(String id);
}