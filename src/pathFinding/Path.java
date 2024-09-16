package pathFinding;

import common.Direction;
import common.Location;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Path implements Iterable<Location> {
    private List<Location> inner;

    public Path(List<Location> inner) { this.inner = inner; }
    public boolean isLocationInPath(int x, int y) {
        return inner.contains(new Location(x, y));
    }
    private char getSymbolFromDirections(Direction from, Direction to) {
        if (from == to) {
            switch (from) {
                case Direction.NORTH, Direction.SOUTH:
                    return '║';
                case Direction.EAST, Direction.WEST:
                    return '═';
                default:
                    break;
            }
        }
        if (from == Direction.SOUTH && to == Direction.EAST ||
                from == Direction.WEST && to == Direction.NORTH) {
            return '╚';
        }
        if (from == Direction.SOUTH && to == Direction.WEST ||
                from == Direction.EAST && to == Direction.NORTH) {
            return '╝';
        }
        if (from == Direction.NORTH && to == Direction.EAST ||
                from == Direction.WEST && to == Direction.SOUTH) {
            return '╔';
        }
        if (from == Direction.NORTH && to == Direction.WEST ||
                from == Direction.EAST && to == Direction.SOUTH) {
            return '╗';
        }
        return ' ';
    }
    public char getSymbolForLocation(int x, int y) {
        Location location = new Location(x, y);
        int locationIndex = inner.indexOf(location);
        if (locationIndex == 0) return 'S';
        if (locationIndex == inner.size() - 1) return 'E';

        Location previousLocation = inner.get(locationIndex - 1);
        Location nextLocation = inner.get(locationIndex + 1);
        Direction directionFrom = Direction.getDirection(previousLocation, location);
        Direction directionTo = Direction.getDirection(location, nextLocation);
        return getSymbolFromDirections(directionFrom, directionTo);
    }
    @Override
    public Iterator<Location> iterator() {
        return inner.iterator();
    }
    @Override
    public void forEach(Consumer<? super Location> action) {
        Iterable.super.forEach(action);
    }
    @Override
    public Spliterator<Location> spliterator() {
        return Iterable.super.spliterator();
    }
}
