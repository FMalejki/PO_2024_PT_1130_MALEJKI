package agh.ics.oop.model;

public class ConsoleMapDisplay implements  MapChangeListener{
    private int updates = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {

        updates++;
        System.out.println("Update number: " + updates);
        System.out.println("Message: " + message);
        System.out.println("State of the map after: ");
        System.out.println(worldMap);

    }
}
