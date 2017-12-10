package itsInTheBlood.models.microbes;

import itsInTheBlood.models.Cell;

public abstract class Microbe extends Cell {

    private static final String CELL_TYPE_NAME = "microbe";
    private static final String ADDITIONAL_PARAM_NAME = "Virulence";

    private int virulence;

    protected Microbe(String id, int health, int positionRow, int positionCow, int virulence) {
        super(id, health, positionRow, positionCow);
        this.virulence = virulence;
    }

    @Override
    protected int getAdditionalParameter() {
        return this.virulence;
    }

    @Override
    public String getCellType() {
        return CELL_TYPE_NAME;
    }

    @Override
    protected String getAdditionalParameterName() {
        return ADDITIONAL_PARAM_NAME;
    }
}
