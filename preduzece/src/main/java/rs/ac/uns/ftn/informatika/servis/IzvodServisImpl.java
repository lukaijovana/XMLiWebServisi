package rs.ac.uns.ftn.informatika.servis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.domen.Izvod;
import rs.ac.uns.ftn.informatika.repozitorijum.IzvodRepozitorijum;

@Service
public class IzvodServisImpl implements IzvodServis {
	
	@Autowired
	IzvodRepozitorijum izvodRepozitorijum;

	@Override
	public List<Izvod> findAll() {
		// TODO Auto-generated method stub
		return izvodRepozitorijum.findAll();
	}

	@Override
	public Izvod findById(Long id) {
		// TODO Auto-generated method stub
		return izvodRepozitorijum.findById(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		izvodRepozitorijum.delete(id);
	}

	@Override
	public Izvod save(Izvod izvod) {
		// TODO Auto-generated method stub
		return izvodRepozitorijum.save(izvod);
	}

}
