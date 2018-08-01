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
 * Ameco 6 Data Provider
 */
public class Ameco6Provider extends DataManager {

    private final String TAG = "PROVIDER AMECO_6 ";
    private String path = "/Users/tkallinich/DashboardProjectResources/Rest/rest_service_data/ameco_06/";

    private Connection con;
    private JSONObject jsonObject;
    private JSONArray jsonArray;


    String hourQuery = "SELECT * FROM AMECO6 WHERE COUNTRY = 'Germany' AND TITLE = 'Average annual hours worked per person employed'";
    String domesticIncomeQuery = "SELECT * FROM AMECO6 WHERE COUNTRY = 'Germany' AND TITLE = 'Domestic income at current prices'";

    ArrayList <String> jsonCollection;

    /**
     * public constructor
     *
     * @param c - conection
     * @throws SQLException
     */
    public Ameco6Provider(Connection c) throws SQLException {
        super();
        con = c;
        jsonCollection = new ArrayList<>();

    }

    /**
     * run querys in Ameco_6
     */
    public JSONObject runQuery() {

        try{
            jsonObject = new JSONObject();
            jsonArray = new JSONArray();

            jsonArray.add(provideDataFromAmeco6("hour", hourQuery));
            jsonArray.add(provideDataFromAmeco6("domesticIncome", domesticIncomeQuery));


            jsonObject.put("Ameco06", jsonArray);

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
    private JSONObject provideDataFromAmeco6(String key, String query) throws SQLException {

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
