package ru.leosam.game;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Stack;

public class Unit {
    public static final Integer MAX_HEALTH = 100;
    @Getter
    private String name;
    @Getter
    private Integer health = MAX_HEALTH;
    private HashMap<Resource, Integer> resources = new HashMap<>();
    private Stack<Command> commands = new Stack<>();

    public Unit(String name) {
        setUnitName(name);
    }

    public String hail() {
        return "Hi, I am " + name;
    }

    @Override
    public String toString() {
        StringBuilder printedResources = new StringBuilder();
        for (Resource resource : resources.keySet()) {
            printedResources
                    .append(resource)
                    .append(": ")
                    .append(resources.get(resource))
                    .append("; ");
        }
        return "Unit{name='" + name
                + "', health=" + health
                + ", resources=[" +
                (printedResources.length() == 0 ? ""
                        :  printedResources.substring(0, printedResources.length() - 2)) + "]}";
    }

    public void setName(String name) {
        String oldName = this.name;
        this.commands.push(() -> {
            this.name = oldName;
        });
        setUnitName(name);
    }

    private void setUnitName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name must not be empty");
        this.name = name;
    }

    public void setHealth(int health) {
        if (health < 0 || health > MAX_HEALTH) throw new IllegalArgumentException("Health is incorrect");
        Integer oldHealth = this.health;
        this.commands.push(() -> {
            this.health = oldHealth;
        });
        this.health = health;
    }

    public HashMap<Resource, Integer> getResources() {
        return new HashMap<>(resources);
    }

    public void setResource(Resource resource, int value) {
        if (value < 0) throw new IllegalArgumentException("Illegal value of " + resource);
        if (resources.containsKey(resource)) {
            this.commands.push(() -> {
                this.resources.put(resource, resources.get(resource));
            });
        } else {
            this.commands.push(() -> {
                this.resources.remove(resource);
            });
        }
        resources.put(resource, value);
    }

    public Integer getResource(Resource resource) {
        if (resources.containsKey(resource)) {
            return resources.get(resource);
        }
        return null;
    }

    public void printResources() {
        resources.values().stream().forEach(System.out::println);
    }

    public Unit undo() throws NothingToUndo {
        if (commands.isEmpty()) throw new NothingToUndo();
        commands.pop().perform();
        return this;
    }
}
