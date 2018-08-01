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
 * Ameco 9 Data Provider
 */
public class Ameco9Provider extends DataManager {

    private final String TAG = "PROVIDER AMECO_9 ";
    private String path = "/Users/tkallinich/DashboardProjectResources/Rest/rest_service_data/ameco_09/";

    private Connection con;
    private JSONObject jsonObject;
    private JSONArray jsonArray;


    String export2010Query = "SELECT * FROM AMECO9 WHERE COUNTRY = 'Germany' AND TITLE = 'Exports of goods and services at 2010 prices'";
    String import2010Query = "SELECT * FROM AMECO9 WHERE COUNTRY = 'Germany' AND TITLE = 'Imports of goods and services at 2010 prices'";

    String exportCurrentQuery = "SELECT * FROM AMECO9 WHERE COUNTRY = 'Germany' AND TITLE = 'Exports of goods at current prices (National accounts)'";
    String importCurrentQuery = "SELECT * FROM AMECO9 WHERE COUNTRY = 'Germany' AND TITLE = 'Imports of goods at current prices (National accounts)'";

    ArrayList <String> jsonCollection;

    /**
     * public constructor
     *
     * @param c - conection
     * @throws SQLException
     */
    public Ameco9Provider(Connection c) throws SQLException {
        super();
        con = c;
        jsonCollection = new ArrayList<>();

    }

    /**
     * run querys in Ameco_9
     */
    public JSONObject runQuery() {

        try{
            jsonObject = new JSONObject();
            jsonArray = new JSONArray();

            jsonArray.add(provideDataFromAmeco9("export2010", export2010Query));
            jsonArray.add(provideDataFromAmeco9("import2010", import2010Query));
            jsonArray.add(provideDataFromAmeco9("exportCurrent", exportCurrentQuery));
            jsonArray.add(provideDataFromAmeco9("importCurrent", importCurrentQuery));

            jsonObject.put("Ameco09", jsonArray);

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
    private JSONObject provideDataFromAmeco9(String key, String query) throws SQLException {

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
        String year2018 = rs.getString("2018");

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
