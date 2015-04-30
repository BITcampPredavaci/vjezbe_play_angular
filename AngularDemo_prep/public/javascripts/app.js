(function(){
	
	var app = angular.module("movieApp", ["movieService"]);
	
	app.controller("movieController", ["$scope", "Movie", function($scope, Movie){
		
		$scope.movies = Movie.query();
		
		$scope.movie = new Movie();
		
		$scope.add = function(){
				$scope.movie.$save(function(movie){
					$scope.movies.push(movie);	
				$scope.movie = new Movie();
				}, function(error){
					$scope.movie.error = {};
					if(error.data.director){
						$scope.movie.error.director
						 = error.data.director[0];
					}
					if(error.data.title){
						$scope.movie.error.title
						 = error.data.title[0];
					}
				});
				
		};
		
		$scope.edit = function(selectedMovie){
			$scope.movie = selectedMovie;
		};
		
		$scope.delete = function(selectedMovie){
				var index = $scope.movies.indexOf(selectedMovie);
				$scope.movies.splice(index, 1);
				$scope.movie.$remove({id: selectedMovie.id});
		};
		
		
		
	}]);
	
	app.directive("listMovies", function(){
		return {
			templateUrl: "./movie"	
		};
	});
	
	
})();