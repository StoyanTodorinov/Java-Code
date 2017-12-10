package itsInTheBlood.cells.microbes;

import itsInTheBlood.cells.Cell;

public abstract class Microbe extends Cell {

    private int virulence;

    public Microbe(String id, int health, int positionRow, int positionCol, int virulence) {
        super(id, health, positionRow, positionCol);
        this.virulence = virulence;
    }

    @Override
    public String getType() {
        return "Microbe";
    }

    @Override
    protected int getExtraParam() {
        return this.virulence;
    }

    @Override
    public String toString() {
        return String.format("--------Health: %d | Virulence: %d | Energy: %d\n", this.getHealth(), this.virulence, this.getEnergy());
    }
}
