package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal {

    private final IWorldMap map;
    private MapDirection animalDirection;
    private Vector2d animalPosition;
    protected List<IPositionChangeObserver> observers = new ArrayList<>();

    protected void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    protected void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    protected void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.animalPosition = initialPosition;
        this.animalDirection = MapDirection.NORTH;
    }


    public Vector2d getAnimalPosition() {
        return animalPosition;
    }


    @Override
    public String toString() {
        return switch (animalDirection) {
            case NORTH -> "N";
            case SOUTH -> "S";
            case WEST -> "W";
            case EAST -> "E";
        };
    }


    public void move(MoveDirection direction) {
        switch (direction) {
            case LEFT -> animalDirection = animalDirection.previous();
            case RIGHT -> animalDirection = animalDirection.next();
            case BACKWARD -> {
                Vector2d animalPositionSubtracted = animalPosition.subtract(animalDirection.toUnitVector());
                if (map.canMoveTo(animalPositionSubtracted)) {
                    positionChanged(animalPosition, animalPositionSubtracted);
                    animalPosition = animalPositionSubtracted;

                }
            }
            case FORWARD -> {
                Vector2d animalPositionAdded = animalPosition.add(animalDirection.toUnitVector());
                if (map.canMoveTo(animalPositionAdded)) {
                    positionChanged(animalPosition, animalPositionAdded);
                    animalPosition = animalPositionAdded;
                }
            }
        }
    }






}
