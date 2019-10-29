angular.module('lunatechApp', [])
    .controller('movieController', ['$scope', '$http', function ($scope, $http) {
        $scope.searchTitle = function () {
            var searchValue = $scope.searchValue;
            if (typeof searchValue == "undefined") {
                searchValue = "titanic";
            }
            $http.get("./title/search?primaryOrOriginalName=" + searchValue)
                .then(function successCallback(response) {
                    console.log(response.data.length);
                    if (response.data.length > 0) {
                        $http.get("http://www.omdbapi.com/?apikey=503adf87&i=" + response.data[0].id)
                            .then(function successCallback(response) {
                                $scope.movieTitle = response.data.Title;
                                $scope.moviePoster = response.data.Poster;
                                $scope.directors = response.data.Director;
                                $scope.writers = response.data.Writer;
                                $scope.stars = response.data.Actors;
                                $scope.summary = response.data.Plot;
                                $scope.rating = response.data.imdbRating;
                                $scope.voteNumber = response.data.imdbVotes;
                                $scope.length = response.data.Runtime;
                                $scope.genre = response.data.Genre;
                                $scope.production = response.data.Production;
                                $scope.released = response.data.Released;
                                $scope.awards = response.data.Awards;

                                $("#movieContent").css({"display": "inline"});
                                $("#notFoundContent").css({"display": "none"});
                            }, function errorCallback(response) {
                                console.log(response);
                            });
                    } else {
                        console.log(response);
                        $("#movieContent").css({"display": "none"});
                        $("#notFoundContent").css({"display": "inline"});
                        $scope.moviePoster = "./images/no-image-available.png";
                    }

                }, function errorCallback(response) {
                    console.log(response);
                });


        };
    }]);
