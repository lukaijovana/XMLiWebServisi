(function() {
     var app = angular.module("myApp");
    var PrikazFakturaController = function ($scope, FaktureService, NalogService) {
    	FaktureService.getAll().then(function(data) {
    		$scope.fakture = data;
    	});
    	
    	$scope.rowClick = function(fakturaId) {
    		
    		for(var br = 0; br < $scope.fakture.length; br++){
    			if($scope.fakture[br].id == fakturaId) {
    				var faktura = $scope.fakture[br];
    				$scope.selektovanaFaktura = faktura;
    			}
    		}
    		if(faktura) {
    			$('#idPoruke').val(faktura.idPoruke);
    			$('#nazivKupca').val(faktura.nazivKupca);
    			$('#nazivDobavljaca').val(faktura.nazivDobavljaca);
    			$('#pibKupca').val(faktura.pibKupca);
    			$('#pibDobavljaca').val(faktura.pibDobavljaca);
    			$('#adresaKupca').val(faktura.adresaKupca);
    			$('#adresaDobavljaca').val(faktura.adresaDobavljaca);
    			$('#brojRacuna').val(faktura.brojRacuna);
    			$('#datumRacuna').val(faktura.datumRacuna);
    			$('#vrednostRobe').val(faktura.vrednostRobe);
    			$('#vrednostUsluga').val(faktura.vrednostUsluga);
    			$('#ukupnoRobaIUsluge').val(faktura.ukupnoRobeIUsluge);
    			$('#ukupanRabat').val(faktura.ukupanRabat);
    			$('#ukupanPorez').val(faktura.ukupanPorez);
    			$('#oznakaValute').val(faktura.oznakaValute);
    			$('#iznosZaUplatu').val(faktura.iznosZaUplatu);
    			$('#uplataNaRacun').val(faktura.uplataNaRacun);
    			$('#datumValute').val(faktura.datumValute);
    			$scope.stavke = faktura.stavke;
    		}
    	}
    	
    	$scope.posaljiNalog = function() {
    		var hitno;
    		if(document.getElementById('hitno').checked) {
    		    hitno = true;
    		} else {
    		   hitno = false;
    		}
    		NalogService.create($scope.selektovanaFaktura.id, hitno).then(function(n){
    			alert("poslat nalog");
    		});
    	}
    	
    };
    app.controller("PrikazFakturaController", PrikazFakturaController);
})();


