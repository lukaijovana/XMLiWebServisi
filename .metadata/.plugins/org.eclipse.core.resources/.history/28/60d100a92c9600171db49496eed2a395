package rs.ac.uns.ftn.informatika.kontroler;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.client.core.WebServiceTemplate;

import rs.ac.uns.ftn.informatika.zahtevizvod.PreuzetZahtev;
import rs.ac.uns.ftn.informatika.zahtevizvod.ZahtevIzvod;
import rs.ac.uns.ftn.informatika.zahtevizvod.PoslatZahtev;
import rs.ac.uns.ftn.informatika.domen.Zahtev;
import rs.ac.uns.ftn.informatika.servis.BankaServis;
import rs.ac.uns.ftn.informatika.servis.IzvodServis;
import rs.ac.uns.ftn.informatika.servis.StavkaIzvodaServis;
import rs.ac.uns.ftn.informatika.domen.NalogBanke;
import rs.ac.uns.ftn.informatika.domen.Izvod;
import rs.ac.uns.ftn.informatika.domen.StavkaIzvoda;
import rs.ac.uns.ftn.informatika.odgovorizvod.PreuzetOdgovor;
import rs.ac.uns.ftn.informatika.odgovorizvod.PresekStavki;

@RestController
@RequestMapping("/izvodi")
public class IzvodiKontroler {
	
	@Autowired
	private IzvodServis izvodServis;
	@Autowired
	private BankaServis bankaServis;
	@Autowired
	private StavkaIzvodaServis stavkaServis;
	
	private Izvod posaljiZahtev(Zahtev zahtev) throws Exception {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan(ClassUtils.getPackageName(PoslatZahtev.class));
        marshaller.afterPropertiesSet();
		WebServiceTemplate ws = new WebServiceTemplate(marshaller);
		PoslatZahtev request = new PoslatZahtev();
		String port = null;
		List<NalogBanke> nalozi = bankaServis.findAll();
		for(NalogBanke bn: nalozi){
			if(bn.getRacun().equals(zahtev.getRacun())) {
				port = bn.getPortBanke();
			}
		}
		ZahtevIzvod izvodZahtev = new ZahtevIzvod();
		
		izvodZahtev.setBrojRacuna(zahtev.getRacun());
		izvodZahtev.setRBRPreseka(zahtev.getRbr());
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(zahtev.getDatum());
		XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		
		izvodZahtev.setDatum(date2);
		
		request.setIzvodZahtev(izvodZahtev);
	    PreuzetOdgovor resp = (PreuzetOdgovor) ws.marshalSendAndReceive("http://localhost:" + port 
	               + "/ws", request);
	     System.out.println(resp.getOdgovor().getPrethodnoStanje() + " " + resp.getOdgovor().getNovoStanje());
	     
	     //Parsiraj odgovor u model i upisi sve sto treba
	     
	     //header
	     Izvod izvod = new Izvod();
	     izvod.setBrojPreseka(resp.getOdgovor().getPresekBroj());
	     izvod.setBrojPromenaNaTeret(resp.getOdgovor().getBrPromenaNaTeret());
	     izvod.setBrojPromenaUKorist(resp.getOdgovor().getBrPromenaUKorist());
	     izvod.setBrojRacuna(resp.getOdgovor().getBrojRacuna());
	     izvod.setDatumNaloga(resp.getOdgovor().getDatum().toGregorianCalendar().getTime());
	     izvod.setNovoStanje(resp.getOdgovor().getNovoStanje().doubleValue());
	     izvod.setPrethodnoStanje(resp.getOdgovor().getPrethodnoStanje().doubleValue());
	     izvod.setUkupnoNaTeret(resp.getOdgovor().getUkupnoNaTeret().doubleValue());
	     izvod.setUkupnoUKorist(resp.getOdgovor().getUkupnoUKorist().doubleValue());
	     
	     List<StavkaIzvoda> stavkeEntiteta = new ArrayList<StavkaIzvoda>();
	     
	     //stavke
	     List<PresekStavki> stavkeXML = resp.getOdgovor().getStavka();
	     
	     for (PresekStavki stavkaPreseka : stavkeXML) {
			StavkaIzvoda stIzvoda = new StavkaIzvoda();
			
			stIzvoda.setDatumNaloga(stavkaPreseka.getDatumNaloga().toGregorianCalendar().getTime());
			stIzvoda.setDatumValute(stavkaPreseka.getDatumValute().toGregorianCalendar().getTime());
			stIzvoda.setDuznikNalogodavac(stavkaPreseka.getDuznik());
			stIzvoda.setIznos(stavkaPreseka.getIznos().doubleValue());
			stIzvoda.setModelOdobrenja(stavkaPreseka.getModelOdobrenja());
			stIzvoda.setModelZaduzenja(stavkaPreseka.getModelZaduzenja());
			stIzvoda.setPozivNaBrojOdobrenja(stavkaPreseka.getPNBOdobrenja());
			stIzvoda.setPozivNaBrojZaduzenja(stavkaPreseka.getPNBZaduzenja());
			stIzvoda.setPrimalacPoverilac(stavkaPreseka.getPrimalac());
			stIzvoda.setRacunDuznika(stavkaPreseka.getRacunDuznika());
			stIzvoda.setRacunPoverioca(stavkaPreseka.getRacunPoverioca());
			stIzvoda.setSmer(stavkaPreseka.getSmer());
			stIzvoda.setSvrhaPlacanja(stavkaPreseka.getSvrhaPlacanja());
			//Upisi u bazu i odma dodaj taj element na temp listu stavki za izvod
			stavkeEntiteta.add(stavkaServis.save(stIzvoda));
	     }
	     
	     izvod.setStavke(stavkeEntiteta);
	     
	     return izvodServis.save(izvod);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity createZahtev(@RequestBody Zahtev zahtev) throws Exception {
		
		System.out.println("Firma pravi zahtev za izvod Banci.");
		
		System.out.println("Racun: " + zahtev.getRacun());
		System.out.println("Rbr: " + zahtev.getRbr());
		System.out.println("Datum: "+ zahtev.getDatum());
		
		Izvod out = new Izvod();
		
		try {
			out = posaljiZahtev(zahtev);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("SNIMLJEN IZVOD!");
		System.out.println("Broj preseka za dan: " + out.getBrojPreseka());
		//System.out.println("Prva Stavka " + out.getStavke().get(0).getIznos());
		
		return new ResponseEntity<>(out, HttpStatus.OK);
	}

}
