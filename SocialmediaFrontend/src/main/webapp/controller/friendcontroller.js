app.controller('friendCtrl', function($scope, $location,friendservice) 
{
   $scope.user={};
   $scope.suggestedfriends=[];
   $scope.pendingrequets=[];
   console.log("friends ctrl");
	
    $scope.suggest_friend = function()
    { 
    	console.log('calling suggested users')
        friendservice.suggest_friend().then(
        function(response)
        {
        	console.log(response.data);
        	$scope.suggestedfriends=response.data;
        	//$location.path('/pendingrequests');
        },
        function(err)
        {
        	console.log(err);
        }); 
    };
    
    $scope.suggest_friend();
    
    $scope.send_request = function(email)
    { 
    	
        friendservice.send_request(email).then(
        function(response)
        {
        	console.log(response.data);
        	
        	$scope.suggest_friend();
        	
        },
        function(err)
        {
        	console.log(err);
        }); 
    };
    
    $scope.pending_requests = function()
    { 
    	
        friendservice.pending_requests().then(
        function(response)
        {
        	console.log(response.data);
        	$scope.pendingrequests=response.data;
        	
        },
        function(err)
        {
        	console.log(err);
        }); 
    };
    
    $scope.pending_requests();
    
    
    $scope.accept_request = function(friend,status)
    { 
    	
        friendservice.accept_request(friend,status).then(
        function(response)
        {
        	console.log(response.data);
        	$scope.acceptrequest=response.data;
        	$scope.pending_requests();
        	$location.path('/pendingrequests');
        },
        function(err)
        {
        	console.log(err);
        }); 
    };
    

    $scope.friends_list = function()
    { 
    	
        friendservice.friends_list().then(
        function(response)
        {
        	console.log(response.data);
        	$scope.friendslist=response.data;
        	
        },
        function(err)
        {
        	console.log(err);
        }); 
    };
    
    $scope.friends_list();
})