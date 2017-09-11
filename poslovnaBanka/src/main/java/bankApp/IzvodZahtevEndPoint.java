package bankApp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.informatika.domen.NalogKorisnikaEntity;
<<<<<<< HEAD
import rs.ac.uns.ftn.informatika.domen.NalogZaPrenosEntity;
=======
import rs.ac.uns.ftn.informatika.domen.NalogZaPlacanjeEntity;
>>>>>>> 0adacb2758328548b12009cca4c3479653db78e5
import rs.ac.uns.ftn.informatika.odgovorizvod.PreuzetOdgovor;
import rs.ac.uns.ftn.informatika.odgovorizvod.OdgovorIzvod;
import rs.ac.uns.ftn.informatika.odgovorizvod.PresekStavki;
import rs.ac.uns.ftn.informatika.zahtevizvod.PreuzetZahtev;
import rs.ac.uns.ftn.informatika.zahtevizvod.PoslatZahtev;
import rs.ac.uns.ftn.informatika.nalogzaplacanje.PrimiNalog;
import rs.ac.uns.ftn.informatika.nalogzaplacanje.Nalog;
<<<<<<< HEAD
import rs.ac.uns.ftn.informatika.nalogzaplacanje.PosaljiNalog;
=======
import rs.ac.uns.ftn.informatika.nalogzaplacanje.PosaljiNalog ;
>>>>>>> 0adacb2758328548b12009cca4c3479653db78e5

@Endpoint
public class IzvodZahtevEndPoint {

	private static final String NAMESPACE_URI = "http://www.informatika.ftn.uns.ac.rs/izvodZahtev";
<<<<<<< HEAD
	private NalogService nalogService;
	private KorisnickiNalogService korisnickiNalogService;
	private NalogBankeService nalogBankeService;
=======
	private NalogServis nalogServis;
	private NalogKorisnikaServis nalogKorisnikaServis;
	private BankarskiNalogServis bankarskiNalogServis;
>>>>>>> 0adacb2758328548b12009cca4c3479653db78e5
	
	@SuppressWarnings("deprecation")
	private boolean istidan(Date prvi, Date drugi)
	{
		if (prvi.getDay() == drugi.getDay())
			if (prvi.getMonth() == drugi.getMonth())
				if (prvi.getYear() == drugi.getYear())
					return true;
		return false;
	}
	
