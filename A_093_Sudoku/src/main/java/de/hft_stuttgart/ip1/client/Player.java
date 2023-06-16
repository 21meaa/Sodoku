package de.hft_stuttgart.ip1.client;

public class Player {
    private String name;

    public Player(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
//Player die erstellt werden können