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
        get("/") { ctx -> ctx.redirect("/browse")}
        // View for multiple movies
        get("/browse/", VueComponent("mov-overview"))
        // View for specific movie
        get("/profile/{title-id}", VueComponent("mov-profile"))
    }

    app.routes {
        path("api") {
            get("/titles") { ctx ->
                DB().use { db ->
                    ctx.contentType("application/json")
                    //val lim : String? = ctx.queryParam("lim")
                    //if (lim != null) {}
                    ctx.json(db.getTitles().toString())
                }
            }
            get("/titles/profile/{title-id}") { ctx ->
                DB().use { db ->
                    ctx.contentType("application/json")
                    val titleId = ctx.pathParam("title-id") // For cleaner handles please separate context calls
                    ctx.json(db.getOne(titleId).toString())
                }
            }
            get("/genres/{p}") { ctx ->
                DB().use { db ->
                    ctx.contentType("application/json")
                    ctx.result(db.getGenres(ctx.pathParam("{p}")).toString())
                }
            }
        }
    }
}