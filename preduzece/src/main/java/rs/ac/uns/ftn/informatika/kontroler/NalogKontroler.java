package rs.ac.uns.ftn.informatika.kontroler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.client.core.WebServiceTemplate;

import rs.ac.uns.ftn.informatika.nalogzaplacanje.PrimiNalog;
import rs.ac.uns.ftn.informatika.nalogzaplacanje.Nalog;
import rs.ac.uns.ftn.informatika.nalogzaplacanje.PosaljiNalog;
import rs.ac.uns.ftn.informatika.domen.NalogBanke;
import rs.ac.uns.ftn.informatika.domen.Faktura;
import rs.ac.uns.ftn.informatika.servis.BankaServis;
import rs.ac.uns.ftn.informatika.servis.FakturaServis;

@RestController
@RequestMapping("/nalozi")
public class NalogKontroler {
	@Autowired
	private FakturaServis fakturaService;
	@Autowired
	private BankaServis bankaService;
	
	private void posaljiNalog(Nalog nalog) throws Exception {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan(ClassUtils.getPackageName(PosaljiNalog.class));
        marshaller.afterPropertiesSet();
		WebServiceTemplate ws = new WebServiceTemplate(marshaller);
		PosaljiNalog request = new PosaljiNalog();
		String port = null;
		List<NalogBanke> nalozi = bankaService.findAll();
		for(NalogBanke bn: nalozi){
			if(bn.getRacun().equals(nalog.getRacunDuznika())) {
				port = bn.getPortBanke();
			}
		}
		request.setNalogZaPlacanje(nalog);
	    PrimiNalog resp = (PrimiNalog) ws.marshalSendAndReceive("http://localhost:" + port 
	               + "/ws", request);
	     System.out.println(resp);
	}
	private void generisiNalog(Faktura faktura, boolean hitno) throws DatatypeConfigurationException {
		Nalog nalog = new Nalog();
		nalog.setIDPoruke(faktura.getIdPoruke());
		nalog.setDuznik(faktura.getNazivKupca());
		nalog.setSvrhaPlacanja("Uplata po fakturi");
		nalog.setPrimalac(faktura.getNazivDobavljaca());
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(faktura.getDatumRacuna());
		XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		nalog.setDatumNaloga(date2);
		GregorianCalendar c1 = new GregorianCalendar();
		c1.setTime(faktura.getDatumValute());
		XMLGregorianCalendar date3 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c1);
		nalog.setDatumValute(date3);
		
		//racun duznika iz firme ocitati
		List<NalogBanke> nalozi = bankaService.findAll();
		for(NalogBanke bn: nalozi) {
			if(bn.getPib().equals(faktura.getPibKupca())) {
				nalog.setRacunDuznika(bn.getRacun());
				break;
			}
		}
		
		nalog.setModelZaduzenja(BigInteger.valueOf(87));
		nalog.setPNBZaduzenja("2432324");
		nalog.setRacunPrimaoca(faktura.getUplataNaRacun());
		nalog.setModelOdobrenja(BigInteger.valueOf(97));
		nalog.setPNBOdobrenja("4324325");
		nalog.setIznos(BigDecimal.valueOf(faktura.getIznosZaUplatu()));
		nalog.setOznakaValute(faktura.getOznakaValute());
		nalog.setHitno(hitno);
		System.out.println("da li je hitno " + hitno);
		try {
			posaljiNalog(nalog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	private ResponseEntity createNalog(@PathVariable Long id, @RequestBody boolean hitno) {
		Faktura faktura = fakturaService.findById(id);
		try {
			generisiNalog(faktura, hitno);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
