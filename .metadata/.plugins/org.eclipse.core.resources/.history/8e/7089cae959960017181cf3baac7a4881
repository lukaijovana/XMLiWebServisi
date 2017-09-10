package bankApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.informatika.domen.NalogKorisnikaEntity;
import rs.ac.uns.ftn.informatika.domen.NalogZaPrenosEntity;
import rs.ac.uns.ftn.informatika.mt900.Zahtev900;
import rs.ac.uns.ftn.informatika.mt900.Odgovor900;

@Endpoint
public class Mt900EndPoint {
	private static final String NAMESPACE_URI = "http://www.informatika.ftn.uns.ac.rs/mt900";
	private NalogService nalogService;
	private KorisnickiNalogService korisnickiNalogService;
	private NalogBankeService nalogBankeService;

	@Autowired
	public Mt900EndPoint(NalogServiceImpl nalogService, KorisnickiNalogServiceImpl korisnickiNalogService, NalogBankeServiceImpl nalogBankeService) {
		this.nalogBankeService = nalogBankeService;
		System.out.println("kreira");
		this.nalogService = nalogService;
		this.korisnickiNalogService = korisnickiNalogService;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendMt900Request")
	@ResponsePayload
	public Odgovor900 sendMtRequest(@RequestPayload Zahtev900 request) {
		Odgovor900 response = new Odgovor900();
		System.out.println("stigla potvrdna poruka");
		System.out.println(request.getMT900Zahtev().getSWIFTDuznika());
		String id = request.getMT900Zahtev().getIDPorukeNaloga();
		List<NalogZaPrenosEntity> nalozi = nalogService.findAll();
		NalogZaPrenosEntity nalog = null;
		for(NalogZaPrenosEntity np: nalozi) {
			if(np.getIdPoruke().equals(id)) {
				nalog = np;
				break;
			}
		}
		List<NalogKorisnikaEntity> korisnici = korisnickiNalogService.findAll();
		for(NalogKorisnikaEntity k: korisnici) {
			if(k.getRacun().equals(nalog.getRacunDuznika())) {
				double rezerv = k.getRezervisanaSuma();
				double stanje = k.getStanjeNaRacunu();
				rezerv -= request.getMT900Zahtev().getIznos().doubleValue();
				k.setRezervisanaSuma(rezerv);
				stanje -= request.getMT900Zahtev().getIznos().doubleValue();
				k.setStanjeNaRacunu(stanje);
				korisnickiNalogService.save(k);
				break;
			}
		}
		
		nalog.setClear(true);
		nalogService.save(nalog);
		response.setMT900Odg("jeee");
		return response;
	}
	

}
