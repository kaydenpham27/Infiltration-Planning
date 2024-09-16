package obstacles;

import common.Location;

public class Guard extends LocatableObstacle {
    public Guard(Location location) {
        super(location);
    }
    @Override
    public char getSymbol() {
        return ObstacleType.GUARD.getSymbol();
    }
    @Override
    public boolean isLocationObstructed(int x, int y) {
        // A guard obstructs its own location
        return (x == location.getX() && y == location.getY());
    }

    /**
     * Constructs new Guard object from the given string argument
     * @param s
     * @return
     */
    public static Guard parse(String s) {
       Location location = Location.parse(s);
       return new Guard(location);
    }

}
