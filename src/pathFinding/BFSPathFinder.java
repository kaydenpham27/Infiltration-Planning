package pathFinding;

import common.Map;
import common.Location;

import java.util.*;

public class BFSPathFinder implements GridPathFinder {
    private Map map;
    public BFSPathFinder(Map map) { this.map = map; }

    @Override
    public Iterable<Location> getNeighbors(Location location) {
        ArrayList<Location> neighbors = new ArrayList<>();
        int x = location.getX();
        int y = location.getY();
        if (!map.isLocationObstructed(x - 1, y)) neighbors.add(new Location(x - 1, y));
        if (!map.isLocationObstructed(x + 1, y)) neighbors.add(new Location(x + 1, y));
        if (!map.isLocationObstructed(x, y - 1)) neighbors.add(new Location(x, y - 1));
        if (!map.isLocationObstructed(x, y + 1)) neighbors.add(new Location(x, y + 1));
        return neighbors;
    }

    @Override
    public Path findPath(Location start, Location target) {
        Queue<Location> queue = new LinkedList<>();
        ArrayList<Location> visited = new ArrayList<>();
        HashMap<Location, Location> previous = new HashMap<>();

        queue.add(start); visited.add(start); previous.put(start, null);
        while (!queue.isEmpty()) {
            Location current = queue.remove();
            if (current.equals(target)) break;
            Iterable<Location> neighbors = getNeighbors(current);
            for (Location neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    previous.put(neighbor, current);
                }
            }
        }
        List<Location> path = new Stack<>();
        Location current = target;
        while (previous.containsKey(current)) {
            path.add(current);
            current = previous.get(current);
        }
        Collections.reverse(path);
        return new Path(path);
    }
}
