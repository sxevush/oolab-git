package agh.ics.oop;

import java.util.*;

public class RectangularMap extends AbstractWorldMap {

    public static final Vector2d LOWER_LEFT_MAP = new Vector2d(0, 0);
    protected Vector2d sizeOfMap;
    protected List<Animal> animals = new ArrayList<>();


    public RectangularMap(Vector2d sizeOfMap) {
        this.sizeOfMap = sizeOfMap;
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(LOWER_LEFT_MAP)
                && position.precedes(sizeOfMap)
                && !this.isOccupied(position);
    }


    public boolean isOccupied(Vector2d position) {
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
    public boolean isOccupiedAnimal(Vector2d position) {
        return false;
    }

    @Override
    public boolean isOccupiedGrass(Vector2d position) {
        return false;
    }


    @Override
    public Animal objectAt(Vector2d position) {
        return animals.stream()
                .filter(animal -> Objects.equals(position, animal.getAnimalPosition()))
                .findFirst()
                .orElse(null);
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals); // not to change animals
    }


    @Override
    public String toString() {
        MapVisualizer drawing = new MapVisualizer(this);
        return drawing.draw(LOWER_LEFT_MAP, sizeOfMap);
    }
}
