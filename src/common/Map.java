package common;

import obstacles.Obstacle;
import pathFinding.BFSPathFinder;
import pathFinding.GridPathFinder;
import pathFinding.Path;

import java.util.ArrayList;

public class Map {
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();
    private final int PADDING = 2;

    public Map(ArrayList<Obstacle> obstacles) {
        this.obstacles.addAll(obstacles);
    }

    public boolean isLocationObstructed(int x, int y) {
        return getObstacleLocation(x, y) != null;
    }
    /**
     * Returns the obstacle at the given location, or null if there is no obstacle at the given location
     * @param x
     * @param y
     * @return
     */
    private Obstacle getObstacleLocation(int x, int y) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.isLocationObstructed(x, y)) {
                return obstacle;
            }
        }
        return null;
    }

    /**
     * Returns the string representation of the map
     * @param matrix
     * @return
     */
    private String matrixToString(char[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : matrix) {
            for (char symbol: row) {
                sb.append(symbol);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    /**
     * Returns a string representation of the map given the mission's start and end location
     * @param start
     * @param target
     * @return
     */
    public String getSolvedMap(Location start, Location target) {
        // find path from start to target
        GridPathFinder pathFinder = new BFSPathFinder(this);
        Path path = pathFinder.findPath(start, target);

        Location topLeft, bottomRight;
        int maxX, maxY, minX, minY;
        maxX = Math.max(start.getX(), target.getX());
        minX = Math.min(start.getX(), target.getX());
        maxY = Math.max(start.getY(), target.getY());
        minY = Math.min(start.getY(), target.getY());
        for (Location location: path) {
            minX = Math.min(minX, location.getX());
            maxX = Math.max(maxX, location.getX());
            minY = Math.min(minY, location.getY());
            maxY = Math.max(maxY, location.getY());
        }
        topLeft = new Location(minX - PADDING, minY - PADDING);
        bottomRight = new Location(maxX + PADDING, maxY + PADDING);

        char[][] solvedMap = new char[maxX - minX + 2 * PADDING + 1][maxY - minY + 2 * PADDING + 1];
        for (int x = topLeft.getX(); x <= bottomRight.getX(); x ++) {
            for (int y = topLeft.getY(); y <= bottomRight.getY(); y ++) {
                int i = x - topLeft.getX();
                int j = y - topLeft.getY();

                if (path.isLocationInPath(x, y)) {
                    solvedMap[i][j] = path.getSymbolForLocation(x, y);
                    continue;
                }

                Obstacle obstructingObstacle = getObstacleLocation(x, y);
                if (obstructingObstacle != null) {
                    solvedMap[i][j] = obstructingObstacle.getSymbol();
                    continue;
                }
                solvedMap[i][j] = '.';
            }
        }
        return matrixToString(solvedMap);
    }
}
