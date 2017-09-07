/**
 * 
 */

(function () {
    'use strict';

    angular
        .module('myApp')
        .factory('StavkeService', StavkeService);

    StavkeService.$inject = ['$http'];
    function StavkeService($http) {
        
		function onComplete(response){
			return response.data;
		}
		
		function onError(error) {
	        return function () {
	            return { success: false, message: error };
	        };
	    }
	    	
		
		function create(stavka) {
			return $http.post('/stavke', stavka).then(onComplete, onError('Error adding drzava!'));
		}
		
		

		return {
			create: create
	    };
   

    }

})();