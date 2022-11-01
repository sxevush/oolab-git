import agh.ics.oop.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalTest {

    @Test
    public void AnimalTest1() {
        String[] pom = {"f", "b", "k", "p"};
        Animal animal_pom = new Animal();
        OptionsParser optionsParser = new OptionsParser();
        MoveDirection[] tab = optionsParser.parse(pom);

        for (MoveDirection temp : tab) {
            animal_pom.move(temp);
        }

        String tmp = animal_pom.toString();
        Assertions.assertEquals(tmp, "(2, 2)" + ", " + "północ");

    }

    @Test
    public void AnimalTest2() {
        String[] pom = {"f", "l", "k", "b", "r", "z"};
        Animal animal_pom = new Animal();
        OptionsParser optionsParser = new OptionsParser();
        MoveDirection[] tab = optionsParser.parse(pom);

        for (MoveDirection temp : tab) {
            animal_pom.move(temp);
        }

        String tmp = animal_pom.toString();
        Assertions.assertEquals(tmp, "(3, 3)" + ", " + "północ");
    }

    @Test
    public void AnimalTest3() {
        String[] pom = {"f", "f", "f", "s", "w", "f"};
        Animal animal_pom = new Animal();
        OptionsParser optionsParser = new OptionsParser();
        MoveDirection[] tab = optionsParser.parse(pom);

        for (MoveDirection temp : tab) {
            animal_pom.move(temp);
        }

        String tmp = animal_pom.toString();
        Assertions.assertEquals(tmp, "(2, 4)" + ", " + "północ");
    }

    @Test
    public void AnimalTest4() {
        String[] pom = {"f", "f", "f", "s", "w", "f", "k", "forward", "hej", "forward", "right", "r", "r"};
        Animal animal_pom = new Animal();
        OptionsParser optionsParser = new OptionsParser();
        MoveDirection[] tab = optionsParser.parse(pom);

        for (MoveDirection temp : tab) {
            animal_pom.move(temp);
        }

        String tmp = animal_pom.toString();
        Assertions.assertEquals(tmp, "(2, 4)" + ", " + "zachód");
    }

}