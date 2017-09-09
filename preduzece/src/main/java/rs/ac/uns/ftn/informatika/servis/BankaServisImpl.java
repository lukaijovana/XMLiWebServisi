package rs.ac.uns.ftn.informatika.servis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.domen.NalogBanke;
import rs.ac.uns.ftn.informatika.repozitorijum.BankaRepozitorijum;
@Service
public class BankaServisImpl implements BankaServis {
	@Autowired
	BankaRepozitorijum bankaRepository;
	@Override
	public List<NalogBanke> findAll() {
		return bankaRepository.findAll();
	}

	@Override
	public NalogBanke findById(Long id) {
		return bankaRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		bankaRepository.delete(id);
	}

	@Override
	public NalogBanke save(NalogBanke nalog) {
		return bankaRepository.save(nalog);
	}

}
