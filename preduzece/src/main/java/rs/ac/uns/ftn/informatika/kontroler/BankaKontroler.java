package rs.ac.uns.ftn.informatika.kontroler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.client.core.WebServiceTemplate;

import rs.ac.uns.ftn.informatika.nalogzakorisnike.KreirajKorisnickiNalogZahtev;
import rs.ac.uns.ftn.informatika.nalogzakorisnike.KreirajKorisnickiNalogOdg;
import rs.ac.uns.ftn.informatika.nalogzakorisnike.KorisnickiNalog;
import rs.ac.uns.ftn.informatika.domen.NalogBanke;
import rs.ac.uns.ftn.informatika.servis.BankaServis;

@RestController
@RequestMapping("/banka")
public class BankaKontroler {
	@Autowired
	private BankaServis bankaServis;
	
	private void posaljiBanci(NalogBanke nalog) throws Exception {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan(ClassUtils.getPackageName(KreirajKorisnickiNalogZahtev.class));
        marshaller.afterPropertiesSet();
		WebServiceTemplate ws = new WebServiceTemplate(marshaller);
		KreirajKorisnickiNalogZahtev request = new KreirajKorisnickiNalogZahtev();
		KorisnickiNalog korisnicki = new KorisnickiNalog();
		korisnicki.setPortKorisnika(nalog.getPortKorisnika());
		korisnicki.setPortBanke(nalog.getPortBanke());
		korisnicki.setRacun(nalog.getRacun());
		korisnicki.setNaziv(nalog.getNaziv());
		korisnicki.setPib(nalog.getPib());
		double s = 200000;
		double s1 = 0;
		korisnicki.setRezervisanaSuma(s1);
		korisnicki.setStanjeNaRacunu(s);
		
		request.setKorisnickiNalog(korisnicki);
	    KreirajKorisnickiNalogOdg resp = (KreirajKorisnickiNalogOdg) ws.marshalSendAndReceive("http://localhost:" + nalog.getPortBanke() 
	               + "/ws", request);
	     System.out.println(resp.getOdgovor());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity createNalog(@RequestBody String port) {
		//kreiraj nov nalog
		NalogBanke nalog = new NalogBanke();
		nalog.setPortKorisnika(port);
		int portBanke = Integer.parseInt(port) + 10;
		nalog.setPortBanke(String.valueOf(portBanke));
		System.out.println(nalog.getPortBanke());
		nalog.setNaziv("Korisnik " + port);
		nalog.setAdresa("Ulica Maj " + port);
		//8092 sifra banke 092
		String sifraBanke = nalog.getPortBanke().substring(1);
		//09x-000000000808x-12
		nalog.setRacun(sifraBanke + "000000000" + port + "12");
		String sifraKorisnika = nalog.getPortKorisnika().substring(1);
		//port 8085 085
		//111-09x-08x
		nalog.setPib("111-" + sifraBanke + "-" + sifraKorisnika);
		NalogBanke nalogIn = bankaServis.save(nalog);
		try {
			posaljiBanci(nalog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(nalogIn != null) {
			return new ResponseEntity<>(nalogIn, HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	private ResponseEntity getAllNaloge() {
		List<NalogBanke> nalozi = bankaServis.findAll();
		if(nalozi != null) {
			return new ResponseEntity<>(nalozi, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
