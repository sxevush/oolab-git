package agh.ics.oop;

import java.util.*;

public class RectangularMap extends AbstractWorldMap {

    protected Vector2d lowerLeftMap = new Vector2d(0, 0);
    public RectangularMap(Vector2d sizeOfMap) {
        this.upperRightMap = sizeOfMap;
    }

    public Vector2d countLowerLeft () {
        return lowerLeftMap;
    }

    @Override
    public Vector2d countUpperRight() {
        return upperRightMap;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerLeftMap)
                && position.precedes(upperRightMap)
                && !this.isOccupiedAnimal(position);
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


}
