package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class OptionParserTest {

    @Test
    public void refactorTestF() {
        String[] arg = {"f"};
        MoveDirection[] expect = {MoveDirection.FORWARD};
        assertArrayEquals(expect, OptionParser.refactor(arg));
    }

    @Test
    public void refactorTestB() {
        String[] arg = {"b"};
        MoveDirection[] expect = {MoveDirection.BACKWARD};
        assertArrayEquals(expect, OptionParser.refactor(arg));
    }

    @Test
    public void refactorTestR() {
        String[] arg = {"r"};
        MoveDirection[] expect = {MoveDirection.RIGHT};
        assertArrayEquals(expect, OptionParser.refactor(arg));
    }

    @Test
    public void refactorTestL() {
        String[] arg = {"l"};
        MoveDirection[] expect = {MoveDirection.LEFT};
        assertArrayEquals(expect, OptionParser.refactor(arg));
    }

    @Test
    public void refactorTestWrong() {
        String[] arg = {"w"};
        MoveDirection[] expect = {};
        assertArrayEquals(expect, OptionParser.refactor(arg));
    }

    @Test
    public void refactorTestMultiple() {
        String[] arg = {"f", "b", "r", "l", "f"};
        MoveDirection[] expect = {MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD};
        assertArrayEquals(expect, OptionParser.refactor(arg));
    }

    @Test
    public void refactorTestMultipleWithWrong() {
        String[] arg = {"f", "b", "w", "r", "l", "f"};
        MoveDirection[] expect = {MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD};
        assertArrayEquals(expect, OptionParser.refactor(arg));
    }
}
