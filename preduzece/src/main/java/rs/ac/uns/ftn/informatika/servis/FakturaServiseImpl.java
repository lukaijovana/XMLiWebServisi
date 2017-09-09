package rs.ac.uns.ftn.informatika.servis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.domen.Faktura;
import rs.ac.uns.ftn.informatika.repozitorijum.FakturaRepozitorijum;
@Service
public class FakturaServiseImpl implements FakturaServis {
	@Autowired
	FakturaRepozitorijum fakturaRepozitorijum;
	
	@Override
	public List<Faktura> findAll() {
		return fakturaRepozitorijum.findAll();
	}

	@Override
	public Faktura findById(Long id) {
		return fakturaRepozitorijum.findById(id);
	}

	@Override
	public void delete(Long id) {
		fakturaRepozitorijum.delete(id);
	}

	@Override
	public Faktura save(Faktura faktura) {
		return fakturaRepozitorijum.save(faktura);
	}

}
