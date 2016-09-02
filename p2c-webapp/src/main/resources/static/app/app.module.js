(function() {
    'use strict';
        
    angular.module('odsApp', [
	    'odsApp.login',
	    'odsApp.userstable',
	    'odsApp.newuser',
	    'ngRoute'
	    ]).run(run);
    
    run.$inject=['$rootScope', '$location', '$window'];
    
    function run($rootScope, $location, $window) {
    	// keep user logged in after page refresh
    	$rootScope.authUser = JSON.parse($window.sessionStorage.getItem('authUser'));
    	if($rootScope.authUser) {
    		$rootScope.isAuthenticated=true;
    	}
    	  
    	$rootScope.$on('$locationChangeStart', function () {
    	     // redirect to login page if not logged in
    	     if ($location.path() !== '/login' && !$rootScope.authUser) {
    	         $location.path('/login');
    	     }
    	});
    }
    
})();