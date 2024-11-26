package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import java.util.Vector;

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
        Animal animal = new Animal(new Vector2d(5, 5));
        try {
            assertTrue(grassField.place(animal));
        }catch (IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        assertTrue(grassField.isOccupied(new Vector2d(5, 5)));
        assertEquals(animal, grassField.objectAt(new Vector2d(5, 5)));
    }

    @Test
    void testAnimalCannotBePlacedOnOccupiedPosition() {
        GrassField grassField = new GrassField(5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 2));
        try {
            assertTrue(grassField.place(animal1));
        }catch (IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
            assertFalse(grassField.place(animal2));
        }catch (IncorrectPositionException e){
            assertEquals("Position " + animal2.getPosition() + " is not correct.", e.getMessage());
        }
        boolean canMove = grassField.canMoveTo(new Vector2d(2, 2));

        assertFalse(canMove);
    }

    @Test
    void testAnimalMovement() {
        GrassField grassField = new GrassField(0);
        Animal animal = new Animal(new Vector2d(1, 1));

        try {
            assertTrue(grassField.place(animal));
        }catch(IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }

        grassField.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(1, 2), animal.getPosition());
        assertTrue(grassField.isOccupied(new Vector2d(1, 2)));
        assertFalse(grassField.isOccupied(new Vector2d(1, 1)));
    }

    @Test
    void testToStringRepresentation() {
        GrassField grassField = new GrassField(5);
        Animal animal = new Animal(new Vector2d(5, 5));

        try {
            assertTrue(grassField.place(animal));
        }catch(IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }

        String mapRepresentation = grassField.toString();

        assertTrue(mapRepresentation.contains("N"));
        assertTrue(mapRepresentation.contains("*"));
    }

    @Test
    void tryPlacementOnGrass(){
        int n = 10;
        boolean flag = false;
        Vector2d placement = new Vector2d(0,0);
        GrassField grassField = new GrassField(n);
        for(int i = 0; i < Math.round(Math.sqrt(10*n)) + 1; i++ ){
            if(!flag) {
                for (int j = 0; j < Math.round(Math.sqrt(10 * n)) + 1; j++) {
                    if (grassField.isOccupied(new Vector2d(i, j))) {
                        flag = true;
                        placement = new Vector2d(i, j);
                        break;
                    }
                }
            }
            else{
                break;
            }
        }
        try {
            assertTrue(grassField.place(new Animal(placement)));
        }catch(IncorrectPositionException e){
            fail("Unexpected exception: " + e.getMessage());
        }
        assertTrue(flag);
        assertTrue(grassField.isOccupied(placement));
    }

    @Test
    void tryMovingOnGrass(){
        int n = 10;
        boolean flag = false;
        Vector2d placement = new Vector2d(0,0);
        GrassField grassField = new GrassField(n);
        for(int i = 0; i < Math.round(Math.sqrt(10*n)) + 1; i++ ){
            if(!flag) {
                for (int j = 0; j < Math.round(Math.sqrt(10 * n)) + 1; j++) {
                    if (grassField.isOccupied(new Vector2d(i, j))) {
                        flag = true;
                        placement = new Vector2d(i, j);
                        break;
                    }
                }
            }
            else{
                break;
            }
        }
        assertTrue(flag);
        assertTrue(grassField.isOccupied(placement));
        assertTrue(grassField.canMoveTo(placement));
    }
}
