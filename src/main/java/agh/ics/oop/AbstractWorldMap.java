package agh.ics.oop;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    abstract public Vector2d countLowerLeft ();
    abstract public Vector2d countUpperRight ();
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    public MapBoundary mapBoundary = new MapBoundary();

    public MapBoundary getMapBoundary() {
        return mapBoundary;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = animals.remove(oldPosition);
        if (animal != null) {
            animals.put(newPosition, animal);
        }
        mapBoundary.positionChanged(oldPosition, newPosition);
    }

    public Map<Vector2d, Animal> getAnimals() {
        return animals;
    }


    @Override
    public boolean isOccupiedAnimal(Vector2d position) {
        return animals.containsKey(position);
    }


    @Override
    public boolean place(Animal animal) throws IllegalArgumentException{
        if (canMoveTo(animal.getAnimalPosition())) {
            animals.put(animal.getAnimalPosition(), animal);
            mapBoundary.addPosition(animal.getAnimalPosition());
            animal.addObserver(this);
            return true;
        }
        throw new IllegalArgumentException("could not place the animal on " + animal.getAnimalPosition());
    }

    @Override
    public String toString() {
        MapVisualizer drawing = new MapVisualizer(this);
        return drawing.draw(countLowerLeft(), countUpperRight());
    }

}
