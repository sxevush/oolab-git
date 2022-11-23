import agh.ics.oop.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class GrassFieldTest {

    // testy puszczane pojedynczo działają, wszystkie na raz już nie - proszę o pomoc :((
    // próbowałem pokombinować z tym co jest napisane tutaj:
    // https://stackoverflow.com/questions/42442016/junit-tests-influence-each-other
    // ale nie działało

    @Test
    public void doesTheMapExtend() {
        // start the engine
        String[] args = {"f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2)};
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        // collect data
        Animal animal1 = map.getAnimals().get(0);
        Vector2d res1 = animal1.getAnimalPosition();
        String res11 = animal1.toString();

        //check data with expected results
        Assertions.assertEquals(res1, new Vector2d(2, 16));
        Assertions.assertEquals(res11, "N");

    }

    @Test
    public void canTheAnimalsBumpIntoEachOther() {
        // start the engine
        String[] args = {"f", "r", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(new Vector2d(10, 5));
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,4) };
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        // collect data
        Animal animal1 = map.getAnimals().get(0);
        Vector2d res1 = animal1.getAnimalPosition();
        String res11 = animal1.toString();
        Animal animal2 = map.getAnimals().get(1);
        Vector2d res2 = animal2.getAnimalPosition();
        String res22 = animal2.toString();

        //check data with expected results
        Assertions.assertEquals(res1, new Vector2d(2, 3));
        Assertions.assertEquals(res11, "N");
        Assertions.assertEquals(res2, new Vector2d(2, 4));
        Assertions.assertEquals(res22, "E");

    }
}
