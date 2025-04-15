package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// generates a random customer from the list of names dishes and instructions
public class GenerateCustomer {

    private List<String> name = new ArrayList<>();
    private List<String> dishName = new ArrayList<>();
    private Random random;
    private String cname;
    private String dname;
    /*
     * EFFECTS: creates a list of names of customers, list of dish names
     */

    public GenerateCustomer() {
        List<String> names = List.of("Lily","Mandy", "Elijah", "Taro", "Bobby", "Randy");
        List<String> dishNames = List.of("Hamburger", "CheeseBurger","Fries","Ice cream");
        name.addAll(names);
        dishName.addAll(dishNames);
        random = new Random();
    }

    /*
     * MODIFIES: this
     * EFFECTS: generates a new customer from list of names and dishes
     */
    public String randomName() {
        cname = name.get(random.nextInt(name.size()));
        return cname;
    
    }
    /*
     * MODIFIES: this
     * EFFECTS: generates a new customer from list of names and dishes
     */

    public String randomDish() {
        dname = dishName.get(random.nextInt(dishName.size()));
        return dname;
    }
    
    /*
     * MODIFIES: this
     * EFFECTS: generates a new customer from list of names and dishes
     */
    public Customer randomCustomer() {
        randomName();
        randomDish();
        Customer c = new Customer(cname, dname);
        return c;
    }
}
