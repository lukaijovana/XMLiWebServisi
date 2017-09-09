package rs.ac.uns.ftn.informatika.servis;

import java.util.List;

import rs.ac.uns.ftn.informatika.domen.NalogBanke;

public interface BankaServis {
	public List<NalogBanke> findAll();
	public NalogBanke findById(Long id);
	public void delete(Long id);
	public NalogBanke save(NalogBanke nalog);
}
