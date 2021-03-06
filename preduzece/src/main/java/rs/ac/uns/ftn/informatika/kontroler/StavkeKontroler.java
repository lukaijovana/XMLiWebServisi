package rs.ac.uns.ftn.informatika.kontroler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.domen.StavkaFakture;
import rs.ac.uns.ftn.informatika.servis.StavkaServis;
@RestController
@RequestMapping("/stavke")
public class StavkeKontroler {
	@Autowired
	private StavkaServis stavkaServis;
	
	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity createStavka(@RequestBody StavkaFakture stavka) {
		System.out.println(stavka);
		StavkaFakture sfIn = stavkaServis.save(stavka);
		if(sfIn != null) {
			return new ResponseEntity<>(sfIn, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
