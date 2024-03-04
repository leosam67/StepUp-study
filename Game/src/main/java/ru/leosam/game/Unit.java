package ru.leosam.game;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public class Unit {
    public static final Integer MAX_HEALTH = 100;
    @Getter
    private String name;
    @Getter
    private Integer health = MAX_HEALTH;
    private final HashMap<Resource, Integer> resources = new HashMap<>();

    public Unit(String name) {
        setUnitName(name);
    }

    public String hail() {
        return "Hi, I am " + name;
    }
    public void setName(String name) {
        setUnitName(name);
    }

    private void setUnitName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name must not be empty");
        this.name = name;
    }

    public void setHealth(int health) {
        if (health < 0 || health > MAX_HEALTH)
            throw new IllegalArgumentException("Health is incorrect");
        this.health = health;
    }

    public HashMap<Resource, Integer> getResources() {
        return new HashMap<>(resources);
    }

    public void setResource(Resource resource, int value) {
        if(value < 0) throw new IllegalArgumentException("Illegal value of " + resource);
        if (resources.containsKey(resource)) {
            resources.put(resource, value);
        } else {
            resources.put(resource, value);
        }
    }

    public Integer getResource(Resource resource) {
        if (resources.containsKey(resource)) {
            return resources.get(resource);
        }
        return null;
    }
}
