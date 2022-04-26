package org.sm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.transform.Result;

public class DB implements AutoCloseable {
    //allows us to easily change the database used
    private static final String JDBC_CONNECTION_STRING = "jdbc:sqlite:./data/dbmovies.db";
    //allows us to re-use the connection between queries if desired
    private Connection connection = null;
    /**
     * Creates an instance of the DB object and connects to the database
     */
    public DB() {
        try {
            connection = DriverManager.getConnection(JDBC_CONNECTION_STRING);
        } catch (SQLException sql) { error(sql); }
    }

    /**
     *
     *"SELECT id, title, genres, release_date, budget, revenue, credits.crew, credits.casting FROM movies_metadata"
     *                 + " JOIN credits ON movies_metadata.creditsID=credits.creditsID,";
     */

    public JSONObject getTitles() {
        JSONObject output = new JSONObject();
        JSONArray array = new JSONArray();
        String query = "SELECT id, title FROM movies_metadata";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            //stmt.setString(1, lim);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                JSONObject inst = new JSONObject();
                inst.put("id", rs.getString("id"));
                inst.put("title", rs.getString("title"));
                //inst.put("genres", rs.getString("genres"));
                //inst.put("release_date", rs.getString("release_date"));
                //inst.put("budget", rs.getString("budget"));
                //inst.put("revenue", rs.getString("revenue"));
                //inst.put("actors", rs.getString("castings"));
                //inst.put("directors", rs.getString("crew"));

                array.put(inst);
            } output.putOnce("data", array);

        } catch (SQLException sqle) { error(sqle); }
        System.out.println(output);
        return output;
    }


    public JSONObject getOne(String x) {
        JSONObject output = new JSONObject();
        JSONArray array = new JSONArray();
        String query = "SELECT title, vote_average FROM movies_metadata WHERE id = ?";
        try {
            PreparedStatement s = connection.prepareStatement(query);
            s.setString(1, x);
            ResultSet rs = s.executeQuery();
            while(rs.next()) {
                JSONObject inst = new JSONObject();
                inst.put("title", rs.getString("title"));
                int vote = Math.round(rs.getFloat("vote_average"));
                inst.put("vote", vote);
                array.put(inst);
            } output.putOnce("data", array);
        } catch (SQLException sqle) { error(sqle); }
        System.out.println(output);
        return output;
    }


        public JSONObject getGenres(String title) {
        JSONObject output = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            PreparedStatement s = connection.prepareStatement(
                    "SELECT genres FROM movies_metadata "
                            + "WHERE title = ?"
            );

            s.setString(1, title);
            ResultSet rs = s.executeQuery();

            while(rs.next()) {
                JSONObject inst = new JSONObject();
                inst.put("genre", rs.getString("genres"));
                array.put(inst);
            } output.putOnce("data", array);
        } catch (SQLException sqle) { error(sqle); }
        System.out.println(output);
        return output;
    }

    /**
     * Closes the connection to the database, required by AutoCloseable interface.
     */
    @Override
    public void close() {
        try {
            if (!connection.isClosed())
                connection.close();
        } catch (SQLException sqle) { error(sqle); }
    }

    /**
     * Prints out the details of the SQL error that has occurred, and exits the programme
     * @param sqle Exception representing the error that occurred
     */
    private void error(SQLException sqle) {
        System.err.println("Problem Opening Database! " + sqle.getClass().getName());
        sqle.printStackTrace();
        //System.exit(1); // This will terminate program upon error comment out for testing
    }
}