import Model.Ameco1;
import com.google.gson.Gson;
import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.sql.*;
import java.util.ArrayList;

public class DataProvider {

    private final String TAG = "DATA PROVIDER ";

    private Connection con;


    public DataProvider(Connection c) {
        this.con = c;

    }

    public void getDatafromTableAmeco1() throws SQLException {

        String tableName = "AMECO1";

        System.out.println(TAG + "   get data from table " + tableName);

        // getColumnNamesFromDatabase(tableName);

        // create query
        String select = "SELECT * FROM " + tableName + " ";
        String where = "COUNTRY = 'Germany' AND TITLE = 'Total population (National accounts)'";

        String query ="SELECT * FROM AMECO1 where  COUNTRY = 'Germany' AND TITLE = 'Total population (National accounts)'";

        System.out.println(TAG + "QUERY : [" + query + "]");

        // create statement
        Statement st = con.createStatement();

        // execute query and java resultset
        ResultSet rs = st.executeQuery(query);


        // create GSON object
        Gson gson = new Gson();

        System.out.println(TAG + "ResultSet:");
        Ameco1 ameco1;

        // iterate through resultset
        while(rs.next()){

            String country = rs.getString("COUNTRY");
            String unit = rs.getString("UNIT");
            String year2018 = rs.getString("2018");
            String title = rs.getString("TITLE");

            // System.out.println("Title " + title);

            ameco1 = new Ameco1(country, unit, year2018);
            // convert gson object to json object
            String json = gson.toJson(ameco1);
            System.out.println(json);

        }
        st.close();
    }

    /**
     * get all columns from table
     *
     * @param tableName
     * @throws SQLException
     */
    private void getColumnNamesFromDatabase(String tableName) throws SQLException {
        System.out.println(TAG + "------------------------------");
        System.out.println(TAG + "get column names");

        DatabaseMetaData md = con.getMetaData();
        ResultSet rs = md.getColumns(null, null, tableName, "%");

        System.out.println(TAG + "Table-name : " + tableName);

        while(rs.next()){
            System.out.println(TAG + "Column-name : " + rs.getString(4));

        }
        System.out.println(TAG + "------------------------------");

    }
}
