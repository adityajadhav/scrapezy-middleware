'use strict';

var myApp = angular.module('myApp',
		[ 'ui.router', 'chart.js', 'ngResource', 'ui.bootstrap','ui.codemirror' ])

.config(function($stateProvider, $locationProvider) {
	$stateProvider.state({
		name : 'home',
		url : '/',
		templateUrl : 'home.html',
		controller : 'HomeCtrl'
	});
	$locationProvider.html5Mode(true);
})

.config([ '$resourceProvider', function($resourceProvider) {
	// Don't strip trailing slashes from calculated URLs
	$resourceProvider.defaults.stripTrailingSlashes = false;
} ]);
