package cresla.entities.modules;

import cresla.interfaces.EnergyModule;

public abstract class BaseEnergyModule extends BaseModule implements EnergyModule {
    private static final String MODULE_AND_ID_FORMAT = "%s Module - %d\n";
    private static final String ENERGY_OUTPUT = "Energy Output";
    private static final String ADDITIONAL_PARAMETER_NAME_AND_VALUE_FORMAT = "%s: %d\n";

    private int energyOutput;

    protected BaseEnergyModule(int id, int energyOutput) {
        super(id);
        this.energyOutput = energyOutput;
    }

    @Override
    public int getEnergyOutput() {
        return this.energyOutput;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format(MODULE_AND_ID_FORMAT, this.getClass().getSimpleName(), this.getId()));
        sb.append(String.format(ADDITIONAL_PARAMETER_NAME_AND_VALUE_FORMAT, ENERGY_OUTPUT, this.energyOutput));

        return sb.toString();
    }
}
