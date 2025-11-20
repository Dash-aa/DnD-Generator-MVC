package com.example.app;

public class CharacterRace {
    private final String name;
    private final Stats raceBonuses;

    public CharacterRace(String name, Stats raceBonuses) {
        this.name = name;
        this.raceBonuses = raceBonuses;
    }
    public String getName() { return name; }
    public Stats getRaceBonuses() { return raceBonuses; }
    @Override public String toString(){ return name; }
}
