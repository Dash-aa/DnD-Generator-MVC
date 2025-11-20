package com.example.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Stats {
    private final HashMap<String, Integer> stats = new HashMap<>();

    public Stats() {}

    public Stats generate() {
        Random r = new Random();
        stats.put("Strength", 8 + r.nextInt(10));
        stats.put("Dexterity", 8 + r.nextInt(10));
        stats.put("Constitution", 8 + r.nextInt(10));
        stats.put("Intelligence", 8 + r.nextInt(10));
        stats.put("Wisdom", 8 + r.nextInt(10));
        stats.put("Charisma", 8 + r.nextInt(10));
        return this;
    }

    public Stats add(String key, int value) {
        stats.put(key, value);
        return this;
    }

    public Map<String, Integer> getStats() {
        return stats;
    }

    public Stats copy() {
        Stats s = new Stats();
        s.stats.putAll(this.stats);
        return s;
    }

    @Override
    public String toString() {
        return stats.toString();
    }
}
