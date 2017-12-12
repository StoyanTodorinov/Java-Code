package cresla.entities.modules;

import cresla.interfaces.AbsorbingModule;

public abstract class BaseAbsorbingModule extends BaseModule implements AbsorbingModule {
    private static final String MODULE_AND_ID_FORMAT = "%s Module - %d\n";
    private static final String HEAT_ABSORBING = "Heat Absorbing";
    private static final String ADDITIONAL_PARAMETER_NAME_AND_VALUE_FORMAT = "%s: %d\n";

    private int heatAbsorbing;

    protected BaseAbsorbingModule(int id, int heatAbsorbing) {
        super(id);
        this.heatAbsorbing = heatAbsorbing;
    }

    @Override
    public int getHeatAbsorbing() {
        return this.heatAbsorbing;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format(MODULE_AND_ID_FORMAT,this.getClass().getSimpleName(), this.getId()));
        sb.append(String.format(ADDITIONAL_PARAMETER_NAME_AND_VALUE_FORMAT, HEAT_ABSORBING, this.heatAbsorbing));

        return sb.toString();
    }
}
