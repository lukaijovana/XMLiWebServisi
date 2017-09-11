package bankApp;

import java.util.List;

import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.domen.BankarskiNalogEntity;
@org.springframework.stereotype.Repository
public interface BankarskiNalogRepozitorijum extends Repository<BankarskiNalogEntity, Long>{
	public List<BankarskiNalogEntity> findAll();
	public BankarskiNalogEntity findById(Long id);
	public void delete(Long id);
	public BankarskiNalogEntity save(BankarskiNalogEntity nalog);
}
