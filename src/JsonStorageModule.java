import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class JsonStorageModule implements StorageModule {
    public static JsonStorageModule instance = null;

    private class Settings {
        public LinkedList<HashMap<String, String>> listNames = new LinkedList<>();
        public String pathToFiles = "";

        public Settings() {
            // load json file
            try {
                Path globalSettingPath = Path.of("storage_module_settings/json/global.json");
                String globalSettingStr = Files.readString(globalSettingPath);
                JSONObject globalSetting = (JSONObject) JSONValue.parse(globalSettingStr);
                
                // get names of lists
                JSONArray listNameArr = (JSONArray) globalSetting.get("lists");
                for (Object o : listNameArr) {
                    JSONObject tempListMeta = (JSONObject) o;
                    HashMap<String, String> newDict = new HashMap<String, String>();

                    // metainfo of each list
                    newDict.put("name", (String) tempListMeta.get("name"));
                    newDict.put("alias", (String) tempListMeta.get("alias"));

                    listNames.add(newDict);
                }

                // get path to files
                pathToFiles = (String) globalSetting.get("path");
            } catch (IOException e) {
                // file can't be opened

                // prompt for new setting
                System.out.println("Json Storage Module: no global config found, create new? Y/n");
                
                String response = System.console().readLine();
                if (response.equals("n") || response.equals("N")) {
                    System.out.println("Json Storage Module: config creation cancelled, exiting ...");
                }
                else {
                    // get path to file
                    Boolean success = false;
                    System.out.println("Json Storage Module: input path to lists' folder");
                    while (!success) {
                        response = System.console().readLine();
                        if (response.equals("!e")) {
                            // user breaks
                            System.out.println("Json Storage Module: operation cancelled, exiting ...");
                            System.exit(0);
                        }
                        try {
                            File myObj = new File(response + (response.charAt(response.length()-1) == '/' ? "badtoken" : "/badtoken"));
                            myObj.createNewFile(); // duplicated or not, I just care about if there's a file to write
                            success = true;
                        } catch (IOException ne) {
                            success = false;
                            System.out.println("Json Storage Module: path invalid, please try again (enter \"!e\" to cancel");
                        }
                    }

                    // output settings
                    OutputGlobalConfig();
                }
            }

        }
    }

    private Settings mySettings = null; // use this to test if there's need for a setting load

    public JsonStorageModule() {
        // singleton
        if (instance == null) {
            instance = this;
        } else {
            return;
        }

        // initial setting loading
        if (this.mySettings == null) {
            this.mySettings = new Settings();
        }
    }

    // private void Reset() { // create a new object replacing this
    //     instance = null;
    //     new JsonStorageModule();
    // }

    private void OutputGlobalConfig() { // serialize current settings to a new file
        JSONObject fileToWrite = new JSONObject();
        JSONArray listArr = new JSONArray();

        // serialize json array
        for (HashMap<String, String> temp : mySettings.listNames) {
            JSONObject tempJson = new JSONObject();
            tempJson.put("name", temp.get("name"));
            tempJson.put("alias", temp.get("alias"));

            listArr.add(tempJson);
        }

        // put together
        fileToWrite.put("path", this.mySettings.pathToFiles);
        fileToWrite.put("lists", listArr);

        // try creating a file
        try {
            File myObj = new File("storage_module_settings/json/global.json");
            myObj.createNewFile(); // duplicated or not, I just care about if there's a file to write
        } catch (IOException e) {
            System.out.println("Json Storage Module: can't create config file");
            System.out.println(e.getMessage());
            System.exit(200);
        }

        // write to file
        try {
            FileWriter myWriter = new FileWriter("storage_module_settings/json/global.json");
            myWriter.write(fileToWrite.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Json Storage Module: can't write to config file");
            System.out.println(e.getMessage());
            System.exit(220);
        }
    }

    // interface functions
    // /////////////////////////////////////////////////////////////////

    // list management
    public void CreateList(String name, String alias) {

    }

    public void RemoveList(String alias) {

    }

    // list modifier
    public void AddItemToList(String summary, String content, String comment, int priority_level) {

    }

    public void RemoveItemFromList(int index) {

    }

    // message with main routine
    public void ReadWholeList(String alias) {

    }

    public void PeakTopOfList(String alias) {

    }

    // alternative message with main routine
    public void PrintWholeList(String alias) {

    }

    public void PrintTopOfList(String alias) {

    }
}
