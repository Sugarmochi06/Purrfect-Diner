package model;


import java.util.List;


// represents a recipe with dishes name and a list of ingredients to use
public class Recipe {

    private List<String> hamburger;
    private List<String> cheeseBurger;


    private List<String> fries;
    private List<String> iceCream;
 

    /*
     * EFFECTS: constructs a recipie with all avaliable recipes. 
     */
    public Recipe() {
        hamburger = List.of("beef","salt","lettuce");
        cheeseBurger = List.of("beef","salt","lettuce", "cheese");
        fries = List.of("potato","salt");
        iceCream = List.of("Ice cream");
    }
    /*
     * EFFECTS: returns recipie for given order; returns null if no order 
     *          like that is found to be a recipie
     */

    public List<String> getRecipe(Order order) {
        if (order.getDishesName().equals("Hamburger")) {
            return hamburger;
        } else if (order.getDishesName().equals("CheeseBurger")) {
            return cheeseBurger;
        } else if (order.getDishesName().equals("Fries")) { 
            return fries;
        } else if (order.getDishesName().equals("Ice cream")) {
            return iceCream;
        }
        return null;
    }

}
