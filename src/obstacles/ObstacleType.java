package obstacles;

public enum ObstacleType {
    GUARD("g", 'g'),
    FENCE("f", 'f'),
    SENSOR("s", 's'),
    CAMERA("c", 'c');
    private final String argumentName;
    private final char symbol;

    ObstacleType(String argumentName, char symbol) {
        this.argumentName = argumentName;
        this.symbol = symbol;
    }
    public String getArgumentName() { return argumentName; }
    public char getSymbol() { return symbol; }

}
