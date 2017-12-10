package itsInTheBlood.cells;

public abstract class Cell {

    private String id;
    private int health;
    private int positionRow;
    private int positionCol;

    public Cell(String id, int health, int positionRow, int positionCol) {
        this.id = id;
        this.health = health;
        this.positionRow = positionRow;
        this.positionCol = positionCol;
    }

    public int getPositionCol() {
        return positionCol;
    }

    public int getPositionRow() {
        return positionRow;
    }

    public String getId() {
        return id;
    }

    public void reduceHealth(int dmg) {
        this.health -= dmg;
    }

    public void increaseHealth(int points) {
        this.health += points;
    }

    public int getHealth() {
        return health;
    }

    public void setPositionRow(int positionRow) {
        this.positionRow = positionRow;
    }

    public void setPositionCol(int positionCol) {
        this.positionCol = positionCol;
    }

    public abstract int getEnergy();

    protected abstract int getExtraParam();

    public abstract String getType();

    @Override
    public abstract String toString();
}
