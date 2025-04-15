package model;

import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writer;
import java.util.Collections;

// represents a Tab with a List of Orders
public class Tab implements Writer {
    List<Order> tab;
  
    /*
     * EFFECTS: constructs a tab with an empty list of orders;
     */

    public Tab() {
        tab = new ArrayList<>();
    }

    /*
     * MODIFIES: this;
     * EFFECTS: adds order to tab;
     */
    public void addOrderToTab(Order order) {
        tab.add(order); 
        EventLog.getInstance().logEvent(new Event("" + order + " added to tab"));
    }

    /*
     * EFFECTS: returns list of order
     */

    public List<Order> getAllOrders() {
        EventLog.getInstance().logEvent(new Event("retrieved all orders from tab"));
        return tab;
    }

    /*
     * REQUIRES: tab size > 0;
     * MODIFIES: this;
     * EFFECTS: removes a completed order from the tab;
     */

    public void removeOrder(Order order) {
        if (order.isCompleted() == true) {
            tab.remove(order);
            
        }
        EventLog.getInstance().logEvent(new Event("removed " + order + " from tab"));
    }

    /*
     * references WorkRoom in JsonSterializationDemo
     * EFFECTS: returns unmodifiable list of orders in tab
     */

    public List<Order> getOrders() {
        return Collections.unmodifiableList(tab);
    }

    /*
    * references WorkRoom in JsonSterializationDemo
    * EFFECTS: returns number of orders in tab
    */

    public int numOrders() {
        return tab.size();
    }

    /*
     * references WorkRoom in JsonSterializationDemo
     */

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("tab", tabToJson());
        return json;
    }

    /*
     * references WorkRoom in JsonSterializationDemo
     * EFFECTS: returns things in this tab as a JSON array
    */

    private JSONArray tabToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Order o : tab) {
            jsonArray.put(o.toJson());
        }

        return jsonArray;
    }
}
