package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GrassField extends AbstractWorldMap {

    private final Map<Vector2d, Grass> grasses;
    private final int number;

    public GrassField(int number){
        this.grasses = new HashMap<>();
        this.number = number;
        placeGrasses();
    }

    public void placeGrasses(){
        Random random = new Random();
        Vector2d start = new Vector2d(0,0);
        Vector2d end = new Vector2d((int) Math.round(Math.sqrt(10*this.number)), (int) Math.round(Math.sqrt(10*this.number)));
        int endX = end.getX();
        int endY = end.getY();
        int startX = start.getX();
        int startY = start.getY();
        int it = 0;
        while(it < number) {
            int randomX = random.nextInt(endX + 1 - startX) + startX; // [min, max+1)
            int randomY = random.nextInt(endY + 1 - startY) + startY;
            try{
                if(placeSingleGrass(randomX,randomY)){
                    it++;
                }
            }
            catch(IncorrectPositionException e){}
        }
    }

    private boolean placeSingleGrass(int X, int Y) throws IncorrectPositionException {
        if (objectAt(new Vector2d(X,Y)) != null) {
            throw new IncorrectPositionException(new Vector2d(X,Y));
        }
        else {
            grasses.put(new Vector2d(X,Y), new Grass(new Vector2d(X,Y)));
            return true;
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement object = super.objectAt(position);
        if(object != null) return object;
        return grasses.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return objectAt(position) == null || objectAt(position) instanceof Grass;
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> elements = super.getElements();
        elements.addAll(grasses.values());
        return elements;
    }

    @Override
    public Boundary getCurrentBounds() {
        if (animals.isEmpty() && grasses.isEmpty()) {
            return new Boundary(new Vector2d(0, 0), new Vector2d(5, 5));
        } else {
            int highestX = 0;
            int highestY = 0;
            int lowestX = 0;
            int lowestY = 0;
            int it = 0;
            for (Vector2d position : animals.keySet()) {
                if (it == 0) {
                    highestX = position.getX();
                    highestY = position.getY();
                    lowestX = position.getX();
                    lowestY = position.getY();
                    it++;
                } else {
                    if (position.getX() > highestX) {
                        highestX = position.getX();
                    }
                    if (position.getY() > highestY) {
                        highestY = position.getY();
                    }
                    if (position.getX() < lowestX) {
                        lowestX = position.getX();
                    }
                    if (position.getY() < lowestY) {
                        lowestY = position.getY();
                    }
                }
            }
            for (Vector2d position : grasses.keySet()) {
                if (position.getX() > highestX) {
                    highestX = position.getX();
                }
                if (position.getY() > highestY) {
                    highestY = position.getY();
                }
                if (position.getX() < lowestX) {
                    lowestX = position.getX();
                }
                if (position.getY() < lowestY) {
                    lowestY = position.getY();
                }
            }
            Vector2d start = new Vector2d(lowestX, lowestY);
            Vector2d end = new Vector2d(highestX, highestY);
            return new Boundary(start, end);
        }
    }
}