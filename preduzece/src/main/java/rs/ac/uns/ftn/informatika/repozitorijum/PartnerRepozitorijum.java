package rs.ac.uns.ftn.informatika.repozitorijum;

import java.util.List;

import org.springframework.data.repository.Repository;

import rs.ac.uns.ftn.informatika.domen.Partner;
@org.springframework.stereotype.Repository
public interface PartnerRepozitorijum extends Repository<Partner, Long>{
	public List<Partner> findAll();
	public Partner findById(Long id);
	public void delete(Long id);
	public Partner save(Partner partner);
	public Partner findByPib(String pib);
}
