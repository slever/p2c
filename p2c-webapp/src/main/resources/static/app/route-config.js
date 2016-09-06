(function() {
	'use strict';

	angular.module('odsApp').config(config);

	config.$inject=['$routeProvider'];
	
	function config($routeProvider) {
		$routeProvider

		.when("/login", {
			templateUrl : 'app/login/login.html',
			controller : 'LoginController',
			controllerAs : 'vm'
		})
		
		.when("/newuser", {
			templateUrl : 'app/newuser/newuser.html',
			controller : 'NewuserController',
			controllerAs : 'vm'
		})
		
		.when("/listUsers", {
			templateUrl : 'app/userstable/userstable.html',
			controller : 'UserstableController',
			controllerAs : 'vm'
		})

		.otherwise( {redirectTo:'/login' } );

	}
})();