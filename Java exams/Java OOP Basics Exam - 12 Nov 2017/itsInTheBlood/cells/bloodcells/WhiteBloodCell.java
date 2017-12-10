package itsInTheBlood.cells.bloodcells;

public class WhiteBloodCell extends BloodCell {

    private int size;

    public WhiteBloodCell(String id, int health, int positionRow, int positionCol, int size) {
        super(id, health, positionRow, positionCol);
        this.size = size;
    }

    @Override
    public int getEnergy() {
        return (this.getHealth() + this.size) * 2;
    }

    @Override
    protected int getExtraParam() {
        return this.size;
    }

    @Override
    protected String getParamName() {
        return "Size";
    }
}
