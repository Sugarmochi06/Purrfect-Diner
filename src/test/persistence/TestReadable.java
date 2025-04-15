package persistence;

import java.io.IOException;
import java.util.List;

import model.Tab;
import model.Order;
import persistence.JsonReadable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

// references tests from JsonSerializationDemo
public class TestReadable extends TestJson {
    @Test
    void testReaderNonExistentFile() {
        JsonReadable reader = new JsonReadable("./data/nonExistent.json");
        try {
            Tab tab = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReadable reader = new JsonReadable("./data/testReaderEmptyWorkRoom.json");
        try {
            Tab tab = reader.read();
            assertEquals(0, tab.numOrders());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReadable reader = new JsonReadable("./data/testReaderGeneralWorkRoom.json");
        try {
            Tab tab = reader.read();
            List<Order> orders = tab.getOrders();
            assertEquals(2, orders.size());
            checkOrder("April", "Fries", orders.get(0));
            checkOrder("Taro", "Ice Cream", orders.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
