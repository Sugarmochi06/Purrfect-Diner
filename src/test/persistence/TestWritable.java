package persistence;

import model.Tab;
import model.Order;
import model.Customer;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// references tests from JsonSerializationDemo
public class TestWritable extends TestJson {
    @Test
    void testWriterInvalidFile() {
        try {
            Tab tab = new Tab();
            JsonWritable writer = new JsonWritable("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Tab tab = new Tab();
            JsonWritable writer = new JsonWritable("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(tab);
            writer.close();

            JsonReadable reader = new JsonReadable("./data/testWriterEmptyWorkroom.json");
            tab = reader.read();
            assertEquals(0, tab.numOrders());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Tab tab = new Tab();
            tab.addOrderToTab(new Order(new Customer("April", "Fries")));
            tab.addOrderToTab(new Order(new Customer("Taro", "Ice Cream")));
            JsonWritable writer = new JsonWritable("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(tab);
            writer.close();

            JsonReadable reader = new JsonReadable("./data/testWriterGeneralWorkroom.json");
            tab = reader.read();
            List<Order> thingies = tab.getOrders();
            assertEquals(2, thingies.size());
            checkOrder("April", "Fries", thingies.get(0));
            checkOrder("Taro", "Ice Cream", thingies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
