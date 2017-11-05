myApp
		.controller(
				'HomeCtrl',
				[
						'$scope',
						'$resource',
						'$uibModal',
						'$http',
						function($scope, $resource, $uibModal, $http) {

							console.log(window.location);

							var findGetParameter = function(parameterName) {
								var result = null, tmp = [];
								location.search
										.substr(1)
										.split("&")
										.forEach(
												function(item) {
													tmp = item.split("=");
													if (tmp[0] === parameterName)
														result = decodeURIComponent(tmp[1]);
												});
								return result;
							}

							var t = findGetParameter("tokens");

							var u = findGetParameter("url");

							var me = this;
							$scope.title = 'scrapezy';

							$scope.url = u !== null ? u
									: "http://www.bloomberg.com/quote/SPX:IND";

							$scope.tokerns = t !== null ? t
									: "div.price,div.ticker";

							$scope.cmModel = "{ \n \"title\": \"My customized API\",\n \"name\" : \"div.ticker\",\n \"price\": \"div.price\"\n}";

							$scope.editorOptions = {
								lineNumbers : true,
								matchBrackets : true

							};

							var bodyObject = {
								username : "test",
								password : "test"
							};

							$scope.scraptit = function() {
								$http
										.post(
												'api/scrapit?tokens='
														+ $scope.tokerns
														+ '&url=' + $scope.url,
												$scope.cmModel, {})
										.then(
												function(response) {

													var modalInstance = $uibModal
															.open({
																templateUrl : 'apikey-modal.html',
																controller : 'ModalCtrl',
																resolve : {
																	uri : function() {
																		return response.data.uri;
																	},
																	base : function() {
																		return response.data.base;
																	}
																}
															});

													modalInstance.result
															.then(
																	function(
																			selectedItem) {

																	},
																	function() {

																	});

												}, function(response) {
													// / / failure callback
												});
							}

						}

				]);