package ru.leosam.game;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
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
        final Resource[] RESOURCES = Resource.values();
        final int RESOURCES_COUNT = RESOURCES.length;
        Random random = new Random();
        for (int k = 0; k < 10; k++) {
            Resource resource = RESOURCES[random.nextInt(RESOURCES_COUNT)];
            int value = random.nextInt(Integer.MAX_VALUE);
            unit.setResource(resource, value);
            Assertions.assertEquals(unit.getResource(resource), value);
            if (value != 0)
                Assertions.assertThrows(IllegalArgumentException.class, () -> unit.setResource(resource, -value));
        }
        for (Resource resource : RESOURCES) {
            System.out.println(resource + ": " + unit.getResource(resource));
        }
    }

    @Test
    @DisplayName("Hail test")
    void hailTest() {
        Unit unit = new Unit("Sorceress");
        Assertions.assertTrue(unit.hail().contains(unit.getName()));
    }
}
