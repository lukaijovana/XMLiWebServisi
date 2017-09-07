/**
 * 
 */

(function () {
    'use strict';

    angular
        .module('myApp')
        .factory('FaktureService', FaktureService);

    FaktureService.$inject = ['$http'];
    function FaktureService($http) {
        
		function onComplete(response){
			return response.data;
		}
		
		function onError(error) {
	        return function () {
	            return { success: false, message: error };
	        };
	    }
	    	
		
		
		function create(faktura) {
			return $http.post('/fakture', faktura).then(onComplete, onError('Error adding faktura!'));
		}
		function getAll() {
			return $http.get('/fakture').then(onComplete, onError('Error getting fakture!'));
		}
		
		

		return {
			create: create,
			getAll: getAll
	    };
   

    }

})();