package agh.ics.oop;

import java.util.Objects;

public abstract class AbstractWorldMap implements IWorldMap {

    protected Vector2d lowerLeftMap;
    protected Vector2d upperRightMap;
    abstract public boolean canMoveTo(Vector2d position);


    abstract public Vector2d countLowerLeft ();

    abstract public Vector2d countUpperRight ();

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
        this.lowerLeftMap = countLowerLeft();
        this.upperRightMap = countUpperRight();
        return drawing.draw(lowerLeftMap, upperRightMap);
    }
}
