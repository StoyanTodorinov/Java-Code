package cresla.entities.reactors;

public class HeatReactor extends BaseReactor {
    private static final int VALUE_WHEN_REACTOR_OVERHEATS = 0;

    private int heatReductionIndex;

    public HeatReactor(int id, int heatReductionIndex, int capacity) {
        super(id, capacity);
        this.heatReductionIndex = heatReductionIndex;
    }

    @Override
    public long getTotalEnergyOutput() {
        if (super.getTotalEnergyOutput() > this.getTotalHeatAbsorbing()) {
            return VALUE_WHEN_REACTOR_OVERHEATS;
        }
        return super.getTotalEnergyOutput();
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return super.getTotalHeatAbsorbing() + this.heatReductionIndex;
    }
}
