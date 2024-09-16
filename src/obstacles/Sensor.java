package obstacles;

import common.Location;
import common.Map;

public class Sensor extends LocatableObstacle {
    private final double radius;

    public Sensor(Location location, double radius) {
        super(location);
        this.radius = radius;
    }

    @Override
    public boolean isLocationObstructed(int x, int y) {
        int dx = x - location.getX();
        int dy = y - location.getY();
        return Math.sqrt(dx * dx + dy * dy) <= radius;
    }

    @Override
    public char getSymbol() { return ObstacleType.SENSOR.getSymbol(); }

    public static Sensor parse(String arg) {
        String[] parts = arg.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Sensor must have 3 inputs (x, y, range)");
        }
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        double r = Double.parseDouble(parts[2]);
        Location location = new Location(x, y);

        return new Sensor(location, r);
    }
}
