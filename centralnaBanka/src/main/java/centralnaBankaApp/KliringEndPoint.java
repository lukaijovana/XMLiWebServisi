package centralnaBankaApp;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.informatika.domen.BankarskiNalogEntity;
import rs.ac.uns.ftn.informatika.kliringzahtev.SendKliringRequest;
import rs.ac.uns.ftn.informatika.kliringzahtev.SendKliringResponse;
import rs.ac.uns.ftn.informatika.mt102.Zahtev102;
import rs.ac.uns.ftn.informatika.mt102.Odgovor102;
import rs.ac.uns.ftn.informatika.mt102.Stavka;
import rs.ac.uns.ftn.informatika.mt103.MT103;
import rs.ac.uns.ftn.informatika.mt103.Zahtev103;
import rs.ac.uns.ftn.informatika.mt103.Odgovor103;
import rs.ac.uns.ftn.informatika.mt103.Ucesnik;
import rs.ac.uns.ftn.informatika.mt900.MT900;
import rs.ac.uns.ftn.informatika.mt900.Zahtev900;
import rs.ac.uns.ftn.informatika.mt900.Odgovor900;
import rs.ac.uns.ftn.informatika.mt910.MT910;
import rs.ac.uns.ftn.informatika.mt910.Zahtev910;
import rs.ac.uns.ftn.informatika.mt910.Odgovor910;

