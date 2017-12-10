package itsInTheBlood.models.microbes;

public class Fungi extends Microbe {

    private static final int DELIMITER = 4;

    public Fungi(String id, int health, int positionRow, int positionCow, int virulence) {
        super(id, health, positionRow, positionCow, virulence);
    }

    @Override
    public int getEnergy() {
        return (this.getHealth() + this.getAdditionalParameter()) / DELIMITER;
    }
}
