package ru.leosam.game;

public enum Resource {
    GOLD("Gold"), WATER("Water"), FOOD("Food"), ENERGY("Energy");
    final String name;
    Resource(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
