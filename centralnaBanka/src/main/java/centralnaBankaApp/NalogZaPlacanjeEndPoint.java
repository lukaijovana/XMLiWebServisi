package centralnaBankaApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.informatika.domen.BankarskiNalogEntity;
import rs.ac.uns.ftn.informatika.nalogzaplacanje.PrimiNalog;
import rs.ac.uns.ftn.informatika.nalogzaplacanje.Nalog;
import rs.ac.uns.ftn.informatika.nalogzaplacanje.PosaljiNalog;

@Endpoint
public class NalogZaPlacanjeEndPoint {
	private static final String NAMESPACE_URI = "http://www.informatika.ftn.uns.ac.rs/nalogzp";
	private BankarskiNalogServis nalogBankeService;
	@Autowired
	public NalogZaPlacanjeEndPoint(BankarskiNalogServisImpl nalogBankeService) {
		this.nalogBankeService = nalogBankeService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendNalogZaPrenosRequest")
	@ResponsePayload
	public PrimiNalog getNalogZaPrenosRequest(@RequestPayload PosaljiNalog request) {
		PrimiNalog response = new PrimiNalog();
	/*	System.out.println("gadja nalog cb");
		//prosledi odgovarajucoj banci i zaduzi obracunski racun banke i dodaj na drugi
		List<NalogBanke> banke = nalogBankeService.findAll();
		NalogZaPrenos nalog = request.getNalogZaPrenos();
		NalogBanke zaZaduziti = null;
		NalogBanke zaOdobriti = null;
		for(NalogBanke nb: banke) {
			if(nb.getSifraBanke().equals(nalog.getRacunDuznika().substring(0,4))) {
				zaZaduziti = nb;
			} else if(nb.getSifraBanke().equals(nalog.getRacunPrimaoca().substring(0, 4))) {
				zaOdobriti = nb;
			}
		}
		if(zaZaduziti != null && zaOdobriti != null) {
			double obracunskiDuznika = zaZaduziti.getObracunskiRacun();
			obracunskiDuznika -= nalog.getIznos().doubleValue();
			if(obracunskiDuznika >= 0) {
				//ima dovoljno sredstava banka duznik, dodaj iznos banci primaocu
				double obracunskiPrimaoca = zaOdobriti.getObracunskiRacun();
				obracunskiPrimaoca += nalog.getIznos().doubleValue();
				//posalji nalog i poruku odobrenja banci primaoca/zaodobriti
				//posalji poruku o zaduzenju banci duznika
			}else {
				//banka nema dovoljno sredstava, odbij
			}
		}
		
		*/
		response.setPrimljeno("opa");
		return response;
	}
	
	
}
