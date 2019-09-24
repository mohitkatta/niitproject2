/**
 * 
 */
app.factory('NotificationService',function($http){
	var notificationService={}
			notificationService.getNotificationsNotViewed=function(){
				return $http.get("http://localhost:8080/SocialmediaMiddleware/getnotifications");
			}
	notificationService.getNotifications=function(id){
		console.log("id is "+id);
		return $http.get("http://localhost:8080/SocialmediaMiddleware/getnotification/"+id);
	}
	notificationService.updateNotification=function(id){
		return $http.put("http://localhost:8080/SocialmediaMiddleware/updateNotification/"+id);
	}
	return notificationService;
})