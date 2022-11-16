package agh.ics.oop;

public class Animal {
    public final MapDirection animalOrientationStart = MapDirection.NORTH;
    public final Vector2d animalPositionStart = new Vector2d(2, 2);
    private MapDirection animalDirection;
    private Vector2d animalPosition;

    public Animal(){
        this.animalDirection = animalOrientationStart;
        this.animalPosition = animalPositionStart;
    }

    public String toString() {
        return "(%d, %d)".formatted(animalPosition.x, animalPosition.y) + ", " + animalDirection;
    }

    public boolean isAt(Vector2d position) { return this.animalPosition.equals(position); }

    public void move(MoveDirection direction) {
        switch (direction) {
            case LEFT -> animalDirection = animalDirection.previous();
            case RIGHT -> animalDirection = animalDirection.next();
            case BACKWARD -> animalPosition = animalPosition.subtract(animalDirection.toUnitVector());
            case FORWARD -> animalPosition = animalPosition.add(animalDirection.toUnitVector());
        }

        animalPosition = animalPosition.lowerLeft(new Vector2d(4, 4));
        animalPosition = animalPosition.upperRight(new Vector2d(0, 0));

    }






}
