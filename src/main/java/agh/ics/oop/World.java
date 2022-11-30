package agh.ics.oop;

import static java.lang.System.out;


public class World {

    public static void main(String[] args) {

        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(new Vector2d(10, 5));
        Vector2d[] positions = { new Vector2d(2,2) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        out.print(map);

    }

}