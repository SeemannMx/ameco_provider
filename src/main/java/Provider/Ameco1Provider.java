package Provider;

import Model.Ameco1Model;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Ameco 1 Data Provider
 */
public class Ameco1Provider extends DataManager {

    private final String TAG = "PROVIDER AMECO_1 ";
    private String path = "/Users/tkallinich/DashboardProjectResources/Rest/rest_service_data/ameco_01/";

    private Connection con;
    private JSONObject jsonObject;
    private JSONArray jsonArray;


    String populationQuery =        "SELECT * FROM AMECO1 WHERE COUNTRY = 'Germany' AND TITLE = 'Total population (National accounts)'";
    String totalLaborforceQuery =   "SELECT * FROM AMECO1 WHERE COUNTRY = 'Germany' AND TITLE = 'Total labour force (Labour force statistics)'";
    String employmentQuery =        "SELECT * FROM AMECO1 WHERE COUNTRY = 'Germany' AND TITLE = 'Employment, persons: total economy (National accounts)'";
    String unEmploymentQuery =      "SELECT * FROM AMECO1 WHERE COUNTRY = 'Germany' AND TITLE = 'Total unemployment :- Member States: definition EUROSTAT'";

    ArrayList <String> jsonCollection;

    /**
     * public constructor
     *
     * @param c - conection
     * @throws SQLException
     */
    public Ameco1Provider(Connection c) throws SQLException {
        super();
        con = c;
        jsonCollection = new ArrayList<>();

    }

    /**
     * run querys in Ameco_1
     */
    public JSONObject runQuery() {

        try{
            jsonObject = new JSONObject();
            jsonArray = new JSONArray();

            jsonArray.add(provideDataFromAmeco1("population", populationQuery));
            jsonArray.add(provideDataFromAmeco1("totalLaborForce",totalLaborforceQuery));
            jsonArray.add(provideDataFromAmeco1("employedPersons",employmentQuery));
            jsonArray.add(provideDataFromAmeco1("unEmployedPersons",unEmploymentQuery));

            jsonObject.put("Ameco01", jsonArray);

            // writeToFile(jsonObject);
            // showCollection();

            convertAndWriteCollectionToJson(path, jsonCollection);

        } catch (Exception e){
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * AMECO 1 - get data from table
     *
     * @param query - predefinded query as class variable
     * @throws SQLException
     */
    private JSONObject provideDataFromAmeco1(String key, String query) throws SQLException {

        System.out.println(TAG + "QUERY : [" + query + "]");

        // create statement
        Statement st = con.createStatement();

        // execute query and java resultset
        ResultSet rs = st.executeQuery(query);

        // create GSON object
        Gson gson = new Gson();

        // System.out.println(TAG + "ResultSet:");
        Ameco1Model ameco1;

        // iterate through resultset

        rs.first();

        String country = rs.getString("COUNTRY");
        String subChapter = rs.getString("SUBCHAPTER");
        String title = rs.getString("TITLE");
        String unit = rs.getString("UNIT");
        String year2018 = rs.getString("2018");

        ameco1 = new Ameco1Model(country, subChapter, title, unit, year2018);
        // convert gson object to json object
        String json = gson.toJson(ameco1);
        // System.out.println(json);

        // create proper json object
        JSONObject jsonObj0 = new JSONObject();
        jsonObj0.put(key, year2018);

        st.close();

        jsonCollection.add(json);

        return jsonObj0;
    }



}
