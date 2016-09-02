/**
* @descve manage http response message display
* @example <alert-success-error></alert-success-error>
*/
(function() {
	'use strict';
	angular
	    .module('odsApp')
	    .directive('responseMessage', responseMessage);
	
	function responseMessage() {
		var directive = {
				templateUrl: 'app/directives/responseMessage/responseMessage.html',
		        restrict: 'E'
		    };
		    return directive;
	}
})();