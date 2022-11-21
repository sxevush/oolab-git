package agh.ics.oop;

import java.util.Objects;

public class Animal {

    private GrassField map;
    private MapDirection animalDirection;
    private Vector2d animalPosition;

//    public Animal() {
//        this(new GrassField());
//    }

    public Animal(GrassField map, Vector2d initialPosition) {
        this.map = map;
        this.animalPosition = initialPosition;
        this.animalDirection = MapDirection.NORTH;
    }

    public Animal(GrassField map) {
        this(map, new Vector2d(2, 2));
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
        }
    }






}
