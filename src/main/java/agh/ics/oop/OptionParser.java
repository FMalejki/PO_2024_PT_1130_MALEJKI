package agh.ics.oop;

public class OptionParser {
    public static MoveDirection[] refactor(String[] args){
        MoveDirection[] directions = new MoveDirection[args.length];
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "f":
                    directions[i] = MoveDirection.FORWARD;
                    break;
                case "b":
                    directions[i] = MoveDirection.BACKWARD;
                    break;
                case "r":
                    directions[i] = MoveDirection.RIGHT;
                    break;
                case "l":
                    directions[i] = MoveDirection.LEFT;
                    break;
                default:
                    directions[i] = MoveDirection.ERROR;
                    break;
            }
        }
        return directions;
    }
}
