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
            Ameco3Provider ameco3Provider = new Ameco3Provider(con);
            ameco03JsonObject = ameco3Provider.runQuery();
            jsonArray.add(ameco03JsonObject);

            // ameco 4
            Ameco4Provider ameco4Provider = new Ameco4Provider(con);
            ameco04JsonObject = ameco4Provider.runQuery();
            jsonArray.add(ameco04JsonObject);

            // ameco 5
            Ameco5Provider ameco5Provider = new Ameco5Provider(con);
            ameco05JsonObject = ameco5Provider.runQuery();
            jsonArray.add(ameco05JsonObject);

            // ameco 6
            Ameco6Provider ameco6Provider = new Ameco6Provider(con);
            ameco06JsonObject = ameco6Provider.runQuery();
            jsonArray.add(ameco06JsonObject);

            // ameco 7
            Ameco7Provider ameco7Provider = new Ameco7Provider(con);
            ameco07JsonObject = ameco7Provider.runQuery();
            jsonArray.add(ameco07JsonObject);

            // ameco 8
            Ameco8Provider ameco8Provider = new Ameco8Provider(con);
            // ameco08JsonObject = ameco8Provider.runQuery();
            // jsonArray.add(ameco08JsonObject);

            // ameco 9
            Ameco9Provider ameco9Provider = new Ameco9Provider(con);
            ameco09JsonObject = ameco9Provider.runQuery();
            jsonArray.add(ameco09JsonObject);

            // ameco 10
            Ameco10Provider ameco10Provider = new Ameco10Provider(con);
            ameco10JsonObject = ameco10Provider.runQuery();
            jsonArray.add(ameco10JsonObject);

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
