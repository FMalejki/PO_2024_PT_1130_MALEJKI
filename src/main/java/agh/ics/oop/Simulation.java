package agh.ics.oop;


import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.util.IncorrectPositionException;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private WorldMap map;
    private List<Animal> animals = new ArrayList<>();
    private List<MoveDirection> moves = new ArrayList<>();

    public Simulation(List<Vector2d> positions, List<MoveDirection> directions, WorldMap map) {
        this.map = map;
        for(Vector2d position : positions){
            Animal animal = new Animal(position);
            try{
                if(map.place(animal)){
                    this.animals.add(animal);
                }
            }catch(IncorrectPositionException e){
                System.out.println("Not added " + e.getMessage());
            }
        }
        this.moves.addAll(directions);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void run() {
        // We use ArrayList because the main
        // operations are adding elements and iterating,
        // those operations are very efficient in the case
        // of array lists.
        // If we were to frequently insert or remove items from the
        // list changing to linkedlists would make more sense
        int i = 0;
        int sizeOfAnimals = this.animals.size();
        for(MoveDirection move : this.moves){
            int numOfAnimal = i%sizeOfAnimals;
            Animal animal = this.animals.get(numOfAnimal);
            map.move(animal, move);
            i++;
        }
    }
}
