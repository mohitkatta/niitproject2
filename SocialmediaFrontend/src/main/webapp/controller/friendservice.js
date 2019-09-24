app.service('friendservice', function($http) {
	
  this.suggest_friend = function () {
    return $http.get("http://localhost:8080/SocialmediaMiddleware/suggestedFriends");
  }
  
  this.send_request = function (email) {
	  console.log(email);
	    return $http.post("http://localhost:8080/SocialmediaMiddleware/registerfriend",email);
	  }
  this.pending_requests=function() {
	  return $http.get("http://localhost:8080/SocialmediaMiddleware/pendingrequests");
  }
  
  this.accept_request=function(friend,status){
	  console.log(friend,status);
	  return $http.put("http://localhost:8080/SocialmediaMiddleware/acceptrequest/"+status,friend);
  }
  
  this.friends_list=function(){
	 
	  return $http.get("http://localhost:8080/SocialmediaMiddleware/friendslist");
  }
  
  
});