	@Autowired
<<<<<<< HEAD
	public IzvodZahtevEndPoint(NalogServiceImpl nalogService, KorisnickiNalogServiceImpl korisnickiNalogService, NalogBankeServiceImpl nalogBankeService) {
		this.nalogBankeService = nalogBankeService;
		System.out.println("kreira se izvod endpoint");
		this.nalogService = nalogService;
		this.korisnickiNalogService = korisnickiNalogService;
=======
	public IzvodZahtevEndPoint(NalogServisImpl nalogService, NalogKorisnikaServisImpl korisnickiNalogService, BankarskiNalogServisImpl nalogBankeService) {
		this.bankarskiNalogServis = nalogBankeService;
		System.out.println("kreira se izvod endpoint");
		this.nalogServis = nalogService;
		this.nalogKorisnikaServis = korisnickiNalogService;
>>>>>>> 0adacb2758328548b12009cca4c3479653db78e5
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendIzvodZahtevRequest")
	@ResponsePayload
	public PreuzetZahtev getIzvodZahtevResponse(@RequestPayload PoslatZahtev request) {
		PreuzetZahtev response = new PreuzetZahtev();
		System.out.println(request.getIzvodZahtev().getBrojRacuna() + " " + request.getIzvodZahtev().getRBRPreseka());
		OdgovorIzvod odg = new OdgovorIzvod();
		odg.setPresekBroj(request.getIzvodZahtev().getRBRPreseka());
		odg.setBrPromenaNaTeret(55);
		odg.setBrPromenaUKorist(22);
		odg.setBrojRacuna(request.getIzvodZahtev().getBrojRacuna());
		odg.setDatum(request.getIzvodZahtev().getDatum());
		odg.setNovoStanje(BigDecimal.valueOf(22));
		odg.setPrethodnoStanje(BigDecimal.valueOf(11));
		odg.setUkupnoNaTeret(BigDecimal.valueOf(11));
		odg.setUkupnoUKorist(BigDecimal.valueOf(111));
		
		//Inicijalizacija headera izvoda
		
		int brojPreseka=0;
		int brojPromenaNaTeret=0;
		int brojPromenaUKorist=0;
		String brojRacuna = request.getIzvodZahtev().getBrojRacuna();
		XMLGregorianCalendar datumNaloga = request.getIzvodZahtev().getDatum();
		double novoStanje = 0;
		double prethodnoStanje = 200000;
		double ukupnoNaTeret = 0;
		double ukupnoUKorist = 0;
		
		//Novo (trenutno) Stanje
<<<<<<< HEAD
		List<NalogKorisnikaEntity> korisnici = korisnickiNalogService.findAll();
=======
		List<NalogKorisnikaEntity> korisnici = nalogKorisnikaServis.findAll();
>>>>>>> 0adacb2758328548b12009cca4c3479653db78e5
		
		for (NalogKorisnikaEntity korisnickiNalogEntity : korisnici) {
			if (korisnickiNalogEntity.getRacun().equals(brojRacuna))
				novoStanje = korisnickiNalogEntity.getStanjeNaRacunu();
		}
		
		//pretrazi sve naloge
<<<<<<< HEAD
		List<NalogZaPrenosEntity> nalozi = nalogService.findAll();

		for (NalogZaPrenosEntity nzp : nalozi) {
=======
		List<NalogZaPlacanjeEntity> nalozi = nalogServis.findAll();

		for (NalogZaPlacanjeEntity nzp : nalozi) {
>>>>>>> 0adacb2758328548b12009cca4c3479653db78e5
			//Racunanje prethodnog stanja pre tog dana
			if(nzp.getDatumNaloga().before(datumNaloga.toGregorianCalendar().getTime()))
			{
				//Ako mi placamo
				if (nzp.getRacunDuznika().equals(brojRacuna))
				{
					prethodnoStanje=prethodnoStanje-nzp.getIznos().doubleValue();
				}
				
				//Ako je nama uplaceno
				if (nzp.getRacunPrimaoca().equals(brojRacuna))
				{
					prethodnoStanje=prethodnoStanje+nzp.getIznos().doubleValue();
				}
			}
			
			//Da li je obradjen nalog
			if(nzp.isHitno() || nzp.isClear())
			{
			//odgovara datum
			if (istidan(nzp.getDatumNaloga(), datumNaloga.toGregorianCalendar().getTime()))
			{
				brojPreseka++;
				
				PresekStavki teretStavka = new PresekStavki();
				//Datumi stavke
				GregorianCalendar datumValute = new GregorianCalendar();
				datumValute.setTime(nzp.getDatumValute());
				XMLGregorianCalendar dateVal;
				try {
					dateVal = DatatypeFactory.newInstance().newXMLGregorianCalendar(datumValute);
					teretStavka.setDatumValute(dateVal);
				} catch (DatatypeConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				teretStavka.setDatumNaloga(datumNaloga);

				teretStavka.setIznos(nzp.getIznos());
				teretStavka.setModelOdobrenja(nzp.getModelOdobrenja().intValue());
				teretStavka.setModelZaduzenja(nzp.getModelZaduzenja().intValue());
				teretStavka.setPNBOdobrenja(nzp.getPozivNaBrojOdobrenja());
				teretStavka.setPNBZaduzenja(nzp.getPozivNaBrojZaduzenja());
				teretStavka.setSmer("S");
				teretStavka.setSvrhaPlacanja(nzp.getSvrhaPlacanja());
				
				teretStavka.setDuznik(nzp.getDuznik());
				teretStavka.setPrimalac(nzp.getPrimalac());
				teretStavka.setRacunDuznika(nzp.getRacunDuznika());
				teretStavka.setRacunPoverioca(nzp.getRacunPrimaoca());
				
				//Ako mi placamo
				if (nzp.getRacunDuznika().equals(brojRacuna))
				{
					brojPromenaNaTeret++;
					ukupnoNaTeret=nzp.getIznos().doubleValue()+ukupnoNaTeret;
				}
				
				//Ako je nama uplaceno
				if (nzp.getRacunPrimaoca().equals(brojRacuna))
				{
					brojPromenaUKorist++;
					ukupnoUKorist = nzp.getIznos().doubleValue() + ukupnoUKorist;
				}
				
				//Odmah na odgovor ide stavka
				odg.getStavka().add(teretStavka);
			}
			}
		}
		//Prethodno Stanje
		novoStanje = prethodnoStanje+ukupnoUKorist-ukupnoNaTeret;
		
		//Obradi u onaj XML oblik header, nakaci sve na odgovor
		odg.setPresekBroj(brojPreseka);
		odg.setBrPromenaNaTeret(brojPromenaNaTeret);
		odg.setBrPromenaUKorist(brojPromenaUKorist);
		odg.setBrojRacuna(brojRacuna);
		odg.setDatum(datumNaloga);
		odg.setNovoStanje(BigDecimal.valueOf(novoStanje));
		odg.setPrethodnoStanje(BigDecimal.valueOf(prethodnoStanje));
		odg.setUkupnoNaTeret(BigDecimal.valueOf(ukupnoNaTeret));
		odg.setUkupnoUKorist(BigDecimal.valueOf(ukupnoUKorist));
		
		System.out.println("Spremio odgovor izvoda");
		
		response.setOdgovor(odg);

		return response;
	}
}
