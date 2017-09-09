package rs.ac.uns.ftn.informatika.servis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.domen.BrojacFaktura;
import rs.ac.uns.ftn.informatika.repozitorijum.BrojacFakturaRepozitorijum;
@Service
public class BrojacFakturaServisImpl implements BrojacFakturaServis{
	@Autowired
	BrojacFakturaRepozitorijum brojacFakturaRepozitorijum;
	@Override
	public List<BrojacFaktura> findAll() {
		// TODO Auto-generated method stub
		return brojacFakturaRepozitorijum.findAll();
	}

	@Override
	public BrojacFaktura findById(Long id) {
		// TODO Auto-generated method stub
		return brojacFakturaRepozitorijum.findById(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		brojacFakturaRepozitorijum.delete(id);
	}

	@Override
	public BrojacFaktura save(BrojacFaktura counter) {
		// TODO Auto-generated method stub
		return brojacFakturaRepozitorijum.save(counter);
	}

	@Override
	public Long getLast() {
		// TODO Auto-generated method stub
		List<BrojacFaktura> fakture = findAll();
		Long max = null;
		if(fakture != null && !fakture.isEmpty()) {
			max = fakture.get(0).getId();
			for(BrojacFaktura c: fakture) {
				if(c.getId() > max) {
					max = c.getId();
				}
			}
		}
		return max;
	}
	
	

}
