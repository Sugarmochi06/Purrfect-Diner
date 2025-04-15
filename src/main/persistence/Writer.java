package persistence;

import org.json.JSONObject;

public interface Writer {
// references Writable in JsonSterializationDemo
// EFFECTS: returns this as JSON object
    JSONObject toJson();
}
