package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;
import java.util.Objects;

public class OptionParser {
    public static MoveDirection[] refactor(String[] args) {
        int counter = 0;
        for(String arg : args){
            switch (arg){
                case "f" -> counter++;
                case "b" -> counter++;
                case "r" -> counter++;
                case "l" -> counter++;
            }
        }
        MoveDirection[] directions = new MoveDirection[counter];
        int j = 0;
        for (String arg : args) {
            switch (arg) {
                case "f":
                    directions[j] = MoveDirection.FORWARD;
                    j++;
                    break;
                case "b":
                    directions[j] = MoveDirection.BACKWARD;
                    j++;
                    break;
                case "r":
                    directions[j] = MoveDirection.RIGHT;
                    j++;
                    break;
                case "l":
                    directions[j] = MoveDirection.LEFT;
                    j++;
                    break;
            }
        }
        return directions;

    }
}
