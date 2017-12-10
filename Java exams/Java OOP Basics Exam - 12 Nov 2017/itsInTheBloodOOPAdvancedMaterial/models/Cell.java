package itsInTheBlood.models;

import itsInTheBlood.interfaces.AbstractCell;

public abstract class Cell implements AbstractCell {

    private static final String TOP_OF_CELL_STRING_FORMAT = "------Cell %s [%d,%d]\n";
    private static final String CELL_STRING_REPRESENTATION_FORMAT = "%s--------Health: %d | %s: %s | Energy: %d\n";
    private static final int HEALTH_OF_A_DEAD_CELL = 0;

    private String id;
    private int health;
    private int positionRow;
    private int positionCol;

    protected Cell(String id, int health, int positionRow, int positionCow) {
        this.id = id;
        this.health = health;
        this.positionRow = positionRow;
        this.positionCol = positionCow;
    }

    public void reduceHealth(int value) {
        this.health -= value;
    }

    public boolean isDead() {
        return this.health <= HEALTH_OF_A_DEAD_CELL;
    }

    public void increaseHealth(int value) {
        this.health += value;
    }

    public int getPositionRow() {
        return this.positionRow;
    }

    public int getPositionCol() {
        return this.positionCol;
    }

    public void setRowAndCol(int row, int col) {
        this.positionRow = row;
        this.positionCol = col;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    protected abstract int getAdditionalParameter();

    protected abstract String getAdditionalParameterName();

    @Override
    public String toString() {
        String topOfCellString = String.format(TOP_OF_CELL_STRING_FORMAT, this.id, this.positionRow, this.positionCol);
        return String.format(CELL_STRING_REPRESENTATION_FORMAT, topOfCellString
                , this.health, this.getAdditionalParameterName(), this.getAdditionalParameter(), getEnergy());
    }
}
