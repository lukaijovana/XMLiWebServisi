package rs.ac.uns.ftn.informatika.repozitorijum;

import java.util.List;

import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.domen.NalogBanke;
@org.springframework.stereotype.Repository
public interface BankaRepozitorijum extends Repository<NalogBanke, Long>{
	public List<NalogBanke> findAll();
	public NalogBanke findById(Long id);
	public void delete(Long id);
	public NalogBanke save(NalogBanke nalog);
}
