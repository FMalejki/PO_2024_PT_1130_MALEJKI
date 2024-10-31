package agh.ics.oop.model;

import java.util.Map;

public class Animal {

    private MapDirection direction;
    private Vector2d position;

    public Animal(){
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
    }

    public Animal(Vector2d vector2d){
        this.position = vector2d;
        this.direction = MapDirection.NORTH;
    }

    public String toString(){
        return this.position.toString() + " " + this.direction.toString();
    }

    public Boolean isAt(Vector2d vector2d){
        return this.position.equals(vector2d);
    }

}
