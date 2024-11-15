package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {

    @Test
    public void testMap() {
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal = new Animal(new Vector2d(2, 2));
        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
    }

    @Test
    public void testMoving() {
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        map.place(animal1);

        assertTrue(map.canMoveTo(new Vector2d(2, 3)));
    }

    @Test
    public void testMoving2() {
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 3));
        map.place(animal1);
        map.place(animal2);

        assertTrue(map.canMoveTo(new Vector2d(2, 4)));
    }

    @Test
    public void testPlacing() {
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 3)); // Duplicate position

        assertTrue(map.place(animal1));

        assertTrue(map.place(animal2));
    }

    @Test
    public void testOccupied() {
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 3));
        map.place(animal1);
        map.place(animal2);

        assertFalse(map.isOccupied(new Vector2d(3, 3)));
    }
}
