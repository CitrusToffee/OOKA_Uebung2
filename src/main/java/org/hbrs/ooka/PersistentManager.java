package org.hbrs.ooka;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.hbrs.ooka.commands.Command;

import java.io.BufferedWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PersistentManager {

    public static void loadFromJson(){
        try{
            // create a writer
            Reader reader = Files.newBufferedReader(Paths.get("config.json"));

            // create Gson instance
            Gson gson = new Gson();

            //create JsonObject instance
            JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();
            for(String key :parser.keySet()){
                JsonObject jsonComp = parser.get(key).getAsJsonObject();
                Component component = Component.getCompFromJson(jsonComp);
                CLager.getInstance().addComponent(component);
            }

            //close the writer
            reader.close();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void saveAsJson(){
        try{
            // create a writer
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("config.json"));



            // create Gson instance
            Gson gson = new Gson();

            // write JSON to file
            writer.write(gson.toJson(CLager.getInstance().getComponentsAsMap()));

            //close the writer
            writer.close();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
