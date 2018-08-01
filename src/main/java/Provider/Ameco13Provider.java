package Provider;

import Model.AmecoModel;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Ameco 13 Data Provider
 */
public class Ameco13Provider extends DataManager {

    private final String TAG = "PROVIDER AMECO_13 ";
    private String path = "/Users/tkallinich/DashboardProjectResources/Rest/rest_service_data/ameco_13/";

    private Connection con;
    private JSONObject jsonObject;
    private JSONArray jsonArray;


    String nominalShortIntrestQuery = "SELECT * FROM AMECO13 WHERE COUNTRY = 'Germany' AND TITLE = 'Nominal short-term interest rates'";
    String realShortIntrestQuery = "SELECT * FROM AMECO13 WHERE COUNTRY = 'Germany' AND TITLE = 'Real short-term interest rates, deflator GDP'";
    String nominalLongIntrestQuery = "SELECT * FROM AMECO13 WHERE COUNTRY = 'Germany' AND TITLE = 'Nominal long-term interest rates'";
    String realLongIntrestQuery = "SELECT * FROM AMECO13 WHERE COUNTRY = 'Germany' AND TITLE = 'Real long-term interest rates, deflator GDP'";

    ArrayList <String> jsonCollection;

    /**
     * public constructor
     *
     * @param c - conection
     * @throws SQLException
     */
    public Ameco13Provider(Connection c) throws SQLException {
        super();
        con = c;
        jsonCollection = new ArrayList<>();

    }

    /**
     * run querys in Ameco_13
     */
    public JSONObject runQuery() {

        try{
            jsonObject = new JSONObject();
            jsonArray = new JSONArray();

            jsonArray.add(provideDataFromAmeco13("nominalShortIntrest", nominalShortIntrestQuery));
            jsonArray.add(provideDataFromAmeco13("realShortIntrest", realShortIntrestQuery));
            jsonArray.add(provideDataFromAmeco13("nominalLongIntrest", nominalLongIntrestQuery));
            jsonArray.add(provideDataFromAmeco13("realLongIntrest", realLongIntrestQuery));

            jsonObject.put("Ameco13", jsonArray);

            // writeToFile(jsonObject);
            showCollection(jsonCollection);

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
    private JSONObject provideDataFromAmeco13(String key, String query) throws SQLException {

        System.out.println(TAG + "QUERY : [" + query + "]");

        // create statement
        Statement st = con.createStatement();

        // execute query and java resultset
        ResultSet rs = st.executeQuery(query);

        // create GSON object
        Gson gson = new Gson();

        // System.out.println(TAG + "ResultSet:");
        AmecoModel ameco2;

        // iterate through resultset

        rs.first();

        String country = rs.getString("COUNTRY");
        String subChapter = rs.getString("SUBCHAPTER");
        String title = rs.getString("TITLE");
        String unit = rs.getString("UNIT");
        String year2018 = rs.getString("2012");

        ameco2 = new AmecoModel(country, subChapter, title, unit, year2018);
        // convert gson object to json object
        String json = gson.toJson(ameco2);
        // System.out.println(json);

        // create proper json object
        JSONObject jsonObj0 = new JSONObject();
        jsonObj0.put(key, year2018);

        st.close();

        jsonCollection.add(json);

        return jsonObj0;
    }
}
