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

        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
    }

    @Test
    public void testMoving2() {
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 3));
        map.place(animal1);
        map.place(animal2);

        assertFalse(map.canMoveTo(new Vector2d(2, 3)));
    }

    @Test
    public void testPlacing() {
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 2)); // Duplicate position

        assertTrue(map.place(animal1));

        assertFalse(map.place(animal2));
    }

    @Test
    public void testOccupied() {
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 3));
        map.place(animal1);
        map.place(animal2);

        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 3)));

        assertFalse(map.isOccupied(new Vector2d(3, 3)));
    }

    @Test
    public void testObjectAt() {
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 3));
        map.place(animal1);
        map.place(animal2);

        assertEquals(animal1, map.objectAt(new Vector2d(2, 2)));
        assertEquals(animal2, map.objectAt(new Vector2d(2, 3)));

        assertNull(map.objectAt(new Vector2d(3, 3)));
    }

    //New tests
    @Test
    public void testObjectAt2() {
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 3));
        map.place(animal1);
        map.place(animal2);

        assertInstanceOf(WorldElement.class, map.objectAt(new Vector2d(2, 2)));
        assertInstanceOf(WorldElement.class, map.objectAt(new Vector2d(2, 3)));
    }
}
