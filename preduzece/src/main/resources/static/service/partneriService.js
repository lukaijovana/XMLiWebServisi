/**
 * 
 */

(function () {
    'use strict';

    angular
        .module('myApp')
        .factory('PartneriService', PartneriService);

    PartneriService.$inject = ['$http'];
    function PartneriService($http) {
		function onComplete(response){
			return response.data;
		}
		function onError(error) {
	        return function () {
	            return { success: false, message: error };
	        };
	    }
		function create(partner) {
			return $http.post('/partneri', partner).then(onComplete, onError('Error adding partner!'));
		}
		
		function getAll() {
			return $http.get('/partneri').then(onComplete, onError('Error getting partner!'));
		}
		
		return {
			create: create,
			getAll: getAll
	    };
   

    }

})();