(function() {
     var app = angular.module("myApp");
    var PreuzmiIzvodController = function ($scope, IzvodService) {
    	
		$('#datepicker').datepicker({ dateFormat: 'yy-mm-dd' });
    	
    	$scope.posaljiZahtev = function() {
    		
    		var zahtev = {};
    		
    		zahtev.datum = $('#datepicker').val();
    		zahtev.racun = $('#racun').val();
    		zahtev.rbr = $('#rbrPreseka').val();

    		IzvodService.create(zahtev).then(function(data) {
        		$scope.izvodi = data;
        		$scope.izvodi.datumNaloga = new Date($scope.izvodi.datumNaloga);
        		
        		var stavkeP = $scope.izvodi.stavke;
        		
        		for(var i in stavkeP)
        		{
        		     stavkeP[i].datumNaloga = new Date(stavkeP[i].datumNaloga);
        		     stavkeP[i].datumValute = new Date(stavkeP[i].datumValute);
        		}
        		
        		$scope.izvodi.stavke = stavkeP;
        	});
    	}
    	
    };
    app.controller("PreuzmiIzvodController", PreuzmiIzvodController);
})();


