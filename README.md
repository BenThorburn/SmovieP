# SmovieP
The movie presentation tool

This is the offical repository for the smart movie project (SmovieP).

It is a full-stack application that makes makes rest api calls to connect and execute methods containing quries for an sqlite database.
This data is recived as JSON data and consumed by VueJS.

The webservices is built using Javalin (A kotlin and java microframework) and dependencies are built is maven.

This service runs on localhost at the specified port and allows for filtering of movie information such as by title, director etc.
Searching and filtering is still under development.




For java Main rather than Kotlin use:


import io.javalin.Javalin;
import io.javalin.plugin.rendering.vue.VueComponent;


public class Main {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create(config ->
			config.enableWebjars()
		).start();
	     // View for multiple movies
	     app.get("/", new VueComponent("mov-overview"));
	     // View for specific movie
	     app.get("/profile/{title-id}", new VueComponent("mov-profile"));

	     
	     app.get("api/titles", ctx -> {
	    	 try(DB db = new DB()) {
	    		 ctx.contentType();
	    		 ctx.json(db.x().toString());
	    	 }
	     });
	     
	     app.get("api/titles/profile/{title-id}", ctx -> {
	    	 try(DB db = new DB()) {
	    		 ctx.contentType();
	    		 String titleId = ctx.pathParam("title-id");
	    		 ctx.json(db.getOne(titleId).toString());
	    	 }
	     });
	}
}
