package Provider;

import Model.Ameco1Model;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ameco1Provider {

    private final String TAG = "PROVIDER AMECO_1 ";
    private String path = "/Users/tkallinich/DashboardProjectResources/Rest";

    private Connection con;

    String totalLaborforceQuery = "SELECT * FROM AMECO1 WHERE COUNTRY = 'Germany' AND TITLE = 'Total labour force (Labour force statistics)'";

    public Ameco1Provider(Connection c) throws SQLException {
        con = c;

    }

    public void runQuery() throws SQLException {

        provideDataFromAmeco1(totalLaborforceQuery);

    }

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
        con.close();
    }
}
