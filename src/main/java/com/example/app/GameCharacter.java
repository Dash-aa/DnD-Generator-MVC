package com.example.app;

import java.util.Map;

public class GameCharacter {
    private String name;
    private CharacterRace race;
    private CharacterClass chClass;
    private Stats stats;

    public GameCharacter(String name, CharacterRace race, CharacterClass chClass) {
        this.name = name;
        this.race = race;
        this.chClass = chClass;
        this.stats = new Stats().generate();
        // apply race bonuses
        if (this.race != null && this.race.getRaceBonuses() != null) {
            for (Map.Entry<String, Integer> e : this.race.getRaceBonuses().getStats().entrySet()) {
                this.stats.getStats().merge(e.getKey(), e.getValue(), Integer::sum);
            }
        }
    }

    // getters/setters
    public String getName() { return name; }
    public CharacterRace getRace() { return race; }
    public CharacterClass getChClass() { return chClass; }
    public Stats getStats() { return stats; }

    // create memento
    public CharacterMemento saveToMemento() {
        return new CharacterMemento(name, race, chClass, stats.copy());
    }
    // restore
    public void restoreFromMemento(CharacterMemento m) {
        this.name = m.getName();
        this.race = m.getRace();
        this.chClass = m.getChClass();
        this.stats = m.getStats().copy();
    }

    @Override
    public String toString() {
        return name + " (" + (race != null ? race.getName() : "NoRace") + " " + (chClass != null ? chClass.getName() : "NoClass") + ")";
    }

    public static class CharacterMemento {
        private final String name;
        private final CharacterRace race;
        private final CharacterClass chClass;
        private final Stats stats;
        public CharacterMemento(String name, CharacterRace race, CharacterClass chClass, Stats stats){
            this.name = name; this.race = race; this.chClass = chClass; this.stats = stats;
        }
        public String getName(){ return name; }
        public CharacterRace getRace(){ return race; }
        public CharacterClass getChClass(){ return chClass; }
        public Stats getStats(){ return stats; }
    }
}
