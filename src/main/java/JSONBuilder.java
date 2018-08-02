import Provider.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;

public class JSONBuilder {

    private final String TAG = "JSON BUILDER ";

    Connection con;

    public JSONBuilder(Connection c) {
        this.con = c;
    }


    public JSONArray createJson(){

        // create Json object and array
        JSONObject mainJSON = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        JSONObject ameco01JsonObject = new JSONObject();
        JSONObject ameco02JsonObject = new JSONObject();
        JSONObject ameco03JsonObject = new JSONObject();
        JSONObject ameco04JsonObject = new JSONObject();
        JSONObject ameco05JsonObject = new JSONObject();
        JSONObject ameco06JsonObject = new JSONObject();
        JSONObject ameco07JsonObject = new JSONObject();
        JSONObject ameco08JsonObject = new JSONObject();
        JSONObject ameco09JsonObject = new JSONObject();
        JSONObject ameco10JsonObject = new JSONObject();
        JSONObject ameco11JsonObject = new JSONObject();
        JSONObject ameco12JsonObject = new JSONObject();
        JSONObject ameco13JsonObject = new JSONObject();
        JSONObject ameco14JsonObject = new JSONObject();
        JSONObject ameco15JsonObject = new JSONObject();
        JSONObject ameco16JsonObject = new JSONObject();
        JSONObject ameco17JsonObject = new JSONObject();
        JSONObject ameco18JsonObject = new JSONObject();

        try{
            // ameco 1
            Ameco1Provider ameco1Provider = new Ameco1Provider(con);
            ameco01JsonObject = ameco1Provider.runQuery();
            jsonArray.add(ameco01JsonObject);

            // ameco 2
            Ameco2Provider ameco2Provider = new Ameco2Provider(con);
            ameco02JsonObject = ameco2Provider.runQuery();
            jsonArray.add(ameco02JsonObject);

            // ameco 3
            Ameco3Provider ameco3Provider = new Ameco3Provider(con);
            ameco03JsonObject = ameco3Provider.runQuery();
            jsonArray.add(ameco03JsonObject);

            // ameco 4
            Ameco4Provider ameco4Provider = new Ameco4Provider(con);
            ameco04JsonObject = ameco4Provider.runQuery();
            jsonArray.add(ameco04JsonObject);

            // ameco 5
            Ameco5Provider ameco5Provider = new Ameco5Provider(con);
            ameco05JsonObject = ameco5Provider.runQuery();
            jsonArray.add(ameco05JsonObject);

            // ameco 6
            Ameco6Provider ameco6Provider = new Ameco6Provider(con);
            ameco06JsonObject = ameco6Provider.runQuery();
            jsonArray.add(ameco06JsonObject);

            // ameco 7
            Ameco7Provider ameco7Provider = new Ameco7Provider(con);
            ameco07JsonObject = ameco7Provider.runQuery();
            jsonArray.add(ameco07JsonObject);

            // ameco 8
            // model exists error in query

            // ameco 9
            Ameco9Provider ameco9Provider = new Ameco9Provider(con);
            ameco09JsonObject = ameco9Provider.runQuery();
            jsonArray.add(ameco09JsonObject);

            // ameco 10
            Ameco10Provider ameco10Provider = new Ameco10Provider(con);
            ameco10JsonObject = ameco10Provider.runQuery();
            jsonArray.add(ameco10JsonObject);

            // ameco 11
            Ameco11Provider ameco11Provider = new Ameco11Provider(con);
            ameco11JsonObject = ameco11Provider.runQuery();
            jsonArray.add(ameco11JsonObject);

            // ameco 12
            Ameco12Provider ameco12Provider = new Ameco12Provider(con);
            ameco12JsonObject = ameco12Provider.runQuery();
            jsonArray.add(ameco12JsonObject);

            // ameco 13
            Ameco13Provider ameco13Provider = new Ameco13Provider(con);
            ameco13JsonObject = ameco13Provider.runQuery();
            jsonArray.add(ameco13JsonObject);

            // ameco 14
            Ameco14Provider ameco14Provider = new Ameco14Provider(con);
            ameco14JsonObject = ameco14Provider.runQuery();
            jsonArray.add(ameco14JsonObject);

            // ameco 15
            Ameco15Provider ameco15Provider = new Ameco15Provider(con);
            ameco15JsonObject = ameco15Provider.runQuery();
            jsonArray.add(ameco15JsonObject);

            // ameco 16
            // model exists but no data

            // ameco 17
            Ameco17Provider ameco17Provider = new Ameco17Provider(con);
            ameco17JsonObject = ameco17Provider.runQuery();
            jsonArray.add(ameco17JsonObject);

            // ameco 18
            Ameco18Provider ameco18Provider = new Ameco18Provider(con);
            ameco18JsonObject = ameco18Provider.runQuery();
            jsonArray.add(ameco18JsonObject);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
}
