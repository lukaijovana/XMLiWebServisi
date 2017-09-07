(function() {
     var app = angular.module("myApp");
    var KreirajFakturuController = function ($scope, BankaService, FaktureService, StavkeService, CounterFakturaService, PartneriService) {
    	$scope.redniBrojStavke = 1;
    	var stopaPDV = 20;
    	//podesavanje da se datum na datepicker-u vezanom za datum valute menja
    	//max vrednost koju moze da primi taj datepicker u zavisnosti od
    	//odabrane vrednosti datepicker-a vezanog za racun
		  function getDate( element ) {
			 var dateFormat = "yy-mm-dd"
		      var date;
		      try {
		        date = $.datepicker.parseDate( dateFormat, element.value );
		      } catch( error ) {
		        date = null;
		      }
		 
		      return date;
		    }
		  $('#datepicker').datepicker({ dateFormat: 'yy-mm-dd' });
		  $('#datepickerRacun').datepicker({ dateFormat: 'yy-mm-dd' });
		
		 //datum racuna ne sme biti veci od trenutnog datuma
		 $( "#datepickerRacun" ).datepicker({ minDate: new Date(2016, 12, 1), maxDate: "+0M +0D" });
		 //datum valute ne sme biti manji od datuma racuna
		 $('#datepickerRacun').on( "change", function() {
		        $('#datepicker').datepicker( "option", "minDate", getDate( this ) );
	     });
	     $( "#datepicker" ).datepicker({ minDate: new Date(2016, 12, 1) });
	     
	     //ukoliko se unese procenat rabata onemoguciti unos u polje za iznos rabata 
	     $("#procenatRabataStavke").on("change", function() {
	    	 if($('#procenatRabataStavke').val()) {
	    		 $('#iznosRabataStavke').attr('disabled','disabled');
	    		 //ukoliko je uneta vrednost, iznos rabata je vrednost * procenat rabata / 100
	    		 if($('#vrednostStavke').val()) {
	    			 var vrednost = $('#vrednostStavke').val();
	    			 var procenat = $('#procenatRabataStavke').val();
	    			 var iznosRabata = vrednost * procenat / 100;
	    			 $('#iznosRabataStavke').val(iznosRabata);
	    			 var umanjeno = vrednost - iznosRabata;
	    			 $('#umanjenoZaRabatStavke').val(umanjeno);
	    			 var ukupanPorez = umanjeno * stopaPDV / 100;
	    			 $('#ukupanPorezStavke').val(ukupanPorez);
	    		 }
	    		 
	    	 }else {
	    		 $('#iznosRabataStavke').removeAttr('disabled');
	    	 }
	    });
	     
	     
	     BankaService.getAll().then(function(odg) {
	    	console.log(odg);
	    	$('#nazivDobavljaca').val(odg[0].naziv);
	    	$('#pibDobavljaca').val(odg[0].pib);
	    	$('#adresaDobavljaca').val(odg[0].adresa);
	    	$('#uplataNaRacun').val(odg[0].racun);
	     });
	     
	     PartneriService.getAll().then(function(ood) {
	    	 $scope.partneri = ood;
	     });
	     
	     $scope.partnerSelect = function(partnerId) {
	    	 $scope.selektovaniPartner = partnerId;
	     }
	     
	     $scope.izaberiPartnera = function() {
	    	 for(var ij = 0; ij < $scope.partneri.length; ij++) {
	    		 if($scope.partneri[ij].id == $scope.selektovaniPartner) {
	    			 $('#nazivKupca').val($scope.partneri[ij].naziv);
	    			 $('#pibKupca').val($scope.partneri[ij].pib);
	    			 $('#adresaKupca').val($scope.partneri[ij].adresa);
	    		 }
	    	 }
	    	 $('#partnerModal').modal('toggle');
	     }
	     
	     //na promenu cene isto treba da se menja polje iznos rabata
	    $("#jedinicnaCenaStavke").on("change", function() {
	    	 if($('#jedinicnaCenaStavke').val()) {
	    		 var jedinicnaCena = $('#jedinicnaCenaStavke').val()
	    		 if($('#kolicinaStavke').val()) {
	    			 var kolicina = $('#kolicinaStavke').val();
		    		 var vrednost = jedinicnaCena * kolicina;
		    		 $('#vrednostStavke').val(vrednost);
		    		 var procenat = $('#procenatRabataStavke').val();
		    		 if(procenat) {
		    			 var iznosRabata = vrednost * procenat / 100;
		    			 $('#iznosRabataStavke').val(iznosRabata);
		    			 var umanjeno = vrednost - iznosRabata;
		    			 $('#umanjenoZaRabatStavke').val(umanjeno);
		    			 var ukupanPorez = umanjeno * stopaPDV / 100;
		    			 $('#ukupanPorezStavke').val(ukupanPorez);
		    		 }else {
		    			 if($('#iznosRabataStavke').val()) {
		    				 var iznosR = $('#iznosRabataStavke').val(); 
		    				 var umanjeno = vrednost - iznosR;
			    			 $('#umanjenoZaRabatStavke').val(umanjeno);
			    			 var ukupanPorez = umanjeno * stopaPDV / 100;
			    			 $('#ukupanPorezStavke').val(ukupanPorez);
		    			 }
		    		 }
	    		 }else {
	    			 $('#vrednostStavke').val(jedinicnaCena);
	    			 var vrednost = jedinicnaCena;
	    			 var procenat = $('#procenatRabataStavke').val();
		    		 if(procenat) {
		    			 var iznosRabata = vrednost * procenat / 100;
		    			 $('#iznosRabataStavke').val(iznosRabata);
		    			 var umanjeno = vrednost - iznosRabata;
		    			 $('#umanjenoZaRabatStavke').val(umanjeno);
		    			 var ukupanPorez = umanjeno * stopaPDV / 100;
		    			 $('#ukupanPorezStavke').val(ukupanPorez);
		    		 }else {
		    			 if($('#iznosRabataStavke').val()) {
		    				 var iznosR = $('#iznosRabataStavke').val(); 
		    				 var umanjeno = vrednost - iznosR;
			    			 $('#umanjenoZaRabatStavke').val(umanjeno);
			    			 var ukupanPorez = umanjeno * stopaPDV / 100;
			    			 $('#ukupanPorezStavke').val(ukupanPorez);
		    			 }
		    		 }
	    		 }
	    	 }else{
	    		 $('#iznosRabataStavke').val("");
	    	 }
	    });
	    
	    //i na promenu kolicine izmeniti sva polja kao u slucaju promene cene
	    $("#kolicinaStavke").on("change", function() {
	    	 if($('#kolicinaStavke').val()) {
	    		 var kolicina = $('#kolicinaStavke').val()
	    		 if($('#jedinicnaCenaStavke').val()) {
	    			 var jedinicnaCena = $('#jedinicnaCenaStavke').val();
		    		 var vrednost = jedinicnaCena * kolicina;
		    		 $('#vrednostStavke').val(vrednost);
		    		 var procenat = $('#procenatRabataStavke').val();
		    		 if(procenat) {
		    			 var iznosRabata = vrednost * procenat / 100;
		    			 $('#iznosRabataStavke').val(iznosRabata);
		    			 var umanjeno = vrednost - iznosRabata;
		    			 $('#umanjenoZaRabatStavke').val(umanjeno);
		    			 var ukupanPorez = umanjeno * stopaPDV / 100;
		    			 $('#ukupanPorezStavke').val(ukupanPorez);
		    		 }else {
		    			 if($('#iznosRabataStavke').val()) {
		    				 var iznosR = $('#iznosRabataStavke').val(); 
		    				 var umanjeno = vrednost - iznosR;
			    			 $('#umanjenoZaRabatStavke').val(umanjeno);
			    			 var ukupanPorez = umanjeno * stopaPDV / 100;
			    			 $('#ukupanPorezStavke').val(ukupanPorez);
		    			 }
		    		 }
	    		 }else {
	    			 $('#vrednostStavke').val("");
	    			 $('#iznosRabataStavke').val("");
	    		 }
	    	 }else{
	    		//racunaj po default-u da je kolicina 1
	    		$('#vrednostStavke').val($('#jedinicnaCenaStavke').val());
	    		var vr = $('#vrednostStavke').val();
	    		 var procenat = $('#procenatRabataStavke').val();
	    		 if(procenat) {
	    			 var iznosRabata = vr * procenat / 100;
	    			 $('#iznosRabataStavke').val(iznosRabata);
	    			 var umanjeno = vr - iznosRabata;
	    			 $('#umanjenoZaRabatStavke').val(umanjeno);
	    			 var ukupanPorez = umanjeno * stopaPDV / 100;
	    			 $('#ukupanPorezStavke').val(ukupanPorez);
	    		 }else {
	    			 if($('#iznosRabataStavke').val()) {
	    				 var iznosR = $('#iznosRabataStavke').val(); 
	    				 var umanjeno = vr - iznosR;
		    			 $('#umanjenoZaRabatStavke').val(umanjeno);
		    			 var ukupanPorez = umanjeno * stopaPDV / 100;
		    			 $('#ukupanPorezStavke').val(ukupanPorez);
	    			 }
	    		 }
	    	 }
	    });
	    
	    //na promenu iznosa rabata promeni umanjeno za rabat
	    $("#iznosRabataStavke").on("change", function() {
	    	if($('#iznosRabataStavke').val()) {
	    		var iznosRabata = $('#iznosRabataStavke').val();
	    		if($('#vrednostStavke').val()) {
	    			var vrednost = $('#vrednostStavke').val();
	    			var umanjeno = vrednost - iznosRabata;
	    			$('#umanjenoZaRabatStavke').val(umanjeno);
	    			var ukupanPorez = umanjeno * stopaPDV / 100;
	    			 $('#ukupanPorezStavke').val(ukupanPorez);
	    		}
	    	}
	    });
	    
	    //kada klikne na kreiraj stavku, povecaj rednibroj fakture
	    $scope.kreirajStavku = function() {
	    	//validacija unosa
	    	var stavka = {};
	    	var isOk = true;
	    	stavka.redniBroj = $scope.redniBrojStavke;
	    	if($('#nazivRobeStavke').val()) {
	    		stavka.nazivRobe = $('#nazivRobeStavke').val();
	    	}else {
	    		isOk = false;
	    	}
	    	
	    	if($('#kolicinaStavke').val()) {
	    		if($('#kolicinaStavke').val() < 0) {
	    			isOk = false;
	    		}else{
	    			stavka.kolicina = $('#kolicinaStavke').val();
	    		}
	    	}else{
	    		stavka.kolicina = 1;
	    	}
	    	
	    	if($('#jedinicaMereStavke').val()) {
	    		stavka.jedinicaMere = $('#jedinicaMereStavke').val();
	    	}else {
	    		isOk = false;
	    	}
	    	
	    	if($('#jedinicnaCenaStavke').val()) {
	    		if($('#jedinicnaCenaStavke').val() < 0) {
	    			isOk = false;
	    		}else {
	    			stavka.jedinicnaCena = $('#jedinicnaCenaStavke').val();
	    		}
	    	}else {
	    		isOk = false;
	    	}
	    	
	    	if($('#vrednostStavke').val()) {
	    		stavka.vrednost = $('#vrednostStavke').val()
	    	}else {
	    		isOk = false;
	    	}
	    	
	    	if($('#procenatRabataStavke').val()) {
	    		stavka.procenatRabata = $('#procenatRabataStavke').val();
	    	}else {
	    		if($('#iznosRabataStavke').val()) {
		    		//
		    	}else {
		    		isOk = false;
		    	}
	    	}
	    	if($('#iznosRabataStavke').val()) {
	    		stavka.iznosRabata = $('#iznosRabataStavke').val();
	    	}else {
	    		isOk = false;
	    	}
	    	if($('#umanjenoZaRabatStavke').val()) {
	    		stavka.umanjenoZaRabat = $('#umanjenoZaRabatStavke').val();
	    	}
	    	if($('#ukupanPorezStavke').val()) {
	    		stavka.ukupanPorez = $('#ukupanPorezStavke').val();
	    	}
	    	if(isOk) {
	    		if($scope.redniBrojStavke == 1) {
	    			$scope.stavke = [];
	    		}
	    		
	    		$scope.redniBrojStavke = $scope.redniBrojStavke + 1;
	    		
	    		if($scope.ukupnoRobeIUsluga) {
		    		var uk =  $scope.ukupnoRobeIUsluga + parseInt(stavka.vrednost);
		    		$scope.ukupnoRobeIUsluga = parseInt(uk);
	    		}else{
	    			$scope.ukupnoRobeIUsluga = parseInt(stavka.vrednost);
	    		}
	    		if($scope.ukupanRabat) {
		    		var rr =  $scope.ukupanRabat + parseFloat(stavka.iznosRabata);
		    		$scope.ukupanRabat = parseFloat(rr);
	    		}else{
	    			$scope.ukupanRabat = parseFloat(stavka.iznosRabata);
	    		}
	    		if($scope.ukupanPorez) {
		    		var pp =  $scope.ukupanPorez + parseFloat(stavka.ukupanPorez);
		    		$scope.ukupanPorez = parseFloat(pp);
	    		}else{
	    			$scope.ukupanPorez = parseFloat(stavka.ukupanPorez);
	    		}
	    		/*StavkeService.create(stavka).then(function(dd) {
	    			$scope.stavke.push(dd);
	    			$('#stavkaModal').modal('toggle');
	    		});*/
	    		$scope.stavke.push(stavka);
	    		$('#stavkaModal').modal('toggle');
	    		
	    	}
	    }
	    CounterFakturaService.getLast().then(function(la) {
	    	$scope.idPoruke = la + 1;
	    });
	    $scope.posaljiFakturu = function() {
	    	var faktura = {};
	    	var tokeni = window.location.href.split(":");
	    	var drugiDeo = tokeni[2];
	    	var port = drugiDeo.substring(0,4);
	    	faktura.idPoruke = port + $scope.idPoruke.toString();
	    	faktura.nazivKupca = $('#nazivKupca').val();
	    	faktura.nazivDobavljaca = $('#nazivDobavljaca').val();
	    	faktura.pibKupca = $('#pibKupca').val();
	    	faktura.pibDobavljaca = $('#pibDobavljaca').val();
	    	faktura.adresaKupca = $('#adresaKupca').val();
	    	faktura.adresaDobavljaca = $('#adresaDobavljaca').val();
	    	faktura.brojRacuna = $scope.idPoruke;
	    	faktura.datumRacuna = $('#datepickerRacun').datepicker('getDate');
	    	faktura.vrednostRobe = $('#vrednostRobe').val();
	    	faktura.vrednostUsluga = $('#vrednostUsluga').val();
	    	faktura.ukupnoRobeIUsluge = $('#ukupnoRobaIUsluge').val();
	    	faktura.ukupanRabat = $('#ukupanRabat').val();
	    	faktura.ukupanPorez = $('#ukupanPorez').val();
	    	faktura.oznakaValute = $('#oznakaValute').val();
	    	faktura.iznosZaUplatu = $('#iznosZaUplatu').val();
	    	faktura.uplataNaRacun = $('#uplataNaRacun').val();
	    	faktura.datumValute = $('#datepicker').val();
	    	faktura.stavke = $scope.stavke;
	    	FaktureService.create(faktura).then(function(res) {
	    		alert("eee");
	    	});
	    }
	    
	    
    };
    app.controller("KreirajFakturuController", KreirajFakturuController);
})();