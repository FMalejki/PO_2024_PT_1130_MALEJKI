package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap{

    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected Vector2d start;
    protected Vector2d end;
    protected final MapVisualizer visualizer = new MapVisualizer(this);


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
        animal.move(direction, this);
        animals.remove(current);
        animals.put(animal.getPosition(), animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(start) && position.precedes(end) && !isOccupied(position);
    }


    @Override
    public WorldElement objectAt(Vector2d position) {
        if(animals.get(position) != null) return animals.get(position);
        return null;
    }
}
