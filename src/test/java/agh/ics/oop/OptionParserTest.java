package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class OptionParserTest {

    @Test
    public void refactorTestF() {
        String[] arg = {"f"};
        List<MoveDirection> expect = List.of(MoveDirection.FORWARD);
        try{
            assertEquals(expect, OptionParser.refactor(arg));
        }catch(IndexOutOfBoundsException e){
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void refactorTestB() {
        String[] arg = {"b"};
        List<MoveDirection> expect = List.of(MoveDirection.BACKWARD);
        try{
            assertEquals(expect, OptionParser.refactor(arg));
        }catch(IndexOutOfBoundsException e){
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void refactorTestR() {
        String[] arg = {"r"};
        List<MoveDirection> expect = List.of(MoveDirection.RIGHT);
        try{
            assertEquals(expect, OptionParser.refactor(arg));
        }catch(IndexOutOfBoundsException e){
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void refactorTestL() {
        String[] arg = {"l"};
        List<MoveDirection> expect = List.of(MoveDirection.LEFT);
        try{
            assertEquals(expect, OptionParser.refactor(arg));
        }catch(IndexOutOfBoundsException e){
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void refactorTestWrong() {
        String[] arg = {"w"};
        try{
            OptionParser.refactor(arg);
        }catch(IllegalArgumentException e){
            assertEquals("w argument is invalid", e.getMessage());
        }
    }

    @Test
    public void refactorTestMultiple() {
        String[] arg = {"f", "b", "r", "l", "f"};
        List<MoveDirection> expect = List.of(MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD);
        try{
            assertEquals(expect, OptionParser.refactor(arg));
        }catch(IndexOutOfBoundsException e){
            fail("Unexpected exception: " + e.getMessage());
        }

    }

    @Test
    public void refactorTestMultipleWithWrong() {
        String[] arg = {"f", "b", "w", "r", "l", "f"};
        try{
            OptionParser.refactor(arg);
        }catch(IllegalArgumentException e){
            assertEquals("w argument is invalid", e.getMessage());
        }
    }
}
