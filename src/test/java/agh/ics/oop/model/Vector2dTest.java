package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    Vector2d v1 = new Vector2d(-1,-2);
    Vector2d v2 = new Vector2d(4, 5);
    Vector2d v3 = new Vector2d(1, 3);

    @Test
    public void equalsTest(){
        assertFalse(v3.equals(v1));
        assertTrue(v2.equals(v2));
    }

    @Test
    public void toStringTest(){
        assertEquals("(-1,-2)",v1.toString());
    }

    @Test
    public void precedesTest(){
        assertTrue(v1.precedes(v2));
        assertFalse(v2.precedes(v3));
    }

    @Test
    public void followsTest(){
        assertTrue(v2.follows(v1));
        assertFalse(v3.follows(v2));
    }

    @Test
    public void upperRightTest(){
        assertEquals(new Vector2d(4,5), v1.upperRight(v2));
    }

    @Test
    public void lowerLeftTest(){
        assertEquals(new Vector2d(1,3), v3.lowerLeft(v2));

    }

    @Test
    public void addTest(){
        assertEquals(new Vector2d(3,3), v1.add(v2));
    }

    @Test
    public void subtractTest(){
        assertEquals(new Vector2d(3,2), v2.subtract(v3));
    }

    @Test
    public void oppositeTest(){
        assertEquals(new Vector2d(1,2), v1.opposite());
    }

}
