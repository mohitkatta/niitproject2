app.controller('friendCtrl', function($scope, $location,friendservice) {
   $scope.user={};
	
    $scope.suggest_friend = function() { 
    	
        friendservice.suggest_friend().then(
        function(response){
        	console.log(response.data);
        	$location.path('/suggestedfriends');
        },
        function(err)
        {
        	console.log(err);
        }); 
    };
    
    $scope.suggest_friend();
    
})