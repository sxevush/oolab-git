package agh.ics.oop;

import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {


    private final SortedSet<Vector2d> xSorted = new TreeSet<>((o1, o2) -> {
        if (o1.x == o2.x) { return o1.y - o2.y; }
        return o1.x - o2.x;
    });

    private final SortedSet<Vector2d> ySorted = new TreeSet<>((o1, o2) -> {
        if (o1.y == o2.y) { return o1.x - o2.x; }
        return o1.y - o2.y;
    });

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        xSorted.remove(oldPosition);
        ySorted.remove(oldPosition);
        xSorted.add(newPosition);
        ySorted.add(newPosition);
    }

    public void addPosition(Vector2d position) {
        xSorted.add(position);
        ySorted.add(position);
    }


    public Vector2d countLowerLeft() { return new Vector2d(xSorted.first().x, ySorted.first().y); }
    public Vector2d countUpperRight() { return new Vector2d(xSorted.last().x, ySorted.last().y); }
}

