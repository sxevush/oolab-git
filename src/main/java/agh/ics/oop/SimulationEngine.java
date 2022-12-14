package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class SimulationEngine implements IEngine, Runnable {

    private MoveDirection[] directions;
    private final Vector2d[] positions;
    private final IWorldMap map;
    private final List<Animal> listAnimals = new ArrayList<>();

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.positions = positions;
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position);
            if (map.place(animal)) {
                listAnimals.add(animal);
            }
        }
    }

    public SimulationEngine(IWorldMap map, Vector2d[] positions, App app) {
        this.map = map;
        this.positions = positions;
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position);
            if (map.place(animal)) {
                listAnimals.add(animal);
                animal.addObserver(app);
            }
        }
    }

    public void setDirections(MoveDirection[] directions) {
        this.directions = directions;
    }

    @Override
    public void run() {
        for (int i = 0; i < directions.length; i++) {
            Animal currentAnimal = listAnimals.get(i % listAnimals.size());
            currentAnimal.move(directions[i]);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                out.println("wątek przerwany");
            }
        }
    }
}
