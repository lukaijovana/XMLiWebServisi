package bankApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.informatika.domen.NalogKorisnikaEntity;
import rs.ac.uns.ftn.informatika.domen.NalogZaPlacanjeEntity;
import rs.ac.uns.ftn.informatika.mt900.Odgovor900;
import rs.ac.uns.ftn.informatika.mt910.Zahtev910;
import rs.ac.uns.ftn.informatika.mt910.Odgovor910;

@Endpoint
public class MT910EndPoint {
	private static final String NAMESPACE_URI = "http://www.informatika.ftn.uns.ac.rs/mt910";
	private NalogServis nalogServis;
	private NalogKorisnikaServis nalogKorisnikaServis;
	private BankarskiNalogServis bankarskiNalogServis;

	@Autowired
	public MT910EndPoint(NalogServisImpl nalogService, NalogKorisnikaServisImpl korisnickiNalogService, BankarskiNalogServisImpl nalogBankeService) {
		this.bankarskiNalogServis = nalogBankeService;
		System.out.println("kreira");
		this.nalogServis = nalogService;
		this.nalogKorisnikaServis = korisnickiNalogService;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendMt910Request")
	@ResponsePayload
	public Odgovor910 sendMtRequest(@RequestPayload Zahtev910 request) {
		Odgovor910 response = new Odgovor910();
		System.out.println("stigla potvrdna poruka");
		System.out.println(request.getMT910Zahtev().getSWIFTPoverioca());
		String id = request.getMT910Zahtev().getIDPorukeNaloga();
		List<NalogZaPlacanjeEntity> nalozi = nalogServis.findAll();
		NalogZaPlacanjeEntity nalog = null;
		for(NalogZaPlacanjeEntity np: nalozi) {
			if(np.getIdPoruke().equals(id)) {
				nalog = np;
				break;
			}
		}
		List<NalogKorisnikaEntity> korisnici = nalogKorisnikaServis.findAll();
		for(NalogKorisnikaEntity k: korisnici) {
			if(k.getRacun().equals(nalog.getRacunPrimaoca())) {
				double stanje = k.getStanjeNaRacunu();
				stanje += request.getMT910Zahtev().getIznos().doubleValue();
				k.setStanjeNaRacunu(stanje);
				nalogKorisnikaServis.save(k);
				break;
			}
		}
		nalog.setClear(true);
		nalogServis.save(nalog);
		response.setMT910Odg("jeee");
		return response;
	}
}
