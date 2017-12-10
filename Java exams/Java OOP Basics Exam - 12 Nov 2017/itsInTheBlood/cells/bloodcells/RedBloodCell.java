package itsInTheBlood.cells.bloodcells;

public class RedBloodCell extends BloodCell {

    private int velocity;

    public RedBloodCell(String id, int health, int positionRow, int positionCol, int velocity) {
        super(id, health, positionRow, positionCol);
        this.velocity = velocity;
    }

    @Override
    public int getEnergy() {
        return this.getHealth() + this.velocity;
    }

    @Override
    protected int getExtraParam() {
        return this.velocity;
    }

    @Override
    protected String getParamName() {
        return "Velocity";
    }
}
