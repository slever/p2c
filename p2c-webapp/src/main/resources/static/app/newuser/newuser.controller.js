(function () {
	'use strict';

	angular
		.module('odsApp.newuser')
		.controller('NewuserController', NewuserController);
	
	NewuserController.$inject = ['newuserService'];
	
	function NewuserController(newuserService) {
		var vm = this;
		
		vm.user = {firstName: '' ,lastName: '',email:'', mobile:'', roles:[]};
		vm.roles = ['CONSUMER' 
				, 'PRODUCER'
				, 'TRANSPORTER'
				, 'ADMIN' ];
		vm.message = '';
		vm.submit=submit;
		vm.toggleSelection = toggleSelection;

		function submit () {
			console.log('submit newuser form');
			newuserService.addUser(vm.user).then(function(data){
				if(data.error){
					vm.error = "Erreur:"+data.message; 
					vm.message = "";	
				} else {
					vm.message = "Your user has been added to the database";	
					vm.error = "";
					vm.user.firstName='';
					vm.user.lastName='';
					vm.user.email='';
					vm.user.mobile='';
					vm.user.roles=[];
				}
			});
		}
		
		function toggleSelection(role) {
		    var idx = vm.user.roles.indexOf(role);
		    if (idx > -1) {
		    	vm.user.roles.splice(idx, 1);
		    } else {
		    	vm.user.roles.push(role);
		    }
		}
	}
})();
