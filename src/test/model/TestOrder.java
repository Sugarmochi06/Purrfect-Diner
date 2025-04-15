package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestOrder {
    Order order;
    String cname;
    String dname;

    Customer customer;
    
    @BeforeEach
    void runBefore() {
        cname = "Bob";
        dname = "Chicken Strips";
  

        customer = new Customer(cname, dname);
        order = new Order(customer);
        

    }

    @Test
    void testConstructor() {
        assertEquals(cname, order.getCustomersName());
        assertEquals(dname, order.getDishesName());
       
    }

    @Test 
    void testServeOrder() {
        assertFalse(order.isCompleted());
        order.serveOrder();
        assertTrue(order.isCompleted());
    }

}
