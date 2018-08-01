import Provider.Ameco1Provider;
import Provider.Ameco2Provider;
import Provider.DataManager;
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
    public void getDatafromTables() {
        // getColumnNamesFromDatabase("AMECO1");

        try{
            JSONObject mainJSON = new JSONObject();
            JSONArray jsonArray = new JSONArray();

            JSONObject ameco01JsonObject = new JSONObject();
            JSONObject ameco02JsonObject = new JSONObject();
            JSONObject ameco03JsonObject = new JSONObject();
            JSONObject ameco04JsonObject = new JSONObject();
            JSONObject ameco05JsonObject = new JSONObject();
            JSONObject ameco06JsonObject = new JSONObject();
            JSONObject ameco07JsonObject = new JSONObject();
            JSONObject ameco08JsonObject = new JSONObject();
            JSONObject ameco09JsonObject = new JSONObject();
            JSONObject ameco10JsonObject = new JSONObject();
            JSONObject ameco11JsonObject = new JSONObject();
            JSONObject ameco12JsonObject = new JSONObject();
            JSONObject ameco13JsonObject = new JSONObject();
            JSONObject ameco14JsonObject = new JSONObject();
            JSONObject ameco15JsonObject = new JSONObject();
            JSONObject ameco16JsonObject = new JSONObject();
            JSONObject ameco17JsonObject = new JSONObject();
            JSONObject ameco18JsonObject = new JSONObject();

            // ameco 1
            Ameco1Provider ameco1Provider = new Ameco1Provider(con);
            ameco01JsonObject = ameco1Provider.runQuery();
            jsonArray.add(ameco01JsonObject);

            // ameco 2
            Ameco2Provider ameco2Provider = new Ameco2Provider(con);
            ameco02JsonObject = ameco2Provider.runQuery();
            jsonArray.add(ameco02JsonObject);

            // ameco 3

            // ameco 4

            // ameco 5

            // ameco 6

            // ameco 7

            // ameco 8

            // ameco 9

            // ameco 10

            // ameco 11

            // ameco 12

            // ameco 13

            // ameco 14

            // ameco 15

            // ameco 16

            // ameco 17

            // ameco 18

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
