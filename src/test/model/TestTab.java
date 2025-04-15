package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestTab {
    Tab tab;
    ArrayList<Order> loftab;

    Order o1;
    Order o2;

    Customer c1;
    Customer c2;

    @BeforeEach 
    void runBefore() {
        c1 = new Customer("April", "Fries");
        c2 = new Customer("Taro", "Ice cream");

        o1 = new Order(c1);
        o2 = new Order(c2);

        tab = new Tab();
        loftab = new ArrayList<>();

    }

    @Test
    void testConstructor() {
        assertTrue(loftab.isEmpty());
    }

    @Test 
    void testAddOneOrder() {
        tab.addOrderToTab(o1);
        assertEquals(List.of(o1), tab.getAllOrders());
    }

    @Test
    void testAddMultipleOrders() {
        tab.addOrderToTab(o1);
        tab.addOrderToTab(o2);
        assertEquals(List.of(o1,o2), tab.getAllOrders());

        tab.addOrderToTab(o1);
        assertEquals(List.of(o1,o2,o1), tab.getAllOrders());
    }

    @Test
    void testRemoveIncompleteOrder() {
        tab.addOrderToTab(o1);
        assertEquals(List.of(o1), tab.getAllOrders());
        tab.removeOrder(o1);
        assertEquals(List.of(o1), tab.getAllOrders());
    }

    @Test
    void testRemoveCompleteOrder() {
        tab.addOrderToTab(o1);
        assertEquals(List.of(o1), tab.getAllOrders());
        o1.serveOrder();
        tab.removeOrder(o1);
        assertEquals(List.of(), tab.getAllOrders());
    }
}
