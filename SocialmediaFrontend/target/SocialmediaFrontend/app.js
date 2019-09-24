var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider,$locationProvider) {
$locationProvider.hashPrefix('');
    $routeProvider
    .when("/", {
        templateUrl : "home.html"
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
        templateUrl : "pendingrequests.html"
    })
    .when("/friends", {
        templateUrl : "friends.html"
    });
});