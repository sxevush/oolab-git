package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    List<Animal> animals = new ArrayList<>();
    private MoveDirection[] directions;
    private Vector2d[] positions;
    private IWorldMap map;

    public SimulationEngine (MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.positions = positions;
    }

    public void addAnimals () {
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position);
            if (map.place(animal)) {
                animals.add(animal);
            }
        }
    }

    @Override
    public void run() {
        addAnimals();
        int currentAnimal = 0;
        int sizeOfAnimals = animals.size();
        for (MoveDirection direction : directions) {
            if (currentAnimal < sizeOfAnimals) {
                animals.get(currentAnimal).move(direction);
                currentAnimal++;
            } else { currentAnimal = 0; }
        }
    }
}
