/**
 * 
 */

(function () {
    'use strict';

    angular
        .module('myApp')
        .factory('CounterFakturaService', CounterFakturaService);

    CounterFakturaService.$inject = ['$http'];
    function CounterFakturaService($http) {
        
		function onComplete(response){
			return response.data;
		}
		
		function onError(error) {
	        return function () {
	            return { success: false, message: error };
	        };
	    }
	    	
		
		
		
		function getLast() {
			return $http.get('/counterFaktura').then(onComplete, onError('Error getting fakture!'));
		}
		
		

		return {
			getLast: getLast
	    };
   

    }

})();