package agh.ics.oop;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public String toString() {
        switch (this) {
            case NORTH -> {
                return "północ";
            }
            case EAST -> {
                return "wschód";
            }
            case WEST -> {
                return "zachód";
            }
            case SOUTH -> {
                return "południe";
            }
        }

        return null;
    }

    public MapDirection next() {
        switch (this) {
            case NORTH -> {
                return EAST;
            }
            case EAST -> {
                return SOUTH;
            }
            case WEST -> {
                return NORTH;
            }
            case SOUTH -> {
                return WEST;
            }
        }

        return null;
    }

    public MapDirection previous() {
        switch (this) {
            case NORTH -> {
                return WEST;
            }
            case EAST -> {
                return NORTH;
            }
            case WEST -> {
                return SOUTH;
            }
            case SOUTH -> {
                return EAST;
            }
        }

        return null;
    }

    public Vector2d toUnitVector() {
        switch (this) {
            case NORTH -> {
                return new Vector2d(0, 1);
            }
            case EAST -> {
                return new Vector2d(1, 0);
            }
            case SOUTH -> {
                return new Vector2d(0, -1);
            }
            case WEST -> {
                return new Vector2d(-1, 0);
            }
        }

        return null;
    }




}
