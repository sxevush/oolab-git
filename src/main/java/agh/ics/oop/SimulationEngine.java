package agh.ics.oop;


public class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;
    private final Vector2d[] positions;
    private final IWorldMap map;

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
                map.place(animal);
            }
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < directions.length; i++) {
            Animal currentAnimal = map.getAnimals().get(i % map.getAnimals().size());
            currentAnimal.move(directions[i]);
        }
    }
}
