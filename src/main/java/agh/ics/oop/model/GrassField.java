package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.nio.file.Watchable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GrassField implements WorldMap {

    private final Map<Vector2d, Animal> animals;
    private final Map<Vector2d, Grass> grasses;
    private final MapVisualizer visualizer;

    private int number;

    public GrassField(int number) {
        this.grasses = new HashMap<>();
        this.number = number;
        this.animals = new HashMap<>();
        this.visualizer = new MapVisualizer(this);
        placeGrasses();
    }

    public void placeGrasses() {
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
            if( placeSingleGrass(randomX, randomY)) {
                it++;
            }
        }
    }

    private boolean placeSingleGrass(int X, int Y) {
        System.out.println("Place single grass");
        if (objectAt(new Vector2d(X,Y)) != null) {
            return false;
        }
        else {
            grasses.put(new Vector2d(X,Y), new Grass(new Vector2d(X,Y)));
            return true;
        }
    }


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
        //if its instance of Grass its not occupied (animal can step on it)
        return objectAt(position) != null;
    }


    @Override
    public WorldElement objectAt(Vector2d position) {
        if(animals.get(position) != null) return animals.get(position);
        if(grasses.get(position) != null) return grasses.get(position);
        return null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    public String toString() {
        //set start and end
        //parse thru animals and grass and take the most high right and low left
        //upper right lower left
        if(animals.isEmpty() && grasses.isEmpty()) {
            return visualizer.draw(new Vector2d(0,0), new Vector2d(5,5));
        }
        else {
            int highestX = 0;
            int highestY = 0;
            int lowestX = 0;
            int lowestY = 0;
            int it = 0;
            for (Vector2d position : animals.keySet()) {
                if(it == 0){
                    highestX = position.getX();
                    highestY = position.getY();
                    lowestX = position.getX();
                    lowestY = position.getY();
                    it++;
                }
                else{
                    if(position.getX() > highestX){
                        highestX = position.getX();
                    }
                    if(position.getY() > highestY){
                        highestY = position.getY();
                    }
                    if(position.getX() < lowestX){
                        lowestX = position.getX();
                    }
                    if(position.getY() < lowestY){
                        lowestY = position.getY();
                    }
                }
            }
            for (Vector2d position : grasses.keySet()) {
                if(position.getX() > highestX){
                    highestX = position.getX();
                }
                if(position.getY() > highestY){
                    highestY = position.getY();
                }
                if(position.getX() < lowestX){
                    lowestX = position.getX();
                }
                if(position.getY() < lowestY){
                    lowestY = position.getY();
                }
            }
            Vector2d start = new Vector2d(lowestX,lowestY);
            Vector2d end = new Vector2d(highestX,highestY);
            return visualizer.draw(start, end);
        }
    }
}
