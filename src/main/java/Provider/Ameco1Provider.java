package Provider;

import Model.Ameco1Model;
import com.google.gson.Gson;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Ameco 1 Data Provider
 */
public class Ameco1Provider {

    private final String TAG = "PROVIDER AMECO_1 ";
    private String path = "/Users/tkallinich/DashboardProjectResources/Rest/rest_service_data/ameco_01/";

    private Connection con;

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
        con = c;
        jsonCollection = new ArrayList<>();

    }

    /**
     * run querys in Ameco_1
     */
    public void runQuery() {

        try{
            provideDataFromAmeco1(populationQuery);
            provideDataFromAmeco1(totalLaborforceQuery);
            provideDataFromAmeco1(employmentQuery);
            provideDataFromAmeco1(unEmploymentQuery);

            // showCollection();
            convertAndWriteCollectionToJson();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * AMECO 1 - get data from table
     *
     * @param query - predefinded query as class variable
     * @throws SQLException
     */
    private void provideDataFromAmeco1(String query) throws SQLException {

        System.out.println(TAG + "QUERY : [" + query + "]");

        // create statement
        Statement st = con.createStatement();

        // execute query and java resultset
        ResultSet rs = st.executeQuery(query);

        // create GSON object
        Gson gson = new Gson();

        System.out.println(TAG + "ResultSet:");
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
        System.out.println(json);

        st.close();

        jsonCollection.add(json);

    }

    /**
     * convert collection of json objects to one json object
     */
    private void convertAndWriteCollectionToJson() throws IOException {

        String json = new Gson().toJson(jsonCollection);
        System.out.println("JSON: " + json);

        // String nameOfFile = "ameco_1_data.json";
        String nameOfFile = "db.json";

        FileWriter file = new FileWriter(path + nameOfFile);
        file.write(json);

        file.flush();
        file.close();

    }

    private void startRunner(){

    }

    /**
     * show content of json - collection
     */
    private void showCollection(){

        System.out.println(TAG);
        for(int i = 0; i < jsonCollection.size(); i++){

            System.out.println("\tCollection: " + jsonCollection.get(i));

        }

    }

}
