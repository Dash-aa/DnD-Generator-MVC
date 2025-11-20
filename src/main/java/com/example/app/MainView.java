package com.example.app;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame {
    private JTextField nameField = new JTextField(15);
    private JComboBox<CharacterRace> raceBox;
    private JComboBox<CharacterClass> classBox;
    private JTextArea statsArea = new JTextArea(8, 30);
    private DefaultListModel<GameCharacter> listModel = new DefaultListModel<>();
    private JList<GameCharacter> characterJList = new JList<>(listModel);
    private JButton genBtn = new JButton("Generate");
    private JButton saveStateBtn = new JButton("Save State");
    private JButton restoreBtn = new JButton("Restore State");
    private JButton addBtn = new JButton("Add to list");
    private JButton exportBtn = new JButton("Export JSON");

    private Caretaker caretaker = new Caretaker();
    private GameCharacter currentCharacter;

    public MainView(List<CharacterRace> races, List<CharacterClass> classes) {
        super("DnD Character Generator (MVC + Memento)");
        raceBox = new JComboBox<>(races.toArray(new CharacterRace[0]));
        classBox = new JComboBox<>(classes.toArray(new CharacterClass[0]));
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.add(new JLabel("Name:")); top.add(nameField);
        top.add(new JLabel("Race:")); top.add(raceBox);
        top.add(new JLabel("Class:")); top.add(classBox);
        add(top, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        statsArea.setEditable(false);
        center.add(new JScrollPane(statsArea), BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);

        JPanel right = new JPanel(new BorderLayout());
        right.add(new JScrollPane(characterJList), BorderLayout.CENTER);
        add(right, BorderLayout.EAST);

        JPanel bottom = new JPanel();
        bottom.add(genBtn); bottom.add(saveStateBtn); bottom.add(restoreBtn);
        bottom.add(addBtn); bottom.add(exportBtn);
        add(bottom, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Controller: listeners
        genBtn.addActionListener(e -> generateCharacter());
        saveStateBtn.addActionListener(e -> saveState());
        restoreBtn.addActionListener(e -> restoreState());
        addBtn.addActionListener(e -> addToList());
        exportBtn.addActionListener(e -> exportList());
    }

    private void generateCharacter() {
        String name = nameField.getText().isEmpty() ? "Unnamed" : nameField.getText();
        CharacterRace race = (CharacterRace) raceBox.getSelectedItem();
        CharacterClass chClass = (CharacterClass) classBox.getSelectedItem();
        currentCharacter = new GameCharacter(name, race, chClass);
        statsArea.setText(currentCharacter.getStats().toString());
    }

    private void saveState() {
        if (currentCharacter == null) { JOptionPane.showMessageDialog(this, "No character to save"); return; }
        caretaker.save(currentCharacter.saveToMemento());
        JOptionPane.showMessageDialog(this, "State saved");
    }

    private void restoreState() {
        GameCharacter.CharacterMemento m = caretaker.restore();
        if (m == null) { JOptionPane.showMessageDialog(this, "No saved states"); return; }
        if (currentCharacter == null) currentCharacter = new GameCharacter(m.getName(), m.getRace(), m.getChClass());
        currentCharacter.restoreFromMemento(m);
        nameField.setText(currentCharacter.getName());
        statsArea.setText(currentCharacter.getStats().toString());
    }

    private void addToList() {
        if (currentCharacter == null) { JOptionPane.showMessageDialog(this, "Generate character first"); return; }
        listModel.addElement(currentCharacter);
        JOptionPane.showMessageDialog(this, "Added to list");
    }

    private void exportList() {
        try {
            List<GameCharacter> characters = new ArrayList<>();
            for (int i=0;i<listModel.size();i++) characters.add(listModel.get(i));
            JsonExporter.export(characters, "characters.json");
            JOptionPane.showMessageDialog(this, "Exported to characters.json");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Export failed: " + ex.getMessage());
        }
    }
}
