(function(){
	
	angular.module("movieService", ["ngResource"])
	.factory("Movie", ["$resource", function($resource){
		return $resource("/api/movie/:id");
	}]);
	
})();