package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    @Test
    void testGrassPlacement() {
        GrassField grassField = new GrassField(10);

        int expectedGrassCount = 10;
        int actualGrassCount = (int) grassField.toString().chars().filter(ch -> ch == '*').count();

        assertEquals(expectedGrassCount, actualGrassCount);
    }

    @Test
    void testAnimalPlacement() {
        GrassField grassField = new GrassField(5);
        Animal animal = new Animal(new Vector2d(2, 2));

        boolean placed = grassField.place(animal);

        assertTrue(placed);
        assertTrue(grassField.isOccupied(new Vector2d(2, 2)));
        assertEquals(animal, grassField.objectAt(new Vector2d(2, 2)));
    }

    @Test
    void testAnimalCannotBePlacedOnOccupiedPosition() {
        GrassField grassField = new GrassField(5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 2));

        grassField.place(animal1);
        boolean placed = grassField.place(animal2);

        assertFalse(placed);
    }

    @Test
    void testAnimalMovement() {
        GrassField grassField = new GrassField(0);
        Animal animal = new Animal(new Vector2d(1, 1));

        grassField.place(animal);

        grassField.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(1, 2), animal.getPosition());
        assertTrue(grassField.isOccupied(new Vector2d(1, 2)));
        assertFalse(grassField.isOccupied(new Vector2d(1, 1)));
    }

    @Test
    void testToStringRepresentation() {
        GrassField grassField = new GrassField(5);
        Animal animal = new Animal(new Vector2d(2, 2));
        grassField.place(animal);

        String mapRepresentation = grassField.toString();

        assertTrue(mapRepresentation.contains("N"));
        assertTrue(mapRepresentation.contains("*"));
    }
}
