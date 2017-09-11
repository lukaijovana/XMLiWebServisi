package bankApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.domen.NalogZaPlacanjeEntity;
import rs.ac.uns.ftn.informatika.nalogzaplacanje.Nalog;

@Service
public class NalogServisImpl implements NalogServis{
	@Autowired
	private NalogRepozitorijum nalogRepozitorijum;

	@Override
	public List<NalogZaPlacanjeEntity> findAll() {
		// TODO Auto-generated method stub
		return nalogRepozitorijum.findAll();
	}

	@Override
	public NalogZaPlacanjeEntity findById(Long id) {
		// TODO Auto-generated method stub
		return nalogRepozitorijum.findById(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		nalogRepozitorijum.delete(id);
	}

	@Override
	public NalogZaPlacanjeEntity save(NalogZaPlacanjeEntity nalog) {
		// TODO Auto-generated method stub
		return nalogRepozitorijum.save(nalog);
	}

	@Override
	public NalogZaPlacanjeEntity findByIdPoruke(String id) {
		// TODO Auto-generated method stub
		List<NalogZaPlacanjeEntity> nalozi = nalogRepozitorijum.findAll();
		for(NalogZaPlacanjeEntity n: nalozi) {
			if(n.getIdPoruke().equals(id)) {
				return n;
			}
		}
		return null;
	}
}
