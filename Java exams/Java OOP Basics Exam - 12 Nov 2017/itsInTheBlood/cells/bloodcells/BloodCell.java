package itsInTheBlood.cells.bloodcells;

import itsInTheBlood.cells.Cell;

public abstract class BloodCell extends Cell {

    public BloodCell(String id, int health, int positionRow, int positionCol) {
        super(id, health, positionRow, positionCol);
    }

    protected abstract String getParamName();

    @Override
    public String getType() {
        return "BloodCell";
    }

    @Override
    public String toString() {
        return String.format("--------Health: %d | %s: %d | Energy: %d\n"
                , this.getHealth(), this.getParamName(), this.getExtraParam(), this.getEnergy());
    }
}
