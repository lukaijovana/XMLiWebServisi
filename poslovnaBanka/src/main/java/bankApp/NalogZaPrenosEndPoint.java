package bankApp;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.informatika.domen.NalogKorisnikaEntity;
import rs.ac.uns.ftn.informatika.domen.NalogBankeEntity;
import rs.ac.uns.ftn.informatika.domen.NalogZaPrenosEntity;
import rs.ac.uns.ftn.informatika.mt103.MT103;
import rs.ac.uns.ftn.informatika.mt103.Zahtev103;
import rs.ac.uns.ftn.informatika.mt103.Odgovor103;
import rs.ac.uns.ftn.informatika.mt103.Ucesnik;
import rs.ac.uns.ftn.informatika.bankarskinalog.UzmiZahtevBnkNalog;
import rs.ac.uns.ftn.informatika.bankarskinalog.UzmiOdgBnkNalog;
import rs.ac.uns.ftn.informatika.bankarskinalog.BankarskiNalog;
import rs.ac.uns.ftn.informatika.nalogzaplacanje.PrimiNalog;
import rs.ac.uns.ftn.informatika.nalogzaplacanje.Nalog;
import rs.ac.uns.ftn.informatika.nalogzaplacanje.PosaljiNalog;

@Endpoint
public class NalogZaPrenosEndPoint {
	private static final String NAMESPACE_URI = "http://www.informatika.ftn.uns.ac.rs/nalogzp";
	private NalogService nalogService;
	private KorisnickiNalogService korisnickiNalogService;
	private NalogBankeService nalogBankeService;

	@Autowired
	public NalogZaPrenosEndPoint(NalogServiceImpl nalogService, KorisnickiNalogServiceImpl korisnickiNalogService, NalogBankeServiceImpl nalogBankeService) {
		this.nalogBankeService = nalogBankeService;
		System.out.println("kreira");
		this.nalogService = nalogService;
		this.korisnickiNalogService = korisnickiNalogService;
	}
	
	private void istaBanka(Nalog nalog) throws Exception {
		List<NalogKorisnikaEntity> nalozi = korisnickiNalogService.findAll();
		NalogKorisnikaEntity nalogDuznika = null;
		NalogKorisnikaEntity nalogPrimaoca = null;
		for(NalogKorisnikaEntity kn: nalozi) {
			if(kn.getRacun().equals(nalog.getRacunDuznika())) {
				nalogDuznika = kn;
			}
			if(kn.getRacun().equals(nalog.getRacunPrimaoca())) {
				nalogPrimaoca = kn;
			}
		}
		
		
		if(nalogDuznika != null && nalogPrimaoca != null) {
			double rezervisanaSuma = nalogDuznika.getRezervisanaSuma();
			double stanje = nalogDuznika.getStanjeNaRacunu();
			double raspolozivo = stanje - rezervisanaSuma;
			BigDecimal rasp = new BigDecimal(raspolozivo);
			if(rasp.compareTo(nalog.getIznos()) >= 0) {
				//ako je rasp vece ili jednako od iznosa
				//ima sredstava, odraditi rtgs
				nalogDuznika.setStanjeNaRacunu(nalogDuznika.getStanjeNaRacunu()-nalog.getIznos().doubleValue());
				nalogPrimaoca.setStanjeNaRacunu(nalogPrimaoca.getStanjeNaRacunu()+nalog.getIznos().doubleValue());
			
				korisnickiNalogService.save(nalogDuznika);
				korisnickiNalogService.save(nalogPrimaoca);
			}
		}
	}
	
