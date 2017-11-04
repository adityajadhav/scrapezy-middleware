myApp
		.controller(
				'HomeCtrl',
				[
						'$scope',
						'$resource',
						'$uibModal',
						'$http',
						function($scope, $resource, $uibModal, $http) {

							var me = this;
							$scope.title = 'Scrapit';

							$scope.url = "http://www.bloomberg.com/quote/SPX:IND";

							$scope.tokerns = "div.price,div.ticker";

							$scope.cmModel = "{ \n \"name\" : \"div.ticker\",\n \"price\":\"div.price\"\n }";

							$scope.editorOptions = {
								lineNumbers : true,
								matchBrackets : true

							};

							var bodyObject = {
								username : "test",
								password : "test"
							};

							$scope.scraptit = function() {
								$http.post(
										'api/scrapit?tokens=' + $scope.tokerns
												+ '&url=' + $scope.url,
										$scope.cmModel, {}).then(
										function(response) {
											// / / success callback
										}, function(response) {
											// / / failure callback
										});
							}

						}

				]);