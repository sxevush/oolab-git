package agh.ics.oop;

public class OptionsParser {
    public MoveDirection[] parse(String[] args) {

        int n = 0;

        for (String arg : args) {
            switch (arg) {
                case "f", "l", "forward", "b", "backward", "right", "r", "left" -> n++;
            }
        }

        MoveDirection[] result = new MoveDirection[n];

        int cnt = 0;
        for (String arg : args) {
            switch (arg) {
                case "f", "forward" -> {
                    result[cnt] = MoveDirection.FORWARD;
                    cnt++;
                }
                case "b", "backward" -> {
                    result[cnt] = MoveDirection.BACKWARD;
                    cnt++;
                }
                case "r", "right" -> {
                    result[cnt] = MoveDirection.RIGHT;
                    cnt++;
                }
                case "l", "left" -> {
                    result[cnt] = MoveDirection.LEFT;
                    cnt++;
                }
            }
        }

        return result;


    }
}
