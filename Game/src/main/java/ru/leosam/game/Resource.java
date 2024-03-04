package ru.leosam.game;

public enum Resource {
    GOLD("Gold"), WATER("Water"), FOOD("Food"), ENERGY("Energy");
    final String name;

    Resource(String name) {
        this.name = name;
    }

    public static final Resource[] RESOURCES = Resource.values();
    public static final int RESOURCES_COUNT = RESOURCES.length;

    @Override
    public String toString() {
        return name;
    }
}
