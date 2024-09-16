package obstacles;

import common.Direction;
import common.Location;

public class Camera extends LocatableObstacle {
    private final Direction facingDirection;

    public Camera(Location location, Direction facingDirection) {
        super(location);
        this.facingDirection = facingDirection;
    }

    @Override
    public char getSymbol() { return ObstacleType.CAMERA.getSymbol(); }

    @Override
    public boolean isLocationObstructed(int x, int y) {
        int dx = x - location.getX();
        int dy = y - location.getY();
        switch (facingDirection) {
            case NORTH:
                return Math.abs(dx) <= -dy;
            case EAST:
                return Math.abs(dy) <= dx;
            case WEST:
                return Math.abs(dy) <= -dx;
            case SOUTH:
                return Math.abs(dx) <= dy;
            default:
                throw new IllegalArgumentException("Camera direction must be one of NORTH, EAST, WEST, SOUTH");
        }
    }

    public static Camera parse(String arg) {
        String[] parts = arg.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Sensor must have 3 inputs (x, y, direction)");
        }
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Direction direction = Direction.parse(parts[2]);
        Location location = new Location(x, y);
        return new Camera(location, direction);
    }
}
