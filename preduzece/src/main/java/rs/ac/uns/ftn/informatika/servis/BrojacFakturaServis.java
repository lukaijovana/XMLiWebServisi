package rs.ac.uns.ftn.informatika.servis;

import java.util.List;

import rs.ac.uns.ftn.informatika.domen.BrojacFaktura;

public interface BrojacFakturaServis {
	public List<BrojacFaktura> findAll();
	public BrojacFaktura findById(Long id);
	public void delete(Long id);
	public BrojacFaktura save(BrojacFaktura counter);
	public Long getLast();
}
