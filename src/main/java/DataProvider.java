import Model.Ameco1;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.cj.xdevapi.JsonArray;
import jdk.nashorn.internal.parser.JSONParser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataProvider {

    private final String TAG = "DATA PROVIDER ";

    private Connection con;


    public DataProvider(Connection c) {
        this.con = c;

    }

    public void getDatafromTableAmeco1() throws SQLException {

        System.out.println(TAG + "   get data from table ameco1");

        // create query
        String query = "SELECT * FROM AMECO1";

        // create statement
        Statement st = con.createStatement();

        // execute query and java resultset
        ResultSet rs = st.executeQuery(query);

        // create GSON object
        Gson gson = new Gson();

        System.out.println("ResultSet: +\n ");

        Ameco1 ameco1;

        // iterate through resultset
        while(rs.next()){

            String country = rs.getString("COUNTRY");
            String unit = rs.getString("UNIT");
            String year2018 = rs.getString("2018");

            ameco1 = new Ameco1(country, unit, year2018);

            // convert gson object to json object
            String json = gson.toJson(ameco1);
            System.out.println(json);

        }
        st.close();
    }
}
