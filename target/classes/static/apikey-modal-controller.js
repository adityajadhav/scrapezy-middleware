myApp.controller('ModalCtrl', [ '$scope', '$resource', '$http', 'uri', 'base',
		function($scope, $resource, $http, uri, base) {
			$scope.uri = uri;
			$scope.base = base;

		} ]);