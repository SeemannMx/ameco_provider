import java.io.IOException;
import java.sql.SQLException;

public class Main {

    private final String TAG = "MAIN ";
    private String path = "/Users/tkallinich/DashboardProjectResources/Rest";



    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        // connect to database
        DatabaseHelper dbHelper = new DatabaseHelper("3306", "myDashboardDatabase", "root", "serena");
        dbHelper.runDataProvider();



    }
}
