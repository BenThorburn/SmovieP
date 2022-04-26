package org.sm

/**
ALl is working well, maybe look more into using query params rather than just path params
Especially for when querying data on a specific vue. Limiting may be best handles through the
consummer rather than in the sql query itself. Think about how searching is going to function if the query is
not returning all the data needed! Limiting the query has this unfortunate effect but maybe there is
 an efficent way to search globally through the api returned JSON data via the consumer or some middle handler?

 So far path routes in this file (Main.kt) are well defined and stutured, making use of app.route to avoid repetition
 where its not needed. How can queries be passed directly to the component when api? This would be good functionality
 in this instance :)
*/

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.plugin.rendering.vue.VueComponent

fun main() {
    val app = Javalin.create { config ->
        config.enableWebjars()
    }.start(8080)

    // Not found
    //app.error(404, "html", VueComponent("err404"))
    //app.error(500, "html", VueComponent("err500-view"))

    app.routes {
        // View for multiple movies
        get("/", VueComponent("mov-overview"))
        // View for specific movie
        get("/profile/{title-id}", VueComponent("mov-profile"))
    }

    app.routes {
        path("api") {
            /**
             *
             * API get call for retrieving all movies
             * consumed by VueComponent mov-overview.vue via "/" path
            */
            get("/titles") { ctx ->
                DB().use { db -> // .use (kotlin) equiv to java's try with resources
                    ctx.contentType("application/json") // Expect json data
                    ctx.json(db.x().toString())         // Context json return
                } // END OF DB() USE
            } // END GET ROUTE

            /**
             *
             * API get call for retrieving information on a specific movie
             * consumed by VueComponent mov-profile.vue via "/profile/{title-id}"
             * where title-id is passed as a path param then as a parameter to DB method
            */
            get("/titles/profile/{title-id}") { ctx ->
                DB().use { db -> // .use (kotlin) equiv to java's try with resources
                    ctx.contentType("application/json")
                    val titleId = ctx.pathParam("title-id")
                    ctx.json(db.getOne(titleId).toString())
                }  // END OF DB() USE
            } // END OF GET ROUTE
        }
    }
}