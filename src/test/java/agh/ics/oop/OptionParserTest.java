package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class OptionParserTest {

    @Test
    public void refactorTestF() {
        String[] arg = {"f"};
        List<MoveDirection> expect = List.of(MoveDirection.FORWARD);
        assertEquals(expect, OptionParser.refactor(arg));
    }

    @Test
    public void refactorTestB() {
        String[] arg = {"b"};
        List<MoveDirection> expect = List.of(MoveDirection.BACKWARD);
        assertEquals(expect, OptionParser.refactor(arg));
    }

    @Test
    public void refactorTestR() {
        String[] arg = {"r"};
        List<MoveDirection> expect = List.of(MoveDirection.RIGHT);
        assertEquals(expect, OptionParser.refactor(arg));
    }

    @Test
    public void refactorTestL() {
        String[] arg = {"l"};
        List<MoveDirection> expect = List.of(MoveDirection.LEFT);
        assertEquals(expect, OptionParser.refactor(arg));
    }

    @Test
    public void refactorTestWrong() {
        String[] arg = {"w"};
        List<MoveDirection> expect = List.of();
        assertEquals(expect, OptionParser.refactor(arg));
    }

    @Test
    public void refactorTestMultiple() {
        String[] arg = {"f", "b", "r", "l", "f"};
        List<MoveDirection> expect = List.of(MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD);
        assertEquals(expect, OptionParser.refactor(arg));
    }

    @Test
    public void refactorTestMultipleWithWrong() {
        String[] arg = {"f", "b", "w", "r", "l", "f"};
        List<MoveDirection> expect = List.of(MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD);
        assertEquals(expect, OptionParser.refactor(arg));
    }
}
