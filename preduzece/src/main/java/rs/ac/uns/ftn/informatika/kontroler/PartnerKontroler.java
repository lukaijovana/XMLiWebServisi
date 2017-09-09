package rs.ac.uns.ftn.informatika.kontroler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.domen.Partner;
import rs.ac.uns.ftn.informatika.servis.PartnerServis;

@RestController
@RequestMapping("/partneri")
public class PartnerKontroler {
	@Autowired
	private PartnerServis poslovniPartnerService;
	
	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity dodajPartnera(@RequestBody Partner partner) {
		Partner pIn = poslovniPartnerService.save(partner);
		if(pIn != null) {
			return new ResponseEntity<>(pIn, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	private ResponseEntity getAll() {
		List<Partner> partneri = poslovniPartnerService.findAll();
		return new ResponseEntity<>(partneri, HttpStatus.OK);
	}
}
