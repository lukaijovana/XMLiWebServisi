/**
 * 
 */

(function () {
    'use strict';

    angular
        .module('myApp')
        .factory('BankaService', BankaService);

    BankaService.$inject = ['$http'];
    function BankaService($http) {
        
		function onComplete(response){
			return response.data;
		}
		
		function onError(error) {
	        return function () {
	            return { success: false, message: error };
	        };
	    }
	    	
		
		
		function create(port) {
			return $http.post('/banka', port).then(onComplete, onError('Error adding faktura!'));
		}
		function getAll() {
			return $http.get('/banka').then(onComplete, onError('Error getting fakture!'));
		}
		
		

		return {
			create: create,
			getAll: getAll
	    };
   

    }

})();