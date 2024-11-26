package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap{

    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected Vector2d start;
    protected Vector2d end;
    protected final MapVisualizer visualizer = new MapVisualizer(this);
    protected final List<MapChangeListener> observers = new ArrayList<>();

    public void addObserver(MapChangeListener listener) {
        observers.add(listener);
    }

    public void removeObserver(MapChangeListener listener) {
        observers.remove(listener);
    }

    protected void mapChanged(String text) {
        for (MapChangeListener listener : observers) {
            listener.mapChanged(this,text);
        }
    }


    @Override
    public boolean place(Animal animal) throws IncorrectPositionException {
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            mapChanged("Animal placed at " + animal.getPosition());
            return true;
        }
        else {
            throw new IncorrectPositionException(animal.getPosition());
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d current = animal.getPosition();
        animal.move(direction, this);
        animals.remove(current);
        animals.put(animal.getPosition(), animal);
        mapChanged("Animal moved to " + animal.getPosition() + " from " + current);
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

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> elements = new ArrayList<>(animals.values());
        return elements;
    }

    @Override
    public Boundary getCurrentBounds(){
        return new Boundary(start, end);
    }

    @Override
    public String toString() {
        return visualizer.draw(getCurrentBounds().start(), getCurrentBounds().end());
    }
}
