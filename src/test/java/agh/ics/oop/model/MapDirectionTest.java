package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {
    @Test
    public void next() {
        //given
        MapDirection direction = MapDirection.NORTH;

        //when
        MapDirection supEast = direction.next();
        MapDirection supSouth = supEast.next();
        MapDirection supWest = supSouth.next();
        MapDirection supNorth = supWest.next();

        //then
        assertEquals(supEast, MapDirection.EAST);
        assertEquals(supSouth, MapDirection.SOUTH);
        assertEquals(supWest, MapDirection.WEST);
        assertEquals(supNorth, MapDirection.NORTH);
    }

    @Test
    public void previous() {
        //given
        MapDirection direction = MapDirection.NORTH;

        //when
        MapDirection supWest = direction.previous();
        MapDirection supSouth = supWest.previous();
        MapDirection supEast = supSouth.previous();
        MapDirection supNorth = supEast.previous();

        //then
        assertEquals(supEast, MapDirection.EAST);
        assertEquals(supSouth, MapDirection.SOUTH);
        assertEquals(supWest, MapDirection.WEST);
        assertEquals(supNorth, MapDirection.NORTH);
    }




}
