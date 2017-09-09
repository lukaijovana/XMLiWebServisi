package rs.ac.uns.ftn.informatika.servis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.domen.Partner;
import rs.ac.uns.ftn.informatika.repozitorijum.PartnerRepozitorijum;

@Service
public class PartnerServisImpl implements PartnerServis{
	@Autowired
	PartnerRepozitorijum partnerRepozitorijum;
	@Override
	public List<Partner> findAll() {
		return partnerRepozitorijum.findAll();
	}

	@Override
	public Partner findById(Long id) {
		return partnerRepozitorijum.findById(id);
	}

	@Override
	public void delete(Long id) {
		partnerRepozitorijum.delete(id);
	}

	@Override
	public Partner save(Partner partner) {
		return partnerRepozitorijum.save(partner);
	}

	@Override
	public Partner findByPib(String pib) {
		return partnerRepozitorijum.findByPib(pib);
	}

}
