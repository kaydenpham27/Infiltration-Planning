package obstacles;

public interface Obstacle {
    char getSymbol();
    // return true is the location is obstructed by the obstacle or false otherwise
    boolean isLocationObstructed(int x, int y);
}
