/**
 * 
 */
app.factory('BlogService',function($http){
	var blogService={}
	
	blogService.addBlog=function(blog){
		console.log(blog.blogContent);
		return $http.post("http://localhost:8080/SocialmediaMiddleware/addBlog",blog);
	}
	blogService.getBlogsWaitingForApproval=function(){
		return $http.get("http://localhost:8080/SocialmediaMiddleware/getblogs/"+0)
	}
	blogService.getBlogsApproved=function(){
		return $http.get("http://localhost:8080/SocialmediaMiddleware/getblogs/"+1)
	}
	blogService.getBlog=function(id){
		return $http.get("http://localhost:8080/SocialmediaMiddleware/getblog/"+id);
	}
	blogService.approve=function(blog){
		
		return $http.put("http://localhost:8080/SocialmediaMiddleware/approve",blog)
	    
	}
   blogService.reject=function(blog,rejectionReason){
	 
    	return $http.put("http://localhost:8080/SocialmediaMiddleware/reject/"+rejectionReason,blog)
    	
   }
   blogService.updateLikes=function(id){
	   console.log('updating likes in service for blog '+id);
	   return $http.put("http://localhost:8080/SocialmediaMiddleware/updatelikes/"+id);
   }
   blogService.addComment=function(id,blogComment){         
	   return $http.post("http://localhost:8080/SocialmediaMiddleware/addcomment/"+id,blogComment);             
   }
   blogService.getBlogComments=function(id){
	   return $http.get("http://localhost:8080/SocialmediaMiddleware/blogcomments/"+id)
   }
   blogService.hasUserLikedBlog=function(id){
	   return $http.get("http://localhost:8080/SocialmediaMiddleware/hasuserlikedblog/"+id)
   }
	return blogService;
	
})