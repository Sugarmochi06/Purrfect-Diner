package model;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class TestRecipe {
    Order o1;
    Order o2; 
    Order o3;
    Order o4;
    Order o5;
    Customer c1;
    Customer c2;
    Customer c3;
    Customer c4;
    Customer c5;
    ArrayList<Ingredient> ingredient;
    Recipe recipe;

    @BeforeEach
    void runBefore() {
        c1 = new Customer("April", "Fries");
        c2 = new Customer("Taro", "Ice cream");
        c3 = new Customer("John", "Hamburger");
        c4 = new Customer("Molly", "CheeseBurger");
        c5 = new Customer("SAM", "fish");

        o1 = new Order(c1);
        o2 = new Order(c2);
        o3 = new Order(c3);
        o4 = new Order(c4);
        o5 = new Order(c5);

        ingredient = new ArrayList<>();

        recipe = new Recipe();

    }

    @Test 
    void testConstructor() {
        assertTrue(ingredient.isEmpty());
    }

    @Test 
    void testGetRecipe() {

        assertEquals(List.of("potato", "salt"), recipe.getRecipe(o1));
        assertEquals(List.of("Ice cream"), recipe.getRecipe(o2));
        assertEquals(List.of("beef","salt","lettuce"), recipe.getRecipe(o3));
        assertEquals(List.of("beef","salt","lettuce", "cheese"), recipe.getRecipe(o4));
        assertNull(recipe.getRecipe(o5));
    }

}
