package model;

import java.util.List;

// represents an Ingredients with different ingredients spit into proteins, veggies, dairy and condiments.
public class Ingredient {
   
    private List<String> protein;
    private List<String> veggie;
    private List<String> dairy;
    private List<String> condiment;

     /*
     * EFFECTS: constructs 4 lists with each one containing all of the protein
     *          veggies, dairy, and condiments respectfully.
     */
    public Ingredient() {
        protein = List.of("beef");
        veggie = List.of("lettuce", "potato");
        dairy = List.of("cheese", "Ice cream");
        condiment = List.of("salt");
        
    }

    public List<String> getProtein() {
        return protein; 
    }

    public List<String> getVeggie() {
        return veggie; 
    }

    public List<String> getDairy() {
        return dairy; 
    }

    public List<String> getCondiment() {
        return condiment; 
    }
    
}
