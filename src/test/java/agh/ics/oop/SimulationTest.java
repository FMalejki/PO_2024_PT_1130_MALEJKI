package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.WorldMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationTest {
    private WorldMap map;

    @Test
    public void animalSimulationDirectionTest() {
        this.map = new RectangularMap(5, 5);
        List<MoveDirection> directions = Arrays.asList(
                MoveDirection.RIGHT,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.RIGHT
        );

        List<Vector2d> positions = Arrays.asList(
                new Vector2d(2, 2),
                new Vector2d(1, 1)
        );

        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        List<Animal> animals = simulation.getAnimals();
        Animal animal0 = animals.get(0);
        Animal animal1 = animals.get(1);

        assertEquals(MapDirection.WEST, animal0.getDirection());
        assertEquals(MapDirection.WEST, animal1.getDirection());
    }

    @Test
    public void animalSimulationMoveTest() {
        this.map = new RectangularMap(5, 5);
        List<MoveDirection> directions = Arrays.asList(
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT,
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD
        );

        List<Vector2d> positions = Arrays.asList(
                new Vector2d(2, 2),
                new Vector2d(1, 1)
        );

        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        List<Animal> animals = simulation.getAnimals();
        Animal animal0 = animals.get(0);
        Animal animal1 = animals.get(1);

        assertEquals(new Vector2d(2, 2), animal0.getPosition());
        assertEquals(new Vector2d(1, 2), animal1.getPosition());
    }

    @Test
    public void animalSimulationOutsideMapMoveTest() {
        this.map = new RectangularMap(5, 5);
        List<MoveDirection> directions = Arrays.asList(
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD
        );

        List<Vector2d> positions = Arrays.asList(
                new Vector2d(4, 4),
                new Vector2d(1, 1),
                new Vector2d(0, 0)
        );

        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        List<Animal> animals = simulation.getAnimals();
        Animal animal0 = animals.get(0);
        Animal animal1 = animals.get(1);
        Animal animal2 = animals.get(2);

        assertEquals(new Vector2d(4, 4), animal0.getPosition());
        assertEquals(new Vector2d(0, 1), animal1.getPosition());
        assertEquals(new Vector2d(1, 0), animal2.getPosition());
    }

    @Test
    public void simulationListTest() {
        this.map = new RectangularMap(5, 5);
        List<MoveDirection> directions = Arrays.asList(
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD
        );

        List<Vector2d> positions = Arrays.asList(
                new Vector2d(4, 4),
                new Vector2d(1, 1),
                new Vector2d(0, 0)
        );

        Simulation simulation = new Simulation(positions, directions, map);
        List<Animal> animals = simulation.getAnimals();

        assertEquals(3, animals.size());
    }
}
