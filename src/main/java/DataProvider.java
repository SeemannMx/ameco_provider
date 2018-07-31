import Model.Ameco1Model;
import Provider.Ameco1Provider;
import com.google.gson.Gson;

import java.sql.*;

public class DataProvider {

    private final String TAG = "DATA PROVIDER ";

    private Connection con;


    public DataProvider(Connection c) {
        this.con = c;

    }

    public void getDatafromTables() throws SQLException {

        // getColumnNamesFromDatabase(tableName);
        Ameco1Provider ameco1Provider = new Ameco1Provider(con);
        ameco1Provider.runQuery();

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
