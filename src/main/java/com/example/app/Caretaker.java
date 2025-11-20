package com.example.app;

import java.util.Stack;

public class Caretaker {
    private final Stack<GameCharacter.CharacterMemento> stack = new Stack<>();
    public void save(GameCharacter.CharacterMemento m){ stack.push(m); }
    public GameCharacter.CharacterMemento restore() {
        if (stack.isEmpty()) return null;
        return stack.pop();
    }
}
