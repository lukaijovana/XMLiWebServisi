package bankApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.informatika.domen.NalogKorisnikaEntity;
import rs.ac.uns.ftn.informatika.domen.NalogZaPlacanjeEntity;
import rs.ac.uns.ftn.informatika.mt900.Zahtev900;
import rs.ac.uns.ftn.informatika.mt900.Odgovor900;

@Endpoint
public class MT900EndPoint {
	private static final String NAMESPACE_URI = "http://www.informatika.ftn.uns.ac.rs/mt900";
	private NalogServis nalogServis;
	private NalogKorisnikaServis nalogKorisnikaServis;
	private BankarskiNalogServis bankarskiNalogServis;

	@Autowired
	public MT900EndPoint(NalogServisImpl nalogService, NalogKorisnikaServisImpl korisnickiNalogService, BankarskiNalogServisImpl nalogBankeService) {
		this.bankarskiNalogServis = nalogBankeService;
		System.out.println("kreira");
		this.nalogServis = nalogService;
		this.nalogKorisnikaServis = korisnickiNalogService;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendMt900Request")
	@ResponsePayload
	public Odgovor900 sendMtRequest(@RequestPayload Zahtev900 request) {
		Odgovor900 response = new Odgovor900();
		System.out.println("stigla potvrdna poruka");
		System.out.println(request.getMT900Zahtev().getSWIFTDuznika());
		String id = request.getMT900Zahtev().getIDPorukeNaloga();
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
			if(k.getRacun().equals(nalog.getRacunDuznika())) {
				double rezerv = k.getRezervisanaSuma();
				double stanje = k.getStanjeNaRacunu();
				rezerv -= request.getMT900Zahtev().getIznos().doubleValue();
				k.setRezervisanaSuma(rezerv);
				stanje -= request.getMT900Zahtev().getIznos().doubleValue();
				k.setStanjeNaRacunu(stanje);
				nalogKorisnikaServis.save(k);
				break;
			}
		}
		
		nalog.setClear(true);
		nalogServis.save(nalog);
		response.setMT900Odg("jeee");
		return response;
	}
	

}
