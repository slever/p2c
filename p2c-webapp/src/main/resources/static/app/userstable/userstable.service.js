(function() {
	'use strict';

	angular
		.module('odsApp.userstable')
		.factory('userstableService', userstableService);
	
	userstableService.$inject = ['$http'];
	
	function userstableService($http) {
		var service = {
				getUsers:getUsers,
				deleteUser:deleteUser
		}
		return service;
		
		/**
		 * @returns get all users
		 */
		function getUsers() {
			return $http.get('/api/users')
				.then(function(success) {
					return success.data;
				}, function(error) {
					console.error("Error getting /api/users :"+error.status);
					return {message : "Something went wrong :"+error.status };
				});
		}
		
		
		function deleteUser(login) {
			return $http.delete("/api/users/"+login,login).then(function(success) {
				return success.data;
			}, function(error) {
				console.error("Error deletting /api/users :"+error.status);
				return error.data;
			});
		}
	}
})();