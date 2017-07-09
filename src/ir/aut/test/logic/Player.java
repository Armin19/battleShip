package ir.aut.test.logic;


import javax.swing.*;

public class Player {
    private String playerName;

    public Player(String name) {
        playerName = name;
    }

    public void setName(String name) {
        this.playerName = name;
    }

    public String getName() {
        return playerName;
    }
}
