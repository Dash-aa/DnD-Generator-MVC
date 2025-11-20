package com.example.app;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // example races/classes
        List<CharacterRace> races = Arrays.asList(
            new CharacterRace("Dwarf (Hill)", new Stats().add("Constitution",2).add("Wisdom",1)),
            new CharacterRace("Elf", new Stats().add("Dexterity",2)),
            new CharacterRace("Human", new Stats().add("Strength",1).add("Dexterity",1)),
            new CharacterRace("Halfling", new Stats().add("Dexterity",2))
        );
        List<CharacterClass> classes = Arrays.asList(
            new CharacterClass("Cleric", "Wisdom", 2),
            new CharacterClass("Ranger", "Dexterity", 1),
            new CharacterClass("Fighter", "Strength", 1),
            new CharacterClass("Wizard", "Intelligence", 2)
        );

        SwingUtilities.invokeLater(() -> {
            MainView mv = new MainView(races, classes);
            mv.setVisible(true);
        });
    }
}
