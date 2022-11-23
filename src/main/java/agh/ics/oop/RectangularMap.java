package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {

    private final int width;
    private final int height;
    public static final Vector2d LOWER_LEFT_MAP = new Vector2d(0, 0);


    public List<Animal> animals = new ArrayList<>();


    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }



    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position != null) {
            if (!isOccupied(position)) {
                return (position.follows(LOWER_LEFT_MAP) && position.precedes(new Vector2d(width, height)));
            }
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals) {
            if (position.equals(animal.getAnimalPosition())) {
                return true;
            }
        }
        return false;
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
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (isOccupied(animal.getAnimalPosition())) { return animal; }
        }
        return null;
    }

    @Override
    public String toString() {
        MapVisualizer drawing = new MapVisualizer(this);
        return drawing.draw(LOWER_LEFT_MAP, new Vector2d(width, height));
    }
}
