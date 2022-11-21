package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class SimulationEngine implements IEngine {

//    List<Animal> animals = new ArrayList<>();
//    List<Grass> grass = new ArrayList<>();
    private MoveDirection[] directions;
    private Vector2d[] positions;
    private GrassField map;

    public SimulationEngine(MoveDirection[] directions, GrassField map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.positions = positions;
        addAnimals();
    }

    public void addAnimals () {
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position);
            if (map.place(animal)) {
                map.place(animal);
                out.print(animal.getAnimalPosition());
                this.map.sizeOfMap = animal.getAnimalPosition().upperRight(this.map.sizeOfMap);

            }
        }
    }


    @Override
    public void run() {
        for (int i = 0; i < directions.length; i++) {
            Animal currentAnimal = map.animals.get(i % map.animals.size());
            currentAnimal.move(directions[i]);
            this.map.sizeOfMap = currentAnimal.getAnimalPosition().upperRight(this.map.sizeOfMap);
        }
    }
}
