package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.IncorrectPositionException;

import java.util.List;

public class World {
    public static void main(String[] args) {
        try {
            System.out.println("START");
            List<MoveDirection> directions = OptionParser.refactor(args);
            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
            GrassField grassField = new GrassField(10);
            System.out.println(grassField);
            System.out.println(grassField.place(new Animal()));
            System.out.println(grassField);
            System.out.println(grassField.objectAt(new Vector2d(2, 2)));
            System.out.println(grassField);
            System.out.println(grassField.isOccupied(new Vector2d(2, 2)));

            System.out.println("STOP");
        }catch(IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IncorrectPositionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void run(List<MoveDirection> directions) {
        for (MoveDirection direction : directions) {
            switch (direction) {
                case FORWARD:
                    System.out.println("zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.println("zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    System.out.println("zwierzak idzie w prawo");
                    break;
                case LEFT:
                    System.out.println("zwierzak idzie w lewo");
                    break;
            }
        }
    }
}
