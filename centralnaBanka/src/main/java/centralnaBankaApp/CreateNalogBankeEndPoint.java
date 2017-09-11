package centralnaBankaApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.informatika.domen.NalogBankeEntity;
import rs.ac.uns.ftn.informatika.bankarskinalog.KreirajZahtevBnkNalog;
import rs.ac.uns.ftn.informatika.bankarskinalog.KreirajOdgBnkNalog;
import rs.ac.uns.ftn.informatika.bankarskinalog.UzmiZahtevBnkNalog;
import rs.ac.uns.ftn.informatika.bankarskinalog.UzmiOdgBnkNalog;
import rs.ac.uns.ftn.informatika.bankarskinalog.BankarskiNalog;

@Endpoint
public class CreateNalogBankeEndPoint {
	private static final String NAMESPACE_URI = "http://www.informatika.ftn.uns.ac.rs/nalogBanke";
	private NalogBankeService nalogBankeService;
	@Autowired
	public CreateNalogBankeEndPoint(NalogBankeServiceImpl nalogBankeService) {
		this.nalogBankeService = nalogBankeService;
	}
	

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createNalogBankeRequest")
	@ResponsePayload
	public KreirajOdgBnkNalog getNalogZaPrenosRequest(@RequestPayload KreirajZahtevBnkNalog request) {
		KreirajOdgBnkNalog response = new KreirajOdgBnkNalog();
		NalogBankeEntity nIn = new NalogBankeEntity(request.getNalogBanke());
		nalogBankeService.save(nIn);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNalogBankeRequest")
	@ResponsePayload
	public UzmiOdgBnkNalog getNalogBankeRequest(@RequestPayload UzmiZahtevBnkNalog request) {
		UzmiOdgBnkNalog response = new UzmiOdgBnkNalog();
		System.out.println("tuuuu");
		List<NalogBankeEntity> nalozi = nalogBankeService.findAll();
		NalogBankeEntity nalog = null;
		for(NalogBankeEntity nal: nalozi) {
			if(nal.getSifraBanke().equals(request.getBnkPort())) {
				nalog = nal;
				break;
			}
		}
		BankarskiNalog nb = new BankarskiNalog();
		nb.setRacun(nalog.getObracunskiRacun());
		nb.setBnkPort(nalog.getPort());
		nb.setSifraBanke(nalog.getSifraBanke());
		nb.setStanjeRacuna(nalog.getStanjeNaRacunu());
		nb.setSWIFT(nalog.getSWIFT());

		response.setNalogBanke(nb);
		return response;
	}
}
