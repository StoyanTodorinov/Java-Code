package cresla.entities.reactors;

public class CryoReactor extends BaseReactor {
    private static final int VALUE_WHEN_REACTOR_OVERHEATS = 0;

    private int cryoProductionIndex;

    public CryoReactor(int id, int cryoProductionIndex, int capacity) {
        super(id, capacity);
        this.cryoProductionIndex = cryoProductionIndex;
    }

    @Override
    public long getTotalEnergyOutput() {
        if (super.getTotalEnergyOutput() * this.cryoProductionIndex > this.getTotalHeatAbsorbing()) {
            return VALUE_WHEN_REACTOR_OVERHEATS;
        }
        return super.getTotalEnergyOutput() * this.cryoProductionIndex;
    }
}
