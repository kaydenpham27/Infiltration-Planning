package common;

public enum Direction {
    NORTH, SOUTH, EAST, WEST;
    public static Direction parse(String arg) {
        switch (arg.toUpperCase()) {
            case "N":
                return NORTH;
            case "E":
                return EAST;
            case "W":
                return WEST;
            case "S":
                return SOUTH;
            default:
                throw new IllegalArgumentException("Direction must be one of n, s, e, w");
        }
    }
    public static Direction getDirection(Location from, Location to) {
        int dx = to.getX() - from.getX();
        int dy = to.getY() - from.getY();
        // Remain unchanged
        if (dx == 0 && dy == 0) return null;

        if (dx == 0) return (dy > 0 ? EAST : WEST);
        if (dy == 0) return (dx > 0 ? SOUTH: NORTH);
        return null;
    }
}
