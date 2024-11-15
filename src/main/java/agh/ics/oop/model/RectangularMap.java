package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap{

    private final Vector2d start;
    private final Vector2d end;
    private final Map<Vector2d, Animal> animals;
    private final MapVisualizer visualizer;

    public RectangularMap(int width, int height){
        start = new Vector2d(0,0);
        end = new Vector2d(width -1, height -1);
        this.animals = new HashMap<>();
        this.visualizer = new MapVisualizer(this);
    }

    //MoveValidator

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(start) && position.precedes(end) && !isOccupied(position);
    }

    //WorldMap

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d current = animal.getPosition();
        animal.move(direction);
        animals.remove(current);
        animals.put(animal.getPosition(), animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    //MapVisualiser

    public String toString() {
        return visualizer.draw(start, end);
    }
}