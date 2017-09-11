package bankApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.informatika.domen.NalogKorisnikaEntity;
import rs.ac.uns.ftn.informatika.domen.NalogZaPrenosEntity;
import rs.ac.uns.ftn.informatika.mt900.Odgovor900;
import rs.ac.uns.ftn.informatika.mt910.Zahtev910;
import rs.ac.uns.ftn.informatika.mt910.Odgovor910;

@Endpoint
public class Mt910EndPoint {
	private static final String NAMESPACE_URI = "http://www.informatika.ftn.uns.ac.rs/mt910";
	private NalogService nalogService;
	private KorisnickiNalogService korisnickiNalogService;
	private NalogBankeService nalogBankeService;

	@Autowired
	public Mt910EndPoint(NalogServiceImpl nalogService, KorisnickiNalogServiceImpl korisnickiNalogService, NalogBankeServiceImpl nalogBankeService) {
		this.nalogBankeService = nalogBankeService;
		System.out.println("kreira");
		this.nalogService = nalogService;
		this.korisnickiNalogService = korisnickiNalogService;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendMt910Request")
	@ResponsePayload
	public Odgovor910 sendMtRequest(@RequestPayload Zahtev910 request) {
		Odgovor910 response = new Odgovor910();
		System.out.println("stigla potvrdna poruka");
		System.out.println(request.getMT910Zahtev().getSWIFTPoverioca());
		String id = request.getMT910Zahtev().getIDPorukeNaloga();
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
			if(k.getRacun().equals(nalog.getRacunPrimaoca())) {
				double stanje = k.getStanjeNaRacunu();
				stanje += request.getMT910Zahtev().getIznos().doubleValue();
				k.setStanjeNaRacunu(stanje);
				korisnickiNalogService.save(k);
				break;
			}
		}
		nalog.setClear(true);
		nalogService.save(nalog);
		response.setMT910Odg("aaaaaaaaaa");
		return response;
	}
}
