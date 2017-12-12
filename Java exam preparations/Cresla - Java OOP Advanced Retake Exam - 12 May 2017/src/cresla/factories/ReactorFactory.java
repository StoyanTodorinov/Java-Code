package cresla.factories;

import cresla.entities.reactors.CryoReactor;
import cresla.entities.reactors.HeatReactor;
import cresla.interfaces.Reactor;

public final class ReactorFactory {
    private static final String CRYO = "Cryo";

    public static Reactor createReactor(String type, int id, int cryoProductionIndex, int capacity) {
        Reactor reactor;
        switch (type) {
            case CRYO:
                reactor = new CryoReactor(id, cryoProductionIndex, capacity);
                break;

            default:
                reactor = new HeatReactor(id, cryoProductionIndex, capacity);
                break;
        }
        return reactor;
    }
}
