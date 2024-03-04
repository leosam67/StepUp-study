package ru.leosam.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class AppTest {
    @Test
    @DisplayName("Корректность имён")
    void nameTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Unit(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Unit(""));
        Unit unit = new Unit("Dwarf");
        Assertions.assertThrows(IllegalArgumentException.class, () -> unit.setName(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> unit.setName(""));
        unit.setName("Elf");
    }

    @Test
    @DisplayName("Корректность здоровья")
    void healthTest() {
        Unit unit = new Unit("Mage");
        Assertions.assertThrows(IllegalArgumentException.class, () -> unit.setHealth(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> unit.setHealth(Unit.MAX_HEALTH + 1));
        for (int k = 0; k < Unit.MAX_HEALTH + 1; k++) {
            unit.setHealth(k);
            Assertions.assertEquals(unit.getHealth(), k);
        }
    }

    @Test
    @DisplayName("Изменение ресурсов")
    void resourceTest() {
        Unit unit = new Unit("Hobbit");
        Random random = new Random();
        for (int k = 0; k < 10; k++) {
            Resource resource = Resource.RESOURCES[random.nextInt(Resource.RESOURCES_COUNT)];
            int value = random.nextInt(Integer.MAX_VALUE);
            unit.setResource(resource, value);
            Assertions.assertEquals(unit.getResource(resource), value);
            if (value != 0)
                Assertions.assertThrows(IllegalArgumentException.class, () -> unit.setResource(resource, -value));
        }
        for (Resource resource : Resource.RESOURCES) {
            System.out.println(resource + ": " + unit.getResource(resource));
        }
        unit.printResources();
    }

    @Test
    @DisplayName("Hail test")
    void hailTest() {
        Unit unit = new Unit("Sorceress");
        Assertions.assertTrue(unit.hail().contains(unit.getName()));
    }

    @Test
    @DisplayName("Undo test")
    void undoTest() {
        Unit unit = new Unit("Engineer");
        Assertions.assertThrows(NothingToUndo.class, unit::undo);
        String initialState = String.valueOf(unit);
        unit.setName("Engineer 2");
        unit.setHealth(50);
        unit.setResource(Resource.GOLD, 10);
        unit.setResource(Resource.FOOD, 20);
        unit.setResource(Resource.WATER, 30);
        unit.setResource(Resource.ENERGY, 40);
        for(;;) {
            try {
                unit.undo();
                System.out.println(unit);
            } catch (NothingToUndo e) {
                break;
            }
        }
        Assertions.assertEquals(String.valueOf(unit), initialState);
    }
}
