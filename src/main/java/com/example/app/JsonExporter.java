package com.example.app;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonExporter {
    @SuppressWarnings("unchecked")
    public static void export(List<GameCharacter> characters, String filename) throws IOException {
        JSONObject root = new JSONObject();
        int i = 1;
        for (GameCharacter c : characters) {
            JSONObject obj = new JSONObject();
            obj.put("Name", c.getName());
            obj.put("Race", c.getRace() != null ? c.getRace().getName() : null);
            obj.put("Class", c.getChClass() != null ? c.getChClass().getName() : null);
            obj.put("Stats", c.getStats().getStats());
            root.put("Character" + i++, obj);
        }
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write(root.toJSONString());
        }
    }
}
