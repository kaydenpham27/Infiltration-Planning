package pathFinding;

import common.Location;

public interface GridPathFinder {
    Path findPath(Location start, Location target);
    Iterable<Location> getNeighbors(Location location);
}
