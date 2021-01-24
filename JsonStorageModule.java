import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JsonStorageModule implements StorageModule {
    public static StorageModule instance = null;

    private class Settings {
        public LinkedList<HashMap<String, String>> listNames = new LinkedList<HashMap<String, String>>();
        public String pathToFiles = "";

        public Settings() throws IOException {
            // load json file
            Path globalSettingPath = Path.of("storage_module_settings/json/global.json");
            String globalSettingStr = Files.readString(globalSettingPath);
            JSONObject globalSetting = (JSONObject) JSONValue.parse(globalSettingStr);

            // get names of lists
            JSONArray listNameArr = (JSONArray) globalSetting.get("lists");
            for (int i = 0; i < listNameArr.size(); i++) {
                JSONObject tempListMeta = (JSONObject) listNameArr.get(i);
                HashMap<String, String> newDict = new HashMap<String, String>();

                // metainfo of each list
                newDict.put("name", (String) tempListMeta.get("name"));
                newDict.put("alias", (String) tempListMeta.get("alias"));

                listNames.add(newDict);
            }

            // get path to files
            pathToFiles = (String) globalSetting.get("path");
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
            try {
                this.mySettings = new Settings();
            } catch (IOException e) {
                System.out.println("JSON Storge Module: no global config found ... creating new config");
                // TODO: when global config file can't be loaded, create a new file
            }
        }
    }

    private void Reset() { // create a new object replacing this
        instance = null;
        new JsonStorageModule();
    }

    private void OutputGlobalConfig() { // serialize current settings to a new file
        // TODO: serialze everything to a json string
        // TODO: write json string to storage_module_settings/json/global.json
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
