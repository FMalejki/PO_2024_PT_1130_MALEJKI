package agh.ics.oop;


import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals = new ArrayList<>();
    private List<MoveDirection> moves = new ArrayList<>();

    public Simulation(List<Vector2d> positions, List<MoveDirection> directions) {
        for(Vector2d position : positions){
            this.animals.add(new Animal(position));
        }
        this.moves.addAll(directions);
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
            animal.move(move);
            System.out.println("Zwierzę " + numOfAnimal + " : " + animal.toString());
            i++;
        }
    }
}
