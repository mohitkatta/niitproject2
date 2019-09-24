app.service('friendservice', function($http) {
	
  this.suggest_friend = function () {
    return $http.get("http://localhost:8080/SocialmediaMiddleware/suggestedFriends");
  }
});