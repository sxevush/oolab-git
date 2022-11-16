package agh.ics.oop;

import java.util.Objects;

public class Animal {

    private Vector2d initialPosition = new Vector2d(2, 2);
    private IWorldMap map;
    private MapDirection animalDirection = MapDirection.NORTH;
    private Vector2d animalPosition;

    public Animal(IWorldMap map) {
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.animalPosition = initialPosition;
    }

    public Vector2d getAnimalPosition() {
        return animalPosition;
    }

    public MapDirection getAnimalDirection() {
        return animalDirection;
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

    public boolean isAt(Vector2d position) { return Objects.equals(animalPosition, position); }

    public void move(MoveDirection direction) {
        {
            switch (direction) {
                case LEFT -> animalDirection = animalDirection.previous();
                case RIGHT -> animalDirection = animalDirection.next();
                case BACKWARD -> {
                    Vector2d animalPositionSubtracted = animalPosition.subtract(animalDirection.toUnitVector());
                    if (map.canMoveTo(animalPositionSubtracted)) {
                        animalPosition = animalPositionSubtracted;
                    }
                }
                case FORWARD -> {
                    Vector2d animalPositionAdded = animalPosition.add(animalDirection.toUnitVector());
                    if (map.canMoveTo(animalPositionAdded)) {
                        animalPosition = animalPositionAdded;
                    }
                }
            }
            //        animalPosition = animalPosition.lowerLeft(World.LOWER_BOUND)
//                .upperRight(World.UPPER_BOUND);
        }




    }






}
