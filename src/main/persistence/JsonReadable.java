
package persistence;

import model.Customer;
import model.GenerateCustomer;
import model.Ingredient;
import model.Order;
import model.Recipe;
import model.Tab;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// ALL CODE IN THIS FILE references the structure and copies code from JsonReader 
// in JsonSerializationDemo

// represents a reader that reads the Tab from JSON data 

public class JsonReadable {
    private String source;
   
    // EFFECTS: constructs reader to read source file 
    public JsonReadable(String source) {
        this.source = source;
    } 

    /*
    * EFFECTS: reads Tab and returns it; throw IOException if error 
    *          occurs from reading
    */
    public Tab read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTab(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) { 
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Tab from JSON object and returns it
    public Tab parseTab(JSONObject jsonObject) {
        Tab tab = new Tab();
        addOrders(tab, jsonObject);
        return tab;
    }

    /*
     * MODIFIES: tab
     * EFFECTS: parses orders from JSON object and adds to GameState
     */
    private void addOrders(Tab tab, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tab");
        for (Object json : jsonArray) {
            JSONObject nextOrder = (JSONObject) json;
            addOrder(tab, nextOrder);
        }

    }

    /*
     * MODIFIES: tab
     * EFFECTS: parses order from JSON object and adds to GameState
     */
    private void addOrder(Tab tab, JSONObject jsonObject) {
        String name = jsonObject.getString("Customername");
        String dish = jsonObject.getString("Dishname");
        Order order = new Order(new Customer(name, dish));
        tab.addOrderToTab(order);


    }
}
