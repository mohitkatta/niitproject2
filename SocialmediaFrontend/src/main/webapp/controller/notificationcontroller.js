/**
 * 
 */
app.controller(
				'notificationCtrl',
				function($scope, $location, $rootScope, $routeParams,
						NotificationService) {
					var id = $routeParams.id;
					function getNotificationsNotViewed() {
						console.log("id in notification ctrler is " + id);
						NotificationService
								.getNotificationsNotViewed()
								.then(
										function(response) {
											$rootScope.notifications = response.data;
											$rootScope.notificationCount = $rootScope.notifications.length;
										}, function(response) {
											$rootScope.error = response.data
											if (response.status == 401)
												$location.path('/signin')
										})
					}

					if (id != undefined) {
						console.log("id in notification ctrler is " + id);
						NotificationService.getNotifications(id).then(
								function(response) {
									$scope.notification = response.data
								}, function(response) {
									$rootScope.error = response.data
									if (response.status == 401)
										$location.path('/signin')
								});
						
						NotificationService.updateNotification(id).then(
								
								function(response) {
									console.log("id in updatenotification "+id)
									$scope.notification=response.data;
									getNotificationsNotViewed();
									
								}, function(response) {
									$rootScope.error = response.data
									console.log("error in updatenotification")
									if (response.status == 401)
										$location.path('/signin')
								})
					}
					
					
					getNotificationsNotViewed()

				})