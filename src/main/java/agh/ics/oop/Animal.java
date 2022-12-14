package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal {

    private final IWorldMap map;
    private MapDirection animalDirection;
    private Vector2d animalPosition;
    protected MapBoundary mapBoundary;
    protected List<IWorldMap> observers = new ArrayList<>();



    public void addObserver(IWorldMap observer) {
        observers.add(observer);
    }

    public void removeObserver(IWorldMap observer) {
        observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IWorldMap observer : observers) {
            observer.positionChanged(oldPosition, newPosition);
            mapBoundary.positionChanged(oldPosition, newPosition);
        }
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.animalPosition = initialPosition;
        this.animalDirection = MapDirection.NORTH;
        mapBoundary = map.getMapBoundary();
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
                    mapBoundary.positionChanged(animalPosition, animalPositionSubtracted);
                    animalPosition = animalPositionSubtracted;

                }
            }
            case FORWARD -> {
                Vector2d animalPositionAdded = animalPosition.add(animalDirection.toUnitVector());
                if (map.canMoveTo(animalPositionAdded)) {
                    positionChanged(animalPosition, animalPositionAdded);
                    mapBoundary.positionChanged(animalPosition, animalPositionAdded);
                    animalPosition = animalPositionAdded;
                }
            }
        }
    }






}
