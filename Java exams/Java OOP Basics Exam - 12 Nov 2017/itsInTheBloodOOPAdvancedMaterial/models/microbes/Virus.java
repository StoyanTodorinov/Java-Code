package itsInTheBlood.models.microbes;

public class Virus extends Microbe {

    public Virus(String id, int health, int positionRow, int positionCow, int virulence) {
        super(id, health, positionRow, positionCow, virulence);
    }

    @Override
    public int getEnergy() {
        return this.getHealth() + this.getAdditionalParameter();
    }
}
