package agh.ics.oop.model;

import java.util.Vector;

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

    public void move(MoveDirection otherDirection){
        switch(otherDirection){
            case RIGHT:
                this.direction = this.direction.next();
            case LEFT:
                this.direction = this.direction.previous();
            case FORWARD: {
                Vector2d supposed = this.position.add(this.direction.toUnitVector());
                if (supposed.getY() <= 4 && supposed.getY() >= 0 && supposed.getX() <= 4 && supposed.getX() >= 0) {
                    this.position = supposed;
                }
            }
            case BACKWARD: {
                Vector2d supposed = this.position.add(this.direction.toUnitVector().opposite());
                if (supposed.getY() <= 4 && supposed.getY() >= 0 && supposed.getX() <= 4 && supposed.getX() >= 0) {
                    this.position = supposed;
                }
            }
            default:
                break;
        }
    }

}
