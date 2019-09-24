var app = angular.module("myApp", ["ngRoute", 'ngCookies']);
app.config(function($routeProvider,$locationProvider) {
$locationProvider.hashPrefix('');
    $routeProvider
    .when("/", {
        templateUrl : "home.html",
        controller: "notificationCtrl"
    })
    .when("/admin", {
        templateUrl : "admin.html"
    })
    .when("/friend", {
        templateUrl : "friend.html"
    })
    .when("/user", {
    	
        templateUrl : "user.html"
    })
    .when("/signup", {
        templateUrl : "signup.html",
        controller:"userCtrl"
    })
    .when("/signin", {
        templateUrl : "signin.html",
        controller:"userCtrl"
    })
    .when("/suggestedfriends", {
        templateUrl : "suggestedfriends.html",
       controller:'friendCtrl'
    })
    .when("/pendingrequests", {
        templateUrl : "pendingrequests.html",
        	controller:'friendCtrl'
    })
    .when("/friends", {
        templateUrl : "friends.html",
        controller:'friendCtrl'
    })
    .when('/chat', {
		templateUrl : 'chat.html',
		controller : 'ChatCtrl'
	})
	.when('/addblog', {
		templateUrl : 'blogform.html',
		controller : 'BlogCtrl'
	}).when('/blogsnotapproved', {
		templateUrl : 'blogsnotapproved.html',
		controller : 'BlogCtrl'// list of blogs
	}).when('/blogsapproved', {
		templateUrl : 'blogsapproved.html',
		controller : 'BlogCtrl' // list of blogs
	}).when('/getblog/:id', {
		templateUrl : 'blogdetails.html',
		controller : 'BlogDetailsCtrl' // single blog post object-queries
										// getBlog() update blog,add comment
	}).when('/getblognotapproved/:id', {
		templateUrl : 'blogapprovalform.html',
		controller : 'BlogDetailsCtrl' // $scope.blogPost=select*from blogpost
										// wher id=?
	}).when('/getnotification/:id', {
		templateUrl : 'notificationdetails.html',
		controller : 'notificationCtrl'
			
	}).when('/getnotifications/:id', {
		templateUrl : 'notificationdetails.html',
		controller : 'notificationCtrl'
			
	}).when('/updatenotification/:id', {
		templateUrl : 'notificationdetails.html',
		controller : 'notificationCtrl'
			
	}).when('/uploadprofilepic',{
    	templateUrl : 'uploadprofilepic.html'
    
	}).otherwise({
		redirectTo : '/'
	});
});


app.run(function($location, $rootScope, $cookieStore, userservice)
		{
			if ($rootScope.loggedInUser == undefined)
			$rootScope.loggedInUser = $cookieStore.get("signinuser")

			$rootScope.logout = function()
			{
				console.log('entering logout')
				userservice.logout().then(function(response)
				{
					delete $rootScope.loggedInUser;
					$cookieStore.remove('signinuser')
					$rootScope.message = "Successfully loggedout.."
						$location.path("/signin");
				}, function(response) 
					{
						$rootScope.error = response.data
						if (response.status == 401)
							$location.path('/signin')

					})
			}

})