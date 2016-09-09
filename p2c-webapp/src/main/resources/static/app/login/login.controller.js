(function () {
    'use strict';

    angular
    	.module('odsApp.login')
        .controller("LoginController", LoginController);
    
    LoginController.$inject = ['$http', '$window','$rootScope','$location'];
    
    function LoginController($http, $window, $rootScope, $location) {
        var vm = this;

        vm.dataLoading = false;
        vm.logout = logout;
        vm.submit = submit;
        vm.user = {login:'', password:''};
		vm.message = '';
		vm.error = '';
		
        function submit() {
        	vm.dataLoading=true;
        	var headers = vm.user && vm.user.login && vm.user.login.trim() ? 
        			{ authorization : "Basic " + btoa(vm.user.login + ":" + vm.user.password) } : {};
			
			return $http.get('/user', {
	            headers : headers
	          })
	          .then(function(response) {
	        	  vm.dataLoading=false;
	      		  vm.message = 'connection succeeded';
	      		  vm.error = '';
	        	  $rootScope.isAuthenticated = true;
	        	  $rootScope.authUser = {
	        			  username: response.data.principal.username,
	        			  authorities: response.data.principal.authorities,
	                      fullname: response.data.principal.fullName
	              };
	        		        	  
	        	  $window.sessionStorage.setItem('authUser', JSON.stringify($rootScope.authUser));
	        	  
	        	  $location.path('/');
	          }, function() {
	      		  vm.message = '';
	      		  vm.error = 'Wrong login or password';
	        	  vm.dataLoading=false;
	        	  delete $window.sessionStorage.token;
	        	  $rootScope.isAuthenticated = false;
                  vm.error = 'Error: Invalid user or invalid password';
	          });
        }

        function logout() {
        	return $http
	    		.post('/logout', {})
	    		.finally(function() {
	                 $rootScope.isAuthenticated = false;
	                 delete $rootScope.authUser;
	                 delete $window.sessionStorage.authUser
	                 $location.path("/");
	    		});
        }
    }
})();