	private void mt102(Nalog nalog) throws Exception {
		//ajde da nadjemo racun duznika
		List<NalogKorisnikaEntity> nalozi = korisnickiNalogService.findAll();
		NalogKorisnikaEntity nalogDuznika = null;
		for(NalogKorisnikaEntity kn: nalozi) {
			if(kn.getRacun().equals(nalog.getRacunDuznika())) {
				nalogDuznika = kn;
				break;
			}
		}
		//ako je racun duznika u nasoj banci rezervisi novac
		if(nalogDuznika != null) {
			double rezervisanaSuma = nalogDuznika.getRezervisanaSuma();
			double stanje = nalogDuznika.getStanjeNaRacunu();
			double raspolozivo = stanje - rezervisanaSuma;
			BigDecimal rasp = new BigDecimal(raspolozivo);
			if(rasp.compareTo(nalog.getIznos()) >= 0) {
				//ako je rasp vece ili jednako od iznosa
				//ima sredstava, odraditi rtgs
				rezervisanaSuma += nalog.getIznos().doubleValue();
				nalogDuznika.setRezervisanaSuma(rezervisanaSuma);
				korisnickiNalogService.save(nalogDuznika);
			}
		}
	}
	
	private void rtgs(Nalog nalog) throws Exception {
		//pronadji duznika
		//ako na racunu ima dovoljno sredstava, rezervisi sumu
		//prosledi nalog centralnoj banci
		List<NalogKorisnikaEntity> nalozi = korisnickiNalogService.findAll();
		NalogKorisnikaEntity nalogDuznika = null;
		System.out.println("pozove rtgs");
		MT103 mt103 = new MT103();
		mt103.setDatumNaloga(nalog.getDatumNaloga());
		mt103.setDatumValute(nalog.getDatumValute());
		Ucesnik duznik = new Ucesnik();
		duznik.setModel(nalog.getModelZaduzenja());
		duznik.setNaziv(nalog.getDuznik());
		duznik.setPNB(nalog.getPNBZaduzenja());
		//swift i obracunski banke
		List<NalogBankeEntity> naloziBanaka = nalogBankeService.findAll();
		duznik.setObracunskiRacun(naloziBanaka.get(0).getObracunskiRacun());
		duznik.setSWIFT(naloziBanaka.get(0).getSWIFT());
		duznik.setRacun(nalog.getRacunDuznika());
		mt103.setDuznik(duznik);
		Ucesnik primalac = new Ucesnik();
		primalac.setModel(nalog.getModelOdobrenja());
		primalac.setNaziv(nalog.getPrimalac());
		primalac.setPNB(nalog.getPNBOdobrenja());
		primalac.setRacun(nalog.getRacunPrimaoca());
		BankarskiNalog bankaPrim = getSwiftIObracunski(nalog.getRacunPrimaoca().substring(0,3));
		primalac.setObracunskiRacun(bankaPrim.getRacun());
		primalac.setSWIFT(bankaPrim.getSWIFT());
		mt103.setPrimalac(primalac);
		mt103.setIDPoruke(nalog.getIDPoruke());
		mt103.setIznos(nalog.getIznos());
		mt103.setSifraValute(nalog.getOznakaValute());
		mt103.setSvrhaPlacanja(nalog.getSvrhaPlacanja());
		for(NalogKorisnikaEntity kn: nalozi) {
			if(kn.getRacun().equals(nalog.getRacunDuznika())) {
				nalogDuznika = kn;
				break;
			}
		}
		if(nalogDuznika != null) {
			double rezervisanaSuma = nalogDuznika.getRezervisanaSuma();
			double stanje = nalogDuznika.getStanjeNaRacunu();
			double raspolozivo = stanje - rezervisanaSuma;
			BigDecimal rasp = new BigDecimal(raspolozivo);
			if(rasp.compareTo(nalog.getIznos()) >= 0) {
				//ako je rasp vece ili jednako od iznosa
				//ima sredstava, odraditi rtgs
				rezervisanaSuma += nalog.getIznos().doubleValue();
				nalogDuznika.setRezervisanaSuma(rezervisanaSuma);
				korisnickiNalogService.save(nalogDuznika);
				//poziv rtgs
				Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
				marshaller.setPackagesToScan(ClassUtils.getPackageName(Zahtev103.class));
		        marshaller.afterPropertiesSet();
				WebServiceTemplate ws = new WebServiceTemplate(marshaller);
				Zahtev103 request = new Zahtev103();
				String port = "8084";
				request.setMT103Zahtev(mt103);
			    Odgovor103 resp = (Odgovor103) ws.marshalSendAndReceive("http://localhost:" + port 
			               + "/ws", request);
			     System.out.println(resp.getMT103Odg());
			}
		}
	}

