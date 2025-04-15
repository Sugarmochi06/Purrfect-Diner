package model;

// represents a customer with name, name of the dish they want to order

public class Customer {

    private String name;
    private String dishName;


    public Customer(String custname, String dish) {
        this.name = custname;
        this.dishName = dish;

    }

    public String getCustomerName() {
        return name;
    }

    public String getDishName() {
        return dishName; 
    }



}
