(function() {
     var app = angular.module("myApp");
    var IndexController = function ($scope, BankaService) {
    	//bla
    	console.log(window.location.href);
    	var tokeni = window.location.href.split(":");
    	var drugiDeo = tokeni[2];
    	var port = drugiDeo.substring(0,4);
    	console.log(port);
    	BankaService.getAll().then(function(resv) {
    		console.log(resv);
    		if(!resv || resv.length == 0){
    			//http://localhost:8085/index.html
				BankaService.create(port).then(function(v) {
		    		
		    	});
    		}else {
    			console.log("e")
    		}
    	})
    	
    };
    app.controller("IndexController", IndexController);
})();