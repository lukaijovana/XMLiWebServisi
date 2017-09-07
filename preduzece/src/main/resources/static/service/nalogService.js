/**
 * 
 */

(function () {
    'use strict';

    angular
        .module('myApp')
        .factory('NalogService', NalogService);

    NalogService.$inject = ['$http'];
    function NalogService($http) {
        
		function onComplete(response){
			return response.data;
		}
		
		function onError(error) {
	        return function () {
	            return { success: false, message: error };
	        };
	    }
		function create(fakturaId, hitno) {
			return $http.post('/nalozi/' + fakturaId, hitno).then(onComplete, onError('Error adding faktura!'));
		}
		
		return {
			create: create
	    };
   

    }

})();