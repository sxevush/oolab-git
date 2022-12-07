package agh.ics.oop;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    abstract protected Vector2d countLowerLeft ();

    abstract protected Vector2d countUpperRight ();
    protected Map<Vector2d, Animal> animals = new HashMap<>();

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        animals.put(newPosition, animals.get(oldPosition));
        animals.remove(oldPosition);
    }

    public Map<Vector2d, Animal> getAnimals() {
        return animals;
    }


    @Override
    public boolean isOccupiedAnimal(Vector2d position) {
        return animals.containsKey(position);
    }


    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getAnimalPosition())) {
            animals.put(animal.getAnimalPosition(), animal);
            animal.addObserver(this);
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
