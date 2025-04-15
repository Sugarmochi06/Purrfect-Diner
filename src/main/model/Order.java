package model;

import org.json.JSONObject;
import persistence.Writer;


// represents an order that has the customers name, dish name, and cooking instructions and completion status.

public class Order implements Writer { 
    private String customerName;
    private String dishName;
    private boolean serveStatus;


    /* 
    *
    * EFFECTS: set customer' name to customer name and dish's name to dish name; 
    *          sets instructions to cookingInstructions; sets served to false;
    */
    public Order(Customer customer) {
        this.customerName = customer.getCustomerName();
        this.dishName = customer.getDishName();
        this.serveStatus = false;
    }

    public String getCustomersName() {
        return customerName;
    }

    public String getDishesName() {
        return dishName; 
    }


    /*
     * EFFECTS: return true if order is served, false if otherwise;
     */

    public boolean isCompleted() {
        return serveStatus;
    }
    /*
     * MODIFIES: this
     * EFFECTS: change order status to true
     */

    public void serveOrder() {
        this.serveStatus = true;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Customername", customerName);
        json.put("Dishname", dishName);
        return json;
    }
}

