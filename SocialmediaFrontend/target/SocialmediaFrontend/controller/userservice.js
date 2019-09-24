app.service('userservice', function($http) {
	
  this.register = function (user) {
    return $http.post("http://localhost:8080/SocialmediaMiddleware/usersignup",user);
  }
  
  this.login = function (user) {
	    return $http.post("http://localhost:8080/SocialmediaMiddleware/usersignin",user);
	  }
  this.logout= function () {
	    return $http.get("http://localhost:8080/SocialmediaMiddleware/logout");
	  }
  
  
});