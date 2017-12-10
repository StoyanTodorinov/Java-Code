package itsInTheBlood.models.microbes;

public class Bacteria extends Microbe {

    private static final int DELIMITER = 3;

    public Bacteria(String id, int health, int positionRow, int positionCow, int virulence) {
        super(id, health, positionRow, positionCow, virulence);
    }

    @Override
    public int getEnergy() {
        return (super.getHealth() + super.getAdditionalParameter()) / DELIMITER;
    }
}
