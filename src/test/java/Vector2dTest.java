import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Vector2dTest {

    @Test
    void testEquals() {
        String arg = "(0, 1)";
        assertEquals(new Vector2d(0, 1).equals(new Vector2d(0, 1)), true);
        assertEquals(new Vector2d(0, 1).equals(new Vector2d(1, 1)), false);
        assertEquals(new Vector2d(0, 1).equals(arg), false);
    }

    @Test
    void testToString() {
        assertEquals(new Vector2d(0, 1).toString(), "(0, 1)");
        assertEquals(new Vector2d(4, 1).toString(), "(4, 1)");
    }

    @Test
    void testPrecedes() {
        assertEquals(new Vector2d(0, 1).precedes(new Vector2d(1, 0)), false);
        assertEquals(new Vector2d(0, 1).precedes(new Vector2d(1, 2)), true);
    }

    @Test
    void testFollows() {
        assertEquals(new Vector2d(0, 1).follows(new Vector2d(1, 0)), false);
        assertEquals(new Vector2d(0, 1).follows(new Vector2d(-1, -2)), true);
    }

    @Test
    void testUpperRight() {
        assertEquals(new Vector2d(0, 1).upperRight(new Vector2d(1, 0)), new Vector2d(1, 1));
        assertEquals(new Vector2d(0, 0).upperRight(new Vector2d(1, 2)), new Vector2d(1, 2));

    }

    @Test
    void testLowerLeft() {
        assertEquals(new Vector2d(0, 1).lowerLeft(new Vector2d(1, 0)), new Vector2d(0, 0));
        assertEquals(new Vector2d(0, 1).lowerLeft(new Vector2d(-1, 1)), new Vector2d(-1, 1));

    }

    @Test
    void testAdd() {
        assertEquals(new Vector2d(3, 1).add(new Vector2d(1, -2)), new Vector2d(4, -1));
    }

    @Test
    void testSubtract() {
        assertEquals(new Vector2d(3, 1).subtract(new Vector2d(1, 2)), new Vector2d(2, -1));
    }

    @Test
    void testOpposite() {
        assertEquals(new Vector2d(0, 1).opposite(), new Vector2d(0, -1));
    }
}
