package bankApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.domen.BankarskiNalogEntity;

@Service
public class BankarskiNalogServisImpl implements BankarskiNalogServis {
	
	@Autowired
	private BankarskiNalogRepozitorijum bankarskiNalogRepozitorijum;
	
	@Override
	public List<BankarskiNalogEntity> findAll() {
		return bankarskiNalogRepozitorijum.findAll();
	}

	@Override
	public BankarskiNalogEntity findById(Long id) {
		return bankarskiNalogRepozitorijum.findById(id);
	}

	@Override
	public void delete(Long id) {
		bankarskiNalogRepozitorijum.delete(id);
	}

	@Override
	public BankarskiNalogEntity save(BankarskiNalogEntity nalog) {
		return bankarskiNalogRepozitorijum.save(nalog);
	}

}
