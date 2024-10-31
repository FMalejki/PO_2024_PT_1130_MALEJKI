package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {
    public static void main(String[] args) {
        System.out.println("START");
        MoveDirection[] directions = OptionParser.refactor(args);
        run(directions);
        System.out.println("STOP");
//        check if Vector2d works as it should
//        Vector2d position1 = new Vector2d(1,2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));

//        check if MapDirection works as it should
//        MapDirection direction = MapDirection.NORTH;
//        System.out.println(direction);
//        direction = direction.next();
//        System.out.println(direction);
//        direction = direction.previous();
//        direction = direction.previous();
//        System.out.println(direction);
//        Vector2d dirVec = direction.toUnitVector();
//        System.out.println(dirVec);
    }

    public static void run(MoveDirection[] directions) {
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
