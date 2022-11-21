//import agh.ics.oop.*;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Assertions;
//
//public class Lab4Testy {
//
//    @Test
//    public void basicTest() {
//        String[] pom = {"f", "b", "r", "l"};
//        MoveDirection[] directions = new OptionsParser().parse(pom);
//        RectangularMap map = new RectangularMap(10, 5);
//        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
//        SimulationEngine engine = new SimulationEngine(directions, map, positions);
//        engine.run();
//        Animal animal1 = map.animals.get(0);
//        Vector2d res1 = animal1.getAnimalPosition();
//        String res11 = animal1.toString();
//        Animal animal2 = map.animals.get(1);
//        Vector2d res2 = animal2.getAnimalPosition();
//        String res22 = animal2.toString();
//        Assertions.assertEquals(res1, new Vector2d(2, 3));
//        Assertions.assertEquals(res11, "W");
//        Assertions.assertEquals(res2, new Vector2d(3, 3));
//        Assertions.assertEquals(res22, "N");
//    }
//    @Test
//    public void basicTestWithDifferentData() {
//        String[] pom = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
//        MoveDirection[] directions = new OptionsParser().parse(pom);
//        RectangularMap map = new RectangularMap(10, 5);
//        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
//        SimulationEngine engine = new SimulationEngine(directions, map, positions);
//        engine.run();
//        Animal animal1 = map.animals.get(0);
//        Vector2d res1 = animal1.getAnimalPosition();
//        String res11 = animal1.toString();
//        Animal animal2 = map.animals.get(1);
//        Vector2d res2 = animal2.getAnimalPosition();
//        String res22 = animal2.toString();
//        Assertions.assertEquals(res1, new Vector2d(2, 5));
//        Assertions.assertEquals(res11, "N");
//        Assertions.assertEquals(res2, new Vector2d(5, 4));
//        Assertions.assertEquals(res22, "E");
//    }
//}