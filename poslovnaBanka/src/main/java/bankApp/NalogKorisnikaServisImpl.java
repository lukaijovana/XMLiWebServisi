package bankApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.domen.NalogKorisnikaEntity;

@Service
public class NalogKorisnikaServisImpl implements NalogKorisnikaServis {
	@Autowired
	private NalogKorisnikaRepozitorijum nalogKorisnikaRepozitorijum;

	@Override
	public List<NalogKorisnikaEntity> findAll() {
		return nalogKorisnikaRepozitorijum.findAll();
	}

	@Override
	public NalogKorisnikaEntity findById(Long id) {
		return nalogKorisnikaRepozitorijum.findById(id);
	}

	@Override
	public void delete(Long id) {
		nalogKorisnikaRepozitorijum.delete(id);
	}

	@Override
	public NalogKorisnikaEntity save(NalogKorisnikaEntity nalog) {
		return nalogKorisnikaRepozitorijum.save(nalog);
	}
}
