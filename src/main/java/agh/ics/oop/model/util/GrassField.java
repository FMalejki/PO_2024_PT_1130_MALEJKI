package agh.ics.oop.model.util;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField implements WorldMap {

    private int number;

    public GrassField(int number) {
        this.number = number;
    }

    private Vector2d rng(Vector2d rangeMax) {
        Vector2d rangeMin = new Vector2d(0,0);
        //rangeMax

        int i = 0;
        while(i < this.number) {
            int randomNumX = ThreadLocalRandom.current().nextInt(rangeMin.getX(), rangeMax.getX() + 1);
            int randomNumY = ThreadLocalRandom.current().nextInt(rangeMin.getY(), rangeMax.getY() + 1);

        }
        System.out.println("Random number: " + randomNum);

    }

    @Override
    public boolean place(Animal animal) {
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }
}
