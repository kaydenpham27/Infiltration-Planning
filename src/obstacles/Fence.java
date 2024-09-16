package obstacles;

import common.Location;

public class Fence extends LocatableObstacle {
    private final Location end;
    public Fence(Location start, Location end) {
        super(start);
        this.end = end;
        if (!isAxial()) {
            throw new IllegalArgumentException("Fence must be vertical or horizontal");
        }
    }
    private boolean isAxial() {
        int dx = location.getX() - end.getX();
        int dy = location.getY() - end.getY();
        return !(dx != 0 && dy != 0);
    }

    @Override
    public boolean isLocationObstructed(int x, int y) {
        int dxE = x - end.getX();
        int dyE = y - end.getY();
        int dxS = x - location.getX();
        int dyS = y - location.getY();
        return dxS * dxE <= 0 && dyS * dyE <= 0;
    }

    @Override
    public char getSymbol() { return ObstacleType.FENCE.getSymbol(); }

    public static Fence parse(String arg) {
        String[] parts = arg.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Fence must have 4 coordinates: startX, startY, endX, endY");
        }
        int startX = Integer.parseInt(parts[0]);
        int startY = Integer.parseInt(parts[1]);
        int endX = Integer.parseInt(parts[2]);
        int endY = Integer.parseInt(parts[3]);
        Location start = new Location(startX, startY);
        Location end = new Location(endX, endY);

        return new Fence(start, end);
    }
}
