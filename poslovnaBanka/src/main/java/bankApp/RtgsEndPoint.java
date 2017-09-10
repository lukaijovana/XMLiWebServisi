package bankApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.informatika.domen.NalogZaPlacanjeEntity;
import rs.ac.uns.ftn.informatika.mt103.Zahtev103;
import rs.ac.uns.ftn.informatika.mt103.Odgovor103;

@Endpoint
public class RtgsEndPoint {
	private static final String NAMESPACE_URI = "http://www.informatika.ftn.uns.ac.rs/mt103";
	private NalogServis nalogServis;
	private NalogKorisnikaServis nalogKorisnikaServis;
	private BankarskiNalogServis bankarskiNalogServis;

	@Autowired
	public RtgsEndPoint(NalogServisImpl nalogService, NalogKorisnikaServisImpl korisnickiNalogService, BankarskiNalogServisImpl nalogBankeService) {
		this.bankarskiNalogServis = nalogBankeService;
		System.out.println("kreira");
		this.nalogServis = nalogService;
		this.nalogKorisnikaServis = korisnickiNalogService;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendRtgsRequest")
	@ResponsePayload
	public Odgovor103 sendMtRequest(@RequestPayload Zahtev103 request) {
		Odgovor103 response = new Odgovor103();
		System.out.println("stigla potvrdna mt103 poruka");
		NalogZaPlacanjeEntity npe = new NalogZaPlacanjeEntity();
		//konvertovanje iz xmlgregorian u sql date
		java.util.Date dt = request.getMT103Zahtev().getDatumNaloga().toGregorianCalendar().getTime();
		java.sql.Date sqlDatumNaloga = new java.sql.Date(dt.getTime());
		java.util.Date dt1 = request.getMT103Zahtev().getDatumValute().toGregorianCalendar().getTime();
		java.sql.Date sqlDatumValute = new java.sql.Date(dt1.getTime());
		npe.setDatumNaloga(sqlDatumNaloga);
		npe.setDatumValute(sqlDatumValute);
		npe.setDuznik(request.getMT103Zahtev().getDuznik().getNaziv());
		npe.setHitno(true);
		npe.setIdPoruke(request.getMT103Zahtev().getIDPoruke());
		npe.setIznos(request.getMT103Zahtev().getIznos());
		npe.setModelOdobrenja(request.getMT103Zahtev().getPrimalac().getModel());
		npe.setModelZaduzenja(request.getMT103Zahtev().getDuznik().getModel());
		npe.setOznakaValute(request.getMT103Zahtev().getSifraValute());
		npe.setPozivNaBrojOdobrenja(request.getMT103Zahtev().getPrimalac().getPNB());
		npe.setPozivNaBrojZaduzenja(request.getMT103Zahtev().getDuznik().getPNB());
		npe.setPrimalac(request.getMT103Zahtev().getPrimalac().getNaziv());
		npe.setRacunDuznika(request.getMT103Zahtev().getDuznik().getRacun());
		npe.setRacunPrimaoca(request.getMT103Zahtev().getPrimalac().getRacun());
		npe.setSvrhaPlacanja(request.getMT103Zahtev().getSvrhaPlacanja());
		npe.setClear(false);
		nalogServis.save(npe);
		response.setMT103Odg("jeee");
		return response;
	}
}
