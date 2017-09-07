(function () {
    var app = angular.module("myApp", ['ngRoute']);
    
    app.config(function ($routeProvider) {
        $routeProvider
        	//Greeting templates
            .when("/kreirajFakturu", {
                templateUrl: "templates/kreirajFakturu.html",
                controller: "KreirajFakturuController"
            })
            .when("/primljeneFakture", {
                templateUrl: "templates/prikazFaktura.html",
                controller: "PrikazFakturaController"
            })
            .when("/dodajPartnera", {
                templateUrl: "templates/dodajPartnera.html",
                controller: "DodajPartneraController"
            })
            .when("/preuzmiIzvod", {
                templateUrl: "templates/preuzmiIzvod.html",
                controller: "PreuzmiIzvodController"
            })
            .otherwise({
            	url: '/',
                templateUrl:"templates/home.html",
                controller: "HomeController"
            });
          
    });
}());