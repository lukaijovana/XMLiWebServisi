package bankApp;

import java.util.List;

import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.domen.NalogZaPlacanjeEntity;
public interface NalogRepozitorijum extends Repository<NalogZaPlacanjeEntity, Long>{
	public List<NalogZaPlacanjeEntity> findAll();
	public NalogZaPlacanjeEntity findById(Long id);
	public void delete(Long id);
	public NalogZaPlacanjeEntity save(NalogZaPlacanjeEntity nalog);
}
