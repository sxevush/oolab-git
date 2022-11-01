package agh.ics.oop;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class Vector2d {

    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() { return "(%d, %d)".formatted(x, y); }

    public boolean precedes(@NotNull Vector2d other) { return this.x <= other.x && this.y <= other.y; }

    public boolean follows(@NotNull Vector2d other) { return this.x >= other.x && this.y >= other.y; }

    public Vector2d add(@NotNull Vector2d other) { return new Vector2d(this.x + other.x, this.y + other.y); }

    public Vector2d subtract(@NotNull Vector2d other) { return new Vector2d(this.x - other.x, this.y - other.y); }

    public Vector2d upperRight(@NotNull Vector2d other) {
        return new Vector2d(Math.max(x, other.x), Math.max(y, other.y));
    }

    public Vector2d lowerLeft(@NotNull Vector2d other) {
        return new Vector2d(Math.min(x, other.x), Math.min(y, other.y));
    }

    public Vector2d opposite() { return new Vector2d(-x, -y); }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        else if (!(other instanceof Vector2d)) return false;
        else return this.x == ((Vector2d) other).x && this.y == ((Vector2d) other).y;
    }
    @Override
    public int hashCode() { return Objects.hash(x, y); }


}
