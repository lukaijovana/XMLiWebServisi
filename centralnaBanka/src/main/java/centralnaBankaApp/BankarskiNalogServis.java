package centralnaBankaApp;

import java.util.List;

import rs.ac.uns.ftn.informatika.domen.BankarskiNalogEntity;

public interface BankarskiNalogServis {
	public List<BankarskiNalogEntity> findAll();
	public BankarskiNalogEntity findById(Long id);
	public void delete(Long id);
	public BankarskiNalogEntity save(BankarskiNalogEntity nalog);
}
