package agh.ics.oop;

import static java.lang.System.out;


public class World {

    public static Direction[] change(String[] directions) {
        int n = directions.length;

        Direction[] tab = new Direction[n];

        for(int i = 0; i < n; i++) {
            switch (directions[i]) {
                case "f" -> tab[i] = Direction.FORWARD;
                case "b" -> tab[i] = Direction.BACKWARD;
                case "r" -> tab[i] = Direction.RIGHT;
                case "l" -> tab[i] = Direction.LEFT;
                default -> tab[i] = Direction.RANDOM;
            }
        }

        return tab;
    }

    public static void run(Direction[] tab) {

        out.print("\n");

//        String joinedDirections = String.join(", ", tab);
//        out.print(joinedDirections);

        int n = tab.length;
        for (int i = 0; i < n-1; i++) {
            out.print(tab[i] + ", ");
        }
        out.print(tab[n-1]);

        out.print("\n");

        for (Direction arg : tab) {
            switch (arg) {
                case FORWARD -> out.print("Zwierzak idzie do przodu.\n");
                case BACKWARD -> out.print("Zwierzak idzie do tylu.\n");
                case RIGHT -> out.print("Zwierzak skreca w prawo.\n");
                case LEFT -> out.print("Zwierzak skreca w lewo.\n");
                case RANDOM -> out.print("Zwierzak nie rusza sie.\n");
            }
        }
    }

    public static void main(String[] directions) {
        out.print("system wystartowal");
        out.print("\n");

        Direction[] tab = change(directions);
        run(tab);

        out.print("\n");
        out.print("system zakończyl dzialanie\n\n");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));



    }

}