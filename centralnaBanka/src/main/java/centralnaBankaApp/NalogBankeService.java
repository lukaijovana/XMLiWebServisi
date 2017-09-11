package centralnaBankaApp;

import java.util.List;

import rs.ac.uns.ftn.informatika.domen.NalogBankeEntity;

public interface NalogBankeService {
	public List<NalogBankeEntity> findAll();
	public NalogBankeEntity findById(Long id);
	public void delete(Long id);
	public NalogBankeEntity save(NalogBankeEntity nalog);
}
