(function() {
	'use strict';

	angular.module('odsApp.userstable').controller("UserstableController",
			UserstableController);

	UserstableController.$inject = [ 'userstableService' ];

	function UserstableController(userstableService) {
		var vm = this;
		vm.users = {
			firstName : '',
			lastName : '',
			email : '',
			roles : [],
			login : ''
		};
		vm.pageno = 1; // initialize page no to 1
		vm.total_count = 0;
		vm.itemsPerPage = 10;
		vm.deleteUser = deleteUser;
		
	
		vm.getData = function(pageno) {
			userstableService.getUsers().then(function(data) {
				console.log('get users à la page ' + pageno );
				for (var i = 0; i < vm.itemsPerPage; i++) {
					var j = i + vm.itemsPerPage * (pageno - 1);
					vm.users[i] = data[j];
				}
				vm.total_count = data.length;
			});
		};
		vm.getData(vm.pageno);

		
		
		function deleteUser (login) {
			console.log('delete an user');
			userstableService.deleteUser(login).then(function(data){
				if(data.error){
					vm.error = "Erreur:"+data.message; 
				} else {
					vm.message = "Votre utilisateur à bien été supprimé de la base de donnée";	
				}
			});
		}
	}
})();
