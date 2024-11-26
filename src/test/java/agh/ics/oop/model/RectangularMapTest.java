package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {

    @Test
    public void testMap() {
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal = new Animal(new Vector2d(2, 2));
        try {
            assertTrue(map.place(animal));
        }catch(IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
    }

    @Test
    public void testMoving() {
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        try {
            assertTrue(map.place(animal1));
        }catch(IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }

        assertTrue(map.canMoveTo(new Vector2d(2, 3)));

        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
    }

    @Test
    public void testMoving2() {
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 3));
        try {
            assertTrue(map.place(animal1));
        }catch(IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
            assertTrue(map.place(animal2));
        }catch(IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }

        assertFalse(map.canMoveTo(new Vector2d(2, 3)));
    }

    @Test
    public void testPlacing() {
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 2)); // Duplicate position
        try {
            assertTrue(map.place(animal1));
        }catch(IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
            map.place(animal2);
        }catch(IncorrectPositionException e){
            assertEquals("Position " + animal2.getPosition() + " is not correct.", e.getMessage());
        }
    }

    @Test
    public void testOccupied() {
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 3));
        try {
            assertTrue(map.place(animal1));
        }catch(IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
            assertTrue(map.place(animal2));
        }catch(IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }

        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 3)));

        assertFalse(map.isOccupied(new Vector2d(3, 3)));
    }

    @Test
    public void testObjectAt() {
        RectangularMap map = new RectangularMap(10, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 3));
        try {
            assertTrue(map.place(animal1));
        }catch(IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
            assertTrue(map.place(animal2));
        }catch(IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }

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
        try {
            assertTrue(map.place(animal1));
        }catch(IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
            assertTrue(map.place(animal2));
        }catch(IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }

        assertInstanceOf(WorldElement.class, map.objectAt(new Vector2d(2, 2)));
        assertInstanceOf(WorldElement.class, map.objectAt(new Vector2d(2, 3)));
    }
}
