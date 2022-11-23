package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractWorldMap implements IWorldMap {

    abstract protected Vector2d countLowerLeft ();

    abstract protected Vector2d countUpperRight ();
    protected List<Animal> animals = new ArrayList<>();

    public List<Animal> getAnimals() {
        return List.copyOf(animals);
    }


    @Override
    public boolean isOccupiedAnimal(Vector2d position) {
        return animals.stream()
                .anyMatch(animal -> Objects.equals(position, animal.getAnimalPosition()));
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getAnimalPosition())) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        MapVisualizer drawing = new MapVisualizer(this);
        return drawing.draw(countLowerLeft(), countUpperRight());
    }

}
