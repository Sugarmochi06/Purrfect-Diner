package model;

import java.util.List;
import java.util.ArrayList;

// represents a bowl 

public class Bowl {
    private List<String> bowl;

    public Bowl() {
        bowl = new ArrayList<>();
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds ingredient to bowl
     */
    public void add(String ingredient) {
        bowl.add(ingredient);
        EventLog.getInstance().logEvent(new Event("added " + ingredient + " to bowl"));
    }


    /*
     * MODIFIES: this
     * EFFECTS: clears bowl
     */
    public void clear() {
        bowl.clear();
        EventLog.getInstance().logEvent(new Event("cleared bowl"));
    }

    /*
     * EFFCTS: returns size of bowl
     */
    public int size() {
        return bowl.size();
    }

    public List<String> getBowl() {
        return bowl;
    }
}
