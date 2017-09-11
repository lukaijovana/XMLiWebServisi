package centralnaBankaApp;

import java.util.List;

import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.domen.NalogBankeEntity;
@org.springframework.stereotype.Repository
public interface NalogBankeRepository extends Repository<NalogBankeEntity, Long>{
	public List<NalogBankeEntity> findAll();
	public NalogBankeEntity findById(Long id);
	public void delete(Long id);
	public NalogBankeEntity save(NalogBankeEntity nalog);
}
