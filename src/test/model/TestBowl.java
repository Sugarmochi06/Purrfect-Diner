package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBowl {
    Bowl bowl;

    @BeforeEach 
    void setup() {
        bowl = new Bowl();
    }

    @Test 
    public void testConstructor() {
        assertEquals(0, bowl.size());
    }

    @Test
    public void testAdd() {
        bowl.add("cheese");
        assertEquals("cheese", bowl.getBowl().get(0));
    }

    @Test
    public void testClear() {
        bowl.add("cheese");
        bowl.clear();
        assertTrue(bowl.getBowl().isEmpty());
    }

    @Test
    public void testSize() {
        bowl.add("cheese");
        assertEquals(1, bowl.size());
    }

    @Test
    public void testGetBowl() {
        bowl.add("cheese");
        assertEquals(List.of("cheese"), bowl.getBowl());
    }
}
