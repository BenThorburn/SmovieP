package org.sm

import org.json.JSONArray
import org.json.JSONObject
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException


class DBb : AutoCloseable {
    // Enables re-use of connection between queries
    private var connection: Connection? = null

    /**
     * Creates an instance of the DB object and connects to the database
     */
    init {
        try {
            // Creates connection per class instance
            connection = DriverManager.getConnection(JDBC_CONNECTION_STRING)
        } catch (sqle: SQLException) {
            error(sqle)
        }
    }

    /**
     * Gets titles from test.db => test_table
     * @return JSONObject
     */
    val titles: JSONObject
        get() {
            val output = JSONObject()
            val array = JSONArray()
            try {
                val stmt = connection!!.createStatement()
                val rs = stmt.executeQuery("SELECT title FROM movies_metadata")
                while (rs.next()) {
                    val inst = JSONObject()
                    inst.put("title", rs.getString("title"))
                    array.put(inst)
                }; output.putOnce("data", array)
            } catch (sqle: SQLException) { error(sqle) }
            println(output)
            return output
        }













    fun getFilmsByGenres(genre: String): JSONObject? {
        val output = JSONObject()
        val array = JSONArray()
        try {
            val s = connection!!.prepareStatement(
                "SELECT title FROM movies_metadata "
                        + "WHERE genres LIKE ?"
            )
            s.setString(1, genre)
            val rs = s.executeQuery()
            while (rs.next()) {
                val inst = JSONObject()
                inst.put("title", rs.getString("title"))
                array.put(inst)
            }
            //JSONArray jsonArray = new JSONArray(result);
            //result="";
            //for(int i=0; i<jsonArray.length();i++){
            //	JSONObject genre = jsonArray.getJSONObject(i);
            //	result += "\n|" +genre.getString("name");
            //}
        } catch (sqle: SQLException) { error(sqle) }
        println(output)
        return output
    }
    /**
     * Closes the connection to the database, required by AutoCloseable interface.
     */
    override fun close() {
        try {
            if (!connection!!.isClosed) connection!!.close()
        } catch (sqle: SQLException) {
            error(sqle)
        }
    }

    /**
     * Prints out the details of the SQL error that has occurred, and exits the programme
     * @param sqle Exception representing the error that occurred
     */
    private fun error(sqle: SQLException) {
        System.err.println("Problem Opening Database! " + sqle.javaClass.name)
        sqle.printStackTrace()
        //System.exit(1); // This will terminate program upon error comment out for testing
    }

    companion object {
        private const val JDBC_CONNECTION_STRING = "jdbc:sqlite:./data/dbmovies.db"
    }
}