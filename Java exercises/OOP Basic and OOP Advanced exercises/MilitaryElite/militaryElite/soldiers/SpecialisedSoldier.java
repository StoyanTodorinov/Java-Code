package militaryElite.soldiers;

import militaryElite.interfaces.soldies.ISpecialisedSoldier;

public abstract class SpecialisedSoldier extends Private implements ISpecialisedSoldier {
    private static final String AIRFORCES = "Airforces";
    private static final String MARINES = "Marines";

    private String corps;

    protected SpecialisedSoldier(String id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName, salary);
        this.setCorps(corps);
    }

    @Override
    public void setCorps(String corps) {
        if (corps.equals(AIRFORCES) || corps.equals(MARINES)) {
            this.corps = corps;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getCorps() {
        return this.corps;
    }
}
