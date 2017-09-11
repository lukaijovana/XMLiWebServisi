package bankApp;

import java.util.List;

import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.domen.NalogZaPrenosEntity;
public interface NalogRepository extends Repository<NalogZaPrenosEntity, Long>{
	public List<NalogZaPrenosEntity> findAll();
	public NalogZaPrenosEntity findById(Long id);
	public void delete(Long id);
	public NalogZaPrenosEntity save(NalogZaPrenosEntity nalog);
}
