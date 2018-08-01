package Provider;

import Model.AmecoModel;
import com.google.gson.Gson;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataManager {

    private final String TAG = "MANAGER ";

    public DataManager() {

    }

    /**
     * show content of json - collection
     */
    public void showCollection(ArrayList<String> jsonCollection){

        System.out.println(TAG);
        for(int i = 0; i < jsonCollection.size(); i++){

            System.out.println("\tCollection: " + jsonCollection.get(i));

        }

    }

    public void writeToFile(JSONObject jsonObject) throws IOException {
        String jsonX = jsonObject.toJSONString();
        String fileName = "test.json";

        String path = "/Users/tkallinich/DashboardProjectResources/AssetResource/";

        FileWriter file = new FileWriter(path + fileName);
        file.write(jsonX);

        file.flush();
        file.close();

    }

    /**
     * convert collection of json objects to one json object
     */
    public void convertAndWriteCollectionToJson(String path, ArrayList<String> jsonCollection) throws IOException {

        String json = new Gson().toJson(jsonCollection);
        // System.out.println("JSON: " + json);

        // String nameOfFile = "ameco_1_data.json";
        String nameOfFile = "db.json";

        FileWriter file = new FileWriter(path + nameOfFile);
        file.write(json);

        file.flush();
        file.close();

    }

}
