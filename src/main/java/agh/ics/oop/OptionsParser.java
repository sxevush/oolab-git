package agh.ics.oop;

import java.util.Objects;
import java.util.stream.Stream;

public class OptionsParser {
    public MoveDirection[] parse(String[] args) {

        return Stream.of(args)
                .map(OptionsParser::convertToMoveDirection)
                .filter(Objects::nonNull)
                .toArray(MoveDirection[]::new);

    }

    private static MoveDirection convertToMoveDirection(String instruction) {
        return switch (instruction) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "r", "right" -> MoveDirection.RIGHT;
            case "l", "left" -> MoveDirection.LEFT;
            default -> throw new IllegalArgumentException(instruction + " is not legal move specification");
        };
    }
}