	private BankarskiNalog getSwiftIObracunski(String sifraBankePrimaoca) {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan(ClassUtils.getPackageName(UzmiZahtevBnkNalog.class));
        try {
			marshaller.afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebServiceTemplate ws = new WebServiceTemplate(marshaller);
		UzmiZahtevBnkNalog request = new UzmiZahtevBnkNalog();
		String port = "8084";
		request.setBnkPort(sifraBankePrimaoca);
		UzmiOdgBnkNalog resp = (UzmiOdgBnkNalog) ws.marshalSendAndReceive("http://localhost:" + port 
	               + "/ws", request);
		return resp.getNalogBanke();
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendNalogZaPrenosRequest")
	@ResponsePayload
	public PrimiNalog getNalogZaPrenosRequest(@RequestPayload PosaljiNalog request) {
		PrimiNalog response = new PrimiNalog();
		System.out.println("gadja nalog");
		//primljen nalog
		Nalog nalogIn = request.getNalogZaPlacanje();
		NalogZaPrenosEntity nalog = new NalogZaPrenosEntity();
		nalog.setIdPoruke(nalogIn.getIDPoruke());
		nalog.setDuznik(nalogIn.getDuznik());
		nalog.setSvrhaPlacanja(nalogIn.getSvrhaPlacanja());
		nalog.setPrimalac(nalogIn.getPrimalac());
		//konvertovanje iz xmlgregorian u sql date
		java.util.Date dt = nalogIn.getDatumNaloga().toGregorianCalendar().getTime();
		java.sql.Date sqlDatumNaloga = new java.sql.Date(dt.getTime());
		nalog.setDatumNaloga(sqlDatumNaloga);
		java.util.Date dt1 = nalogIn.getDatumValute().toGregorianCalendar().getTime();
		java.sql.Date sqlDatumValute = new java.sql.Date(dt1.getTime());
		nalog.setDatumValute(sqlDatumValute);
		nalog.setRacunDuznika(nalogIn.getRacunDuznika());
		nalog.setModelZaduzenja(nalogIn.getModelZaduzenja());
		nalog.setPozivNaBrojZaduzenja(nalogIn.getPNBZaduzenja());
		nalog.setRacunPrimaoca(nalogIn.getRacunPrimaoca());
		nalog.setModelOdobrenja(nalogIn.getModelOdobrenja());
		nalog.setPozivNaBrojOdobrenja(nalogIn.getPNBOdobrenja());
		nalog.setIznos(nalogIn.getIznos());
		nalog.setOznakaValute(nalogIn.getOznakaValute());
		nalog.setHitno(nalogIn.isHitno());
		nalog.setClear(false);
		nalogService.save(nalog);
		BigDecimal iznosZaRtgs = new BigDecimal(250000);
		boolean istaBanka = false;
		//ako su prve tri cifre racuna iste, u istoj su banci
		System.out.println(nalogIn.getRacunDuznika());
		System.out.println(nalogIn.getRacunPrimaoca());
		if(nalogIn.getRacunDuznika().substring(0, 4).equals(nalogIn.getRacunPrimaoca().substring(0, 4))) {
			istaBanka = true;
		}
		System.out.println(nalogIn.getIznos().compareTo(iznosZaRtgs) >= 0 );
		System.out.println(nalogIn.isHitno());
		System.out.println(istaBanka);
		if((nalogIn.getIznos().compareTo(iznosZaRtgs) >= 0 || nalogIn.isHitno()) && !istaBanka) {
			System.out.println("dodje do poredjenja");
			try {
				rtgs(nalogIn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (!istaBanka)
		{
			//pravi MT102 poruku
			try {
				mt102(nalogIn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			//Ista banka su samo prebaci novac i pozz pozz
			try {
				istaBanka(nalogIn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//ako je hitno ili ako je iznos veci od 250 000 i primalac je u drugoj banci, rtgs
		//ako je unutar iste banke odmah izvrsi
		//ako nije u istoj banci i nije hitno i manji iznos od 250000, cekaj clearing
		System.out.println("kraj metode");
		response.setPrimljeno("stiglo");
		
		return response;
	}
	
	
}
