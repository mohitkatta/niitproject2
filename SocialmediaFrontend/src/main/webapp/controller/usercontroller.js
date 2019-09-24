app.controller('userCtrl', function($scope, $location,userservice,$rootScope,$cookieStore) {
   $scope.user={};
	
    $scope.register = function(user) { 
    	$scope.user=user;
    	console.log($scope.user);
        userservice.register($scope.user).then(
        function(response){
        	console.log(response.data);
        	$location.path('/signin');
        },
        function(err)
        {
        	console.log(err);
        }); 
    };
    
    $scope.login = function(user) { 
    	$scope.user=user;
    	console.log($scope.user);
    	
        userservice.login($scope.user).then(
        function(response){
        	console.log(JSON.stringify(response.data));
        	$rootScope.loggedInUser = response.data;
			$cookieStore.put("signinuser", response.data);
        	$location.path('/');
        },
        function(err)
        {
        	console.log(err);
        }); 
    };
    
//    $scope.logout = function() { 
//    	console.log('logging out');
//        userservice.logout().then(
//        function(response){
//        	console.log(JSON.stringify(response.data));
//        	$rootScope.loggedInUser = response.data; //user object
//			$cookieStore.put('signinuser', response.data);
//        	$location.path('/signin');
//        },
//        function(err)
//        {
//        	console.log(err);
//        }); 
//    };
//    
    
    
    
});


