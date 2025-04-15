package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestIngredient {
    Ingredient ingredient;

    @BeforeEach
    void runBefore() {
        ingredient = new Ingredient();
    } 

    @Test
    void testConstructor() {
        assertEquals(1, ingredient.getProtein().size());
        assertEquals(2, ingredient.getVeggie().size());
        assertEquals(2, ingredient.getDairy().size());
        assertEquals(1, ingredient.getCondiment().size());
    }
}
