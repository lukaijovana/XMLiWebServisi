package rs.ac.uns.ftn.informatika.kontroler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import rs.ac.uns.ftn.informatika.domen.BrojacFaktura;
import rs.ac.uns.ftn.informatika.domen.Faktura;
import rs.ac.uns.ftn.informatika.domen.Partner;
import rs.ac.uns.ftn.informatika.servis.BrojacFakturaServis;
import rs.ac.uns.ftn.informatika.servis.FakturaServis;
import rs.ac.uns.ftn.informatika.servis.PartnerServis;

@RestController
@RequestMapping("/fakture")
public class FaktureKontroler {
	@Autowired
	private FakturaServis fakturaServis;
	@Autowired
	private PartnerServis partnerServis;
	@Autowired
	private BrojacFakturaServis counterFakturaService;
	
	@RequestMapping(value="/rest", method = RequestMethod.POST)
	private ResponseEntity createFakturaRest(@RequestBody Faktura faktura) {
		Faktura fIn = fakturaServis.save(faktura);
		RestTemplate restTemplate = new RestTemplate();
		if(fIn != null) {
			return new ResponseEntity<>(fIn, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity createFaktura(@RequestBody Faktura faktura) {
		RestTemplate restTemplate = new RestTemplate();
		Partner partner = partnerServis.findByPib(faktura.getPibKupca());
		BrojacFaktura cpf = new BrojacFaktura();
		counterFakturaService.save(cpf);
		ResponseEntity<Faktura> response = restTemplate
        		  .exchange("http://localhost:" + partner.getPort() + "/fakture/rest", HttpMethod.POST, new HttpEntity<>(faktura), Faktura.class);
        return response;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	private ResponseEntity getFakture() {
		List<Faktura> fakture = fakturaServis.findAll();
		if(fakture != null) {
			return new ResponseEntity<>(fakture, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
