/**
 * 
 */

(function () {
    'use strict';

    angular
        .module('myApp')
        .factory('IzvodService', IzvodService);

    IzvodService.$inject = ['$http'];
    function IzvodService($http) {
        
		function onComplete(response){
			return response.data;
		}
		
		function onError(error) {
	        return function () {
	            return { success: false, message: error };
	        };
	    }
		

		function create(izvodRequest) {
			return $http.post('/izvodi', izvodRequest).then(onComplete, onError('Error getting izvodi!'));
		}
		
		return {
			create: create
	    };
   

    }

})();