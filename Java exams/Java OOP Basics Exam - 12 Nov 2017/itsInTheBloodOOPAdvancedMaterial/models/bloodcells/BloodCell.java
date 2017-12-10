package itsInTheBlood.models.bloodcells;

import itsInTheBlood.models.Cell;

public abstract class BloodCell extends Cell {

    private static final String BLOOD_CELL_TYPE = "bloodcell";

    protected BloodCell(String id, int health, int positionRow, int positionCow) {
        super(id, health, positionRow, positionCow);
    }

    @Override
    public String getCellType() {
        return BLOOD_CELL_TYPE;
    }
}
