package itsInTheBlood.models.bloodcells;

public class WhiteBloodCell extends BloodCell {

    private static final String ADDITIONAL_PARAM_NAME = "Size";
    private static final int MULTIPLIER = 2;
    private int size;

    public WhiteBloodCell(String id, int health, int positionRow, int positionCow, int size) {
        super(id, health, positionRow, positionCow);
        this.size = size;
    }

    @Override
    public int getEnergy() {
        return (super.getHealth() + this.size) * MULTIPLIER;
    }

    @Override
    protected int getAdditionalParameter() {
        return this.size;
    }

    @Override
    protected String getAdditionalParameterName() {
        return ADDITIONAL_PARAM_NAME;
    }
}
