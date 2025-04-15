package model;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class TestGenerateCustomer {
    List<String> names;
    List<String> dish;
    GenerateCustomer gcust;
    Random random;

    @BeforeEach
    void runBefore() {
        gcust = new GenerateCustomer();
        names = List.of("Amy","Adam", "Jack", "Jill");
        dish = List.of("Hamburger","Ice cream", "Fries", "Fish burger");
        random = new Random();

    }

    @Test 
    void testRandomName() {
        assertTrue(names.contains(names.get(random.nextInt(names.size()))));
        assertTrue(names.contains(names.get(random.nextInt(names.size()))));
        assertTrue(names.contains(names.get(random.nextInt(names.size()))));
        assertTrue(names.contains(names.get(random.nextInt(names.size()))));
        assertTrue(names.contains(names.get(random.nextInt(names.size()))));
        assertTrue(names.contains(names.get(random.nextInt(names.size()))));
        assertTrue(names.contains(names.get(random.nextInt(names.size()))));
        assertTrue(names.contains(names.get(random.nextInt(names.size()))));
    }

    @Test 
    void testRandomDish() {
        assertTrue(dish.contains(dish.get(random.nextInt(names.size()))));
        assertTrue(dish.contains(dish.get(random.nextInt(names.size()))));
        assertTrue(dish.contains(dish.get(random.nextInt(names.size()))));
        assertTrue(dish.contains(dish.get(random.nextInt(names.size()))));
        assertTrue(dish.contains(dish.get(random.nextInt(names.size()))));
        assertTrue(dish.contains(dish.get(random.nextInt(names.size()))));
        assertTrue(dish.contains(dish.get(random.nextInt(names.size()))));
        assertTrue(dish.contains(dish.get(random.nextInt(names.size()))));
        
    }

    @Test 
    void testRandomCustomer() {
        Customer c = gcust.randomCustomer();
        assertTrue(c.getCustomerName().contains(c.getCustomerName()));
        assertTrue(c.getDishName().contains(c.getDishName()));

    }
}
