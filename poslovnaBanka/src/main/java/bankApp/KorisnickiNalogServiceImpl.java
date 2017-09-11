package bankApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.domen.NalogKorisnikaEntity;

@Service
public class KorisnickiNalogServiceImpl implements KorisnickiNalogService {
	@Autowired
	private KorisnickiNalogRepository korisnickiNalogRepository;

	@Override
	public List<NalogKorisnikaEntity> findAll() {
		return korisnickiNalogRepository.findAll();
	}

	@Override
	public NalogKorisnikaEntity findById(Long id) {
		return korisnickiNalogRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		korisnickiNalogRepository.delete(id);
	}

	@Override
	public NalogKorisnikaEntity save(NalogKorisnikaEntity nalog) {
		return korisnickiNalogRepository.save(nalog);
	}
}
