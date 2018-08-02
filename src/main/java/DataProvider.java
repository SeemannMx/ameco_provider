import Provider.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;

/**
 * provide data from tables
 */
public class DataProvider extends DataManager {

    private final String TAG = "DATA PROVIDER ";
    private Connection con;

    /**
     * public constructor of data provider
     */
    public DataProvider(Connection c) {
        super();
        this.con = c;

    }

    /**
     * run querys for each table, save data in directory as json and push via shell to
     * github repo as simulated rest service
     */
    public void getDatafromTables() throws SQLException {
        // getColumnNamesFromDatabase("AMECO8");

        // create Json object and array
        JSONObject mainJSON = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        try{

            // build json array of json objects
            JSONBuilder jsonBuilder = new JSONBuilder(con);
            jsonArray = jsonBuilder.createJson();

            // build complete JSON
            mainJSON.put("data", jsonArray);

            writeToFile(mainJSON);

            // run shell script to push in repo
            ShellRunner shell = new ShellRunner();
            shell.run("git_push");

        } catch (Exception e){
            e.printStackTrace();
        }
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
