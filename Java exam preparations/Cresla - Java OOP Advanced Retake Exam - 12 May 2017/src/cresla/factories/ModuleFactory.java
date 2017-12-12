package cresla.factories;

import cresla.entities.modules.CooldownSystem;
import cresla.entities.modules.CryogenRod;
import cresla.entities.modules.HeatProcessor;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.EnergyModule;

public final class ModuleFactory {
    private static final String CRYOGEN_ROD = "CryogenRod";
    private static final String HEAT_PROCESSOR = "HeatProcessor";
    private static final String COOLDOWN_SYSTEM = "CooldownSystem";

    public static EnergyModule createEnergyModule(String type, int id, int capacity) {
        if (type.equals(CRYOGEN_ROD)) {
            return new CryogenRod(id, capacity);
        }
        return null;
    }

    public static AbsorbingModule createAbsorbingModule(String type, int id, int capacity) {
        if (type.equals(HEAT_PROCESSOR)) {
            return new HeatProcessor(id, capacity);
        } else if (type.equals(COOLDOWN_SYSTEM)) {
            return new CooldownSystem(id, capacity);
        }
        return null;
    }
}