@Endpoint
public class KliringEndPoint {
	private static final String NAMESPACE_URI = "http://www.informatika.ftn.uns.ac.rs/kliringZahtev";
	private BankarskiNalogServis nalogBankeService;
	@Autowired
	public KliringEndPoint(BankarskiNalogServisImpl nalogBankeService) {
		this.nalogBankeService = nalogBankeService;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendKliringRequest")
	@ResponsePayload
	public SendKliringResponse getSendKliringResponse(@RequestPayload SendKliringRequest request) {
		System.out.println(request.getMessage());
		SendKliringResponse response = new SendKliringResponse();
		response.setMessage("Primljen zahtev.");
		
		//Za sve naloge zahtevaj Mt102 poruku
		List<BankarskiNalogEntity> nalozi = nalogBankeService.findAll();
		for (BankarskiNalogEntity nalog : nalozi) {
			String port = nalog.getPort();
			System.out.println("==========Za: "+ nalog.getPort());
			System.out.println(nalog.getObracunskiRacun());
			try{

			Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
			marshaller.setPackagesToScan(ClassUtils.getPackageName(Zahtev102.class));
	        marshaller.afterPropertiesSet();
	        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
	        Zahtev102 requestMt102 = new Zahtev102();
	        requestMt102.setMT102Zahtev(port);

	        Odgovor102 respMt102 = (Odgovor102) ws.marshalSendAndReceive("http://localhost:" + port 
		               + "/ws", requestMt102);
	        System.out.println("Dobio ob rac duznika:"+respMt102.getMT102Odg().getRacunBnkDuznika());
	        System.out.println("===Stavke:");
	        //TODO: nakaci u listu Mt102 poruki
	        List <Stavka> stavke = respMt102.getMT102Odg().getNalozi();
	        for (Stavka stavka : stavke) {
	        	System.out.println(stavka.getRacunDuznika());
				System.out.println(stavka.getIznos());
				
	    		List<BankarskiNalogEntity> banke = nalogBankeService.findAll();
	    		System.out.println(stavka.getIznos());
	    		MT103 vracaj = new MT103();
	    		BankarskiNalogEntity zaZaduziti = null;
	    		BankarskiNalogEntity zaOdobriti = null;
	    		for(BankarskiNalogEntity nb: banke) {
	    			if(nb.getObracunskiRacun().equals(respMt102.getMT102Odg().getRacunBnkDuznika())) {
	    				zaZaduziti = nb;
	    			} else {
	    				zaOdobriti = nb;
	    			}
	    		}
	    		
	    		if(zaZaduziti != null && zaOdobriti != null) {
	    			double obracunskiDuznika = zaZaduziti.getStanjeNaRacunu();
	    			obracunskiDuznika -= stavka.getIznos().doubleValue();
	    			if(obracunskiDuznika >= 0) {
	    				//ima dovoljno sredstava banka duznik, dodaj iznos banci primaocu
	    				double obracunskiPrimaoca = zaOdobriti.getStanjeNaRacunu();
	    				obracunskiPrimaoca += stavka.getIznos().doubleValue();
	    				//posalji nalog i poruku odobrenja banci primaoca/zaodobriti
	    				//posalji poruku o zaduzenju banci duznika
	    				
	    				MT900 odg = new MT900();
	    				odg.setIDPoruke("9");
	    				odg.setSWIFTDuznika(zaZaduziti.getSWIFT());
	    				odg.setDatum(respMt102.getMT102Odg().getDatumValute());
	    				odg.setIDPorukeNaloga(stavka.getIDNaloga());
	    				odg.setIznos(stavka.getIznos());
	    				odg.setRacunBnkDuznika(zaZaduziti.getObracunskiRacun());
	    				odg.setSifraValute(respMt102.getMT102Odg().getSifraValute());
	    				zaZaduziti.setStanjeNaRacunu(obracunskiDuznika);
	    				nalogBankeService.save(zaZaduziti);
	    				zaOdobriti.setStanjeNaRacunu(obracunskiPrimaoca);
	    				nalogBankeService.save(zaOdobriti);
	    				System.out.println("Saljem Mt900 za stavku "+stavka.getIznos());
	    				try {
	    					posaljiMt900(odg, zaZaduziti.getPort());
	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}
	    				
	    				vracaj.setDatumNaloga(stavka.getDatumNaloga());
	    				vracaj.setDatumValute(respMt102.getMT102Odg().getDatumValute());
	    				vracaj.setIDPoruke(stavka.getIDNaloga());
	    				vracaj.setIznos(stavka.getIznos());
	    				vracaj.setSifraValute(respMt102.getMT102Odg().getSifraValute());
	    				vracaj.setSvrhaPlacanja(stavka.getSvrhaPlacanja());
	    				
	    				Ucesnik duznikU = new Ucesnik();
	    				
	    				duznikU.setModel(BigInteger.valueOf(stavka.getModelZaduzenja()));
	    				duznikU.setNaziv(stavka.getDuznik());
	    				duznikU.setObracunskiRacun(zaZaduziti.getObracunskiRacun());
	    				duznikU.setPNB(stavka.getPNBZaduzenja());
	    				duznikU.setRacun(stavka.getRacunDuznika());
	    				duznikU.setSWIFT(zaZaduziti.getSWIFT());
	    				
	    				Ucesnik primalacU = new Ucesnik();
	    				
	    				primalacU.setModel(BigInteger.valueOf(stavka.getModelOdobrenja()));
	    				primalacU.setNaziv(stavka.getPrimalac());
	    				primalacU.setObracunskiRacun(zaOdobriti.getObracunskiRacun());
	    				primalacU.setPNB(stavka.getPNBOdobrenja());
	    				primalacU.setRacun(stavka.getRacunPoverioca());
	    				primalacU.setSWIFT(zaOdobriti.getSWIFT());
	    				
	    				vracaj.setDuznik(duznikU);
	    				vracaj.setPrimalac(primalacU);
	    				System.out.println("Saljem Mt103 za stavku "+vracaj.getIznos());
	    				//poslati mt103 primaocu
	    				try {
	    					posaljiMt103(vracaj, zaOdobriti.getPort());
	    				} catch (Exception e1) {
	    					// TODO Auto-generated catch block
	    					e1.printStackTrace();
	    				}
	    				
	    				//poslati mt910 primaocu
	    				MT910 mt910 = new MT910();
	    				mt910.setIDPoruke("10");
	    				mt910.setDatum(vracaj.getDatumValute());
	    				mt910.setIDPorukeNaloga(vracaj.getIDPoruke());
	    				mt910.setIznos(vracaj.getIznos());
	    				mt910.setRacunBnkPoverioca(zaOdobriti.getObracunskiRacun());
	    				mt910.setSWIFTPoverioca(zaOdobriti.getSWIFT());
	    				mt910.setSifraValute(vracaj.getSifraValute());
	    				
	    				System.out.println("Saljem Mt910 za stavku "+mt910.getIznos());
	    				try {
	    					posaljiMt910(mt910, zaOdobriti.getPort());
	    				} catch (Exception e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    			}else {
	    				//banka nema dovoljno sredstava, odbij
	    			}
	    		}
	        	
	        	
	        	
			}
	        
	        
			}
			catch(Exception e)
			{
				
			}
		}
		
		return response;
	}
	
	private void posaljiMt900(MT900 mt900, String port) throws Exception {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan(ClassUtils.getPackageName(Zahtev900.class));
        marshaller.afterPropertiesSet();
		WebServiceTemplate ws = new WebServiceTemplate(marshaller);
		Zahtev900 request = new Zahtev900();
		request.setMT900Zahtev(mt900);
		Odgovor900 resp = (Odgovor900) ws.marshalSendAndReceive("http://localhost:" + port 
	               + "/ws", request);
	     System.out.println(resp.getMT900Odg());
	}
	
	private void posaljiMt910(MT910 mt910, String port) throws Exception {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan(ClassUtils.getPackageName(Zahtev910.class));
        marshaller.afterPropertiesSet();
		WebServiceTemplate ws = new WebServiceTemplate(marshaller);
		Zahtev910 request = new Zahtev910();
		request.setMT910Zahtev(mt910);
		Odgovor910 resp = (Odgovor910) ws.marshalSendAndReceive("http://localhost:" + port 
	               + "/ws", request);
	     System.out.println(resp.getMT910Odg());
	}
	
	private void posaljiMt103(MT103 nalog, String port) throws Exception {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan(ClassUtils.getPackageName(Zahtev103.class));
        marshaller.afterPropertiesSet();
		WebServiceTemplate ws = new WebServiceTemplate(marshaller);
		Zahtev103 request = new Zahtev103();
		request.setMT103Zahtev(nalog);
		Odgovor103 resp = (Odgovor103) ws.marshalSendAndReceive("http://localhost:" + port 
	               + "/ws", request);
	     System.out.println(resp.getMT103Odg());
	}
}
