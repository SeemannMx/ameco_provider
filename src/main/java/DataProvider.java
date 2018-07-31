import Provider.Ameco1Provider;

import java.sql.*;

public class DataProvider {

    private final String TAG = "DATA PROVIDER ";

    private Connection con;

    String AMECO1 = "AMECO1";


    public DataProvider(Connection c) {
        this.con = c;

    }

    public void getDatafromTables() {

        // getColumnNamesFromDatabase(AMECO1);

        try{
            Ameco1Provider ameco1Provider = new Ameco1Provider(con);
            ameco1Provider.runQuery();

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
