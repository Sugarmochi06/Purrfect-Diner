package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class TestCustomer {
    Customer customer;

    @BeforeEach
    void runBefore() {
        customer = new Customer("april", "fries");

    }

    @Test 
    void testConstructor() {
        assertEquals("april", customer.getCustomerName());
        assertEquals("fries", customer.getDishName());
    }

}
