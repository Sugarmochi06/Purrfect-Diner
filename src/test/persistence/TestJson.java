package persistence;

import model.Order;
import model.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestJson {
    protected void checkOrder(String name, String dish, Order order) {
        assertEquals(name, order.getCustomersName());
        assertEquals(dish, order.getDishesName());
        assertFalse(order.isCompleted());
    }
}
