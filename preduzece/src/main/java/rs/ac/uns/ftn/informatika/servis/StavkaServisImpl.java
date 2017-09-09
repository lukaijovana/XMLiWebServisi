package rs.ac.uns.ftn.informatika.servis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.domen.StavkaFakture;
import rs.ac.uns.ftn.informatika.repozitorijum.StavkaRepozitorijum;

@Service
public class StavkaServisImpl implements StavkaServis {
	@Autowired
	StavkaRepozitorijum stavkaRepozitorijum;

	@Override
	public List<StavkaFakture> findAll() {
		return stavkaRepozitorijum.findAll();
	}

	@Override
	public StavkaFakture findById(Long id) {
		return stavkaRepozitorijum.findById(id);
	}

	@Override
	public void delete(Long id) {
		stavkaRepozitorijum.delete(id);
	}

	@Override
	public StavkaFakture save(StavkaFakture stavka) {
		return stavkaRepozitorijum.save(stavka);
	}
}
