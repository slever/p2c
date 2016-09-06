(function() {
	'use strict';

	angular
		.module('odsApp.newuser')
		.factory('newuserService', newuserService);
	
	newuserService.$inject = ['$http'];
	
	function newuserService($http) {
		var service = {
			addUser:addUser
		}
		return service;
		
		/**
		 * Send the new user to server
		 */
		function addUser(user) {
			return $http.post("/api/users",user).then(function(success) {
				return success.data;
			}, function(error) {
				return error.data;
			});
		}
		
	}
})();