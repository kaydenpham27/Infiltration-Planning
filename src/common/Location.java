package common;

import java.util.Objects;

public class Location {
    private final int x;
    private final int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    /**
     * Return true if the given location and object type if equal to this location information
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Location location = (Location) obj;
        return (x == location.x && y == location.y);
    }
    /**
     * Returns the hash code of this location
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    /**
     * Returns a string representation of this location
     */
    @Override
    public String toString() {
        return "Location{" + "x = " + x + ", y = " + y + "}";
    }
    /**
     * Parses the given string into a Location object
     */
    public static Location parse(String s) {
        String[] parts = s.split(",");
        return new Location(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

}
