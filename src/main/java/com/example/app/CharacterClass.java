package com.example.app;

public class CharacterClass {
    private final String name;
    private final String bonusType;
    private final int bonusValue;

    public CharacterClass(String name, String bonusType, int bonusValue) {
        this.name = name;
        this.bonusType = bonusType;
        this.bonusValue = bonusValue;
    }
    public String getName() { return name; }
    public String getBonusType() { return bonusType; }
    public int getBonusValue() { return bonusValue; }
    @Override public String toString(){ return name; }
}
