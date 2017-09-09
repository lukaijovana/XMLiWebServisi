package rs.ac.uns.ftn.informatika.servis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.domen.StavkaIzvoda;
import rs.ac.uns.ftn.informatika.repozitorijum.StavkaIzvodaRepozitorijum;
@Service
public class StavkaIzvodaServisImpl implements StavkaIzvodaServis {
	
	@Autowired
	StavkaIzvodaRepozitorijum stavkaIzvodaRepozitorijum;

	@Override
	public List<StavkaIzvoda> findAll() {
		// TODO Auto-generated method stub
		return stavkaIzvodaRepozitorijum.findAll();
	}

	@Override
	public StavkaIzvoda findById(Long id) {
		// TODO Auto-generated method stub
		return stavkaIzvodaRepozitorijum.findById(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		stavkaIzvodaRepozitorijum.delete(id);
	}

	@Override
	public StavkaIzvoda save(StavkaIzvoda stavkaIzvoda) {
		// TODO Auto-generated method stub
		return stavkaIzvodaRepozitorijum.save(stavkaIzvoda);
	}

}
