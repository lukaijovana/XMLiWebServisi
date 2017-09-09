package rs.ac.uns.ftn.informatika.kontroler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.informatika.servis.BrojacFakturaServis;
@RestController
@RequestMapping("/counterFaktura")
public class BrojacFakturaKontroler {
	@Autowired
	private BrojacFakturaServis counterFakturaService;
	@RequestMapping(method = RequestMethod.GET)
	private ResponseEntity getLast() {
		Long last = counterFakturaService.getLast();
		if(last == null) {
			last = new Long(0);
		}
		return new ResponseEntity<>(last, HttpStatus.OK);
	}
}
