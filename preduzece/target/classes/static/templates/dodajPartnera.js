(function() {
     var app = angular.module("myApp");
    var DodajPartneraController = function ($scope, PartneriService) {
    	
    	$scope.dodajPartnera = function() {
    		var partner = {};
    		partner.pib = $('#pib').val();
    		partner.adresa = $('#adresa').val();
    		partner.racun = $('#racun').val();
    		console.log(partner.racun);
    		partner.port = $('#port').val();
    		partner.naziv = $('#naziv').val();
    		PartneriService.create(partner).then(function(a) {
    			window.location = "#!/"
    		});
    	}
    };
    app.controller("DodajPartneraController", DodajPartneraController);
})();