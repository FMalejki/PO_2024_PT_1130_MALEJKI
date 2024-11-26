package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.IncorrectPositionException;

import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("START");

        try {

            List<MoveDirection> directions = OptionParser.refactor(args);
            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
            GrassField grassField = new GrassField(10);
            grassField.addObserver(new ConsoleMapDisplay());
            Simulation simulation = new Simulation(positions, directions, grassField);
            simulation.run();

        }catch(IllegalArgumentException e) {

            System.out.println("Error: " + e.getMessage());

        }

        System.out.println("STOP");
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
