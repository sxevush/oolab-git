package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;
    private final Vector2d[] positions;
    private final IWorldMap map;
    private final List<Animal> listAnimals = new ArrayList<>();

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.positions = positions;
        addAnimals();
    }



    public void addAnimals () {
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position);
            if (map.place(animal)) {
                listAnimals.add(animal);
            }

        }
    }

    @Override
    public void run() {
        for (int i = 0; i < directions.length; i++) {
            Animal currentAnimal = listAnimals.get(i % listAnimals.size());
            currentAnimal.move(directions[i]);
        }
    }
}
