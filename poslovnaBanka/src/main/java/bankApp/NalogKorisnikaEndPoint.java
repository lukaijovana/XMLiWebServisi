package bankApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.informatika.domen.NalogKorisnikaEntity;
import rs.ac.uns.ftn.informatika.domen.BankarskiNalogEntity;
import rs.ac.uns.ftn.informatika.nalogzakorisnike.NalogKorisnikaZahtev;
import rs.ac.uns.ftn.informatika.nalogzakorisnike.NalogKorisnikaOdgovor;
import rs.ac.uns.ftn.informatika.nalogzakorisnike.NalogKorisnika;
import rs.ac.uns.ftn.informatika.bankarskinalog.KreirajZahtevBnkNalog;
import rs.ac.uns.ftn.informatika.bankarskinalog.KreirajOdgBnkNalog;
import rs.ac.uns.ftn.informatika.bankarskinalog.BankarskiNalog;

@Endpoint
public class NalogKorisnikaEndPoint {
	private static final String NAMESPACE_URI = "http://www.informatika.ftn.uns.ac.rs/korisnickiNalog";
	private NalogKorisnikaServis nalogKorisnikaServis;
	private BankarskiNalogServis bankarskiNalogServis;

	@Autowired
	public NalogKorisnikaEndPoint(NalogKorisnikaServisImpl korisnickiNalogService, BankarskiNalogServisImpl nalogBankeService) {
		this.nalogKorisnikaServis = korisnickiNalogService;
		this.bankarskiNalogServis = nalogBankeService;
	}

	private void kreirajNalogBanke(String portBanke) {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan(ClassUtils.getPackageName(KreirajZahtevBnkNalog.class));
        try {
			marshaller.afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebServiceTemplate ws = new WebServiceTemplate(marshaller);
		KreirajZahtevBnkNalog request = new KreirajZahtevBnkNalog();
		String port = "8084";
		BankarskiNalog nalog = new BankarskiNalog();
		nalog.setBnkPort(portBanke);
		nalog.setRacun(portBanke.substring(1) + "000000000" + portBanke + "12");
		nalog.setSifraBanke(portBanke.substring(1));
		double inicijalno = 1000000;
		nalog.setStanjeRacuna(inicijalno);
		nalog.setSWIFT("BANK" + "RS" + portBanke.substring(2));
		System.out.println("kreira nalog banke");
		bankarskiNalogServis.save(new BankarskiNalogEntity(nalog));
		request.setNalogBanke(nalog);
		KreirajOdgBnkNalog resp = (KreirajOdgBnkNalog) ws.marshalSendAndReceive("http://localhost:" + port 
	               + "/ws", request);
	     System.out.println(resp.getOdgovor());
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createKorisnickiNalogRequest")
	@ResponsePayload
	public NalogKorisnikaOdgovor getNalogZaPrenosRequest(@RequestPayload NalogKorisnikaZahtev request) {
		NalogKorisnikaOdgovor response = new NalogKorisnikaOdgovor();
		//iz request-a uzmi nalog, sacuvaj ga u banci
		System.out.println("desii");
		NalogKorisnika kn = request.getNkZahtev();
		NalogKorisnikaEntity korisnicki = new NalogKorisnikaEntity(kn);
		nalogKorisnikaServis.save(korisnicki);
		kreirajNalogBanke(kn.getPortBnk());
		response.setNkOdg("stiglo");
		return response;
	}
}
