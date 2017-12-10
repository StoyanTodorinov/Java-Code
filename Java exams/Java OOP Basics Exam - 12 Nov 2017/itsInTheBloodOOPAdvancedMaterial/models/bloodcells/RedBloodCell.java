package itsInTheBlood.models.bloodcells;

public class RedBloodCell extends BloodCell {

    public static final String ADDITIONAL_PARAM_NAME = "Velocity";
    private int velocity;

    public RedBloodCell(String id, int health, int positionRow, int positionCow, int velocity) {
        super(id, health, positionRow, positionCow);
        this.velocity = velocity;
    }

    @Override
    public int getEnergy() {
        return super.getHealth() + this.velocity;
    }

    @Override
    protected int getAdditionalParameter() {
        return this.velocity;
    }

    @Override
    protected String getAdditionalParameterName() {
        return ADDITIONAL_PARAM_NAME;
    }
}
