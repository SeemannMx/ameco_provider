import Model.Ameco1;

import java.sql.*;

public class DatabaseHelper {
    private String port = "3306";
    private String dbName = "alg_1";
    private String userName = "root";
    private String password = "root";
    private Connection con;

    private final String TAG = "DATABASE HELPER ";


    // name of database: myDashboardDatabase
    // user: root
    // password to mysql database:  serena

    // start database if needed
    // brew services start mysql

    // restart database if needed
    // brew services restart mysql

    /**
     * create connection to database "myDashboardDatabase"
     *
     * @param port
     * @param dbName
     * @param userName
     * @param password
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public DatabaseHelper(String port, String dbName, String userName, String password) throws ClassNotFoundException, SQLException{
        this.port = port;
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
        System.out.println(TAG + " DB name:     " + dbName);
        System.out.println(TAG + " DB port:     " + port);
        System.out.println(TAG + " DB username: " + userName);
        System.out.println(TAG + " DB password: " + "* * * * * *");
        // System.out.println(TAG + " DB password: " + password);
        connect();
    }

    /**
     * connect to database
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void connect() throws ClassNotFoundException, SQLException {
        System.out.println(TAG + " start connection ...");

        Class.forName("com.mysql.cj.jdbc.Driver");
        String sslOff = "?autoReconnect=true&useSSL=false";
        String timeZone = "&serverTimezone=Europe/Moscow";
        con = DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/" + dbName + sslOff + timeZone, userName, password);

        System.out.println(TAG + " connected to database");

    }

    /**
     * run data provider to get data from each table
     *
     * @throws SQLException
     */
    public void runDataProvider() throws SQLException {
        System.out.println(TAG + " run provider");

        DataProvider provider = new DataProvider(con);
        provider.getDatafromTableAmeco1();

    }



}
