package obstacles;

import common.Location;

/**
 * Represents an obstacle that can be located on the map
 * Can not create object directly from an abstract class -> must be done through inheriting
 * a class that extends on this abstract class
 */
public abstract class LocatableObstacle implements Obstacle {
    protected final Location location;

    public LocatableObstacle(Location location) {
        this.location = location;
    }
    public LocatableObstacle(int x, int y) {
        this.location = new Location(x, y);
    }
    public Location getLocation() { return location; }

    public abstract char getSymbol();
    public abstract boolean isLocationObstructed(int x, int y);
}
