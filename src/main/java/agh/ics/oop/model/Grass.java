package agh.ics.oop.model;

public class Grass implements WorldElement {

    private Vector2d position;

    public Grass(Vector2d vector2d) {
        this.position = vector2d;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "*";
    }


}
