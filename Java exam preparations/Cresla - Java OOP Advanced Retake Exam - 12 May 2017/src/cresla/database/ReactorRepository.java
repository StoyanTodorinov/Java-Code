package cresla.database;

import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Reactor;

import java.util.LinkedHashMap;
import java.util.Map;

public class ReactorRepository implements Repository {
    private static final int INITIAL_VALUE = 0;
    private static final String CRYO_REACTOR = "CryoReactor";
    private static final String CRYO_REACTORS_REPRESENTATION_FORMAT = "Cryo Reactors: %d\n";
    private static final String HEAT_REACTORS_REPRESENTATION_FORMAT = "Heat Reactors: %d\n";
    private static final String ENERGY_MODULES_REPRESENTATION_FORMAT = "Energy Modules: %d\n";
    private static final String ABSORBING_MODULES_REPRESENTATON_FORMAT = "Absorbing Modules: %d\n";
    private static final String TOTAL_ENERGY_OUTPUT_REPRESENTATION_FORMAT = "Total Energy Output: %d\n";
    private static final String TOTAL_HEAT_ABSORBING_REPRESENTATION_FORMAT = "Total Heat Absorbing: %d\n";

    private Map<Integer, Reactor> reactors;
    private Map<Integer, AbsorbingModule> absorbingModules;
    private Map<Integer, EnergyModule> energyModules;

    public ReactorRepository() {
        this.reactors = new LinkedHashMap<>();
        this.absorbingModules = new LinkedHashMap<>();
        this.energyModules = new LinkedHashMap<>();
    }

    @Override
    public void addReactor(Reactor reactor) {
        this.reactors.put(reactor.getId(), reactor);
    }

    @Override
    public void addEnergyModule(int id, EnergyModule module) {
        this.reactors.get(id).addEnergyModule(module);
        this.energyModules.put(module.getId(), module);
    }

    @Override
    public void addAbsorbingModule(int id, AbsorbingModule module) {
        this.reactors.get(id).addAbsorbingModule(module);
        this.absorbingModules.put(module.getId(), module);
    }

    @Override
    public String reportItem(int id) {
        if (this.reactors.containsKey(id)) {
            return this.reactors.get(id).toString();
        } else if (this.energyModules.containsKey(id)) {
            return this.energyModules.get(id).toString();
        } else {
            return this.absorbingModules.get(id).toString();
        }
    }

    @Override
    public String toString() {
        int cryoReactorsCount = INITIAL_VALUE;
        int heatReactorsCount = INITIAL_VALUE;

        for (Reactor reactor : reactors.values()) {
            if (reactor.getClass().getSimpleName().equals(CRYO_REACTOR)) {
                cryoReactorsCount++;
            } else {
                heatReactorsCount++;
            }
        }

        return String.format(CRYO_REACTORS_REPRESENTATION_FORMAT, cryoReactorsCount) +
                String.format(HEAT_REACTORS_REPRESENTATION_FORMAT, heatReactorsCount) +
                String.format(ENERGY_MODULES_REPRESENTATION_FORMAT, energyModules.size()) +
                String.format(ABSORBING_MODULES_REPRESENTATON_FORMAT, absorbingModules.size()) +
                String.format(TOTAL_ENERGY_OUTPUT_REPRESENTATION_FORMAT, getTotalEnergyOfAllReactors(reactors)) +
                String.format(TOTAL_HEAT_ABSORBING_REPRESENTATION_FORMAT, getTotalAbsorbingOfAllReactors(reactors));

    }

    private static long getTotalEnergyOfAllReactors(Map<Integer, Reactor> reactors) {
        long sum = 0;
        for (Reactor reactor : reactors.values()) {
            sum += reactor.getTotalEnergyOutput();
        }
        return sum;
    }

    private static long getTotalAbsorbingOfAllReactors(Map<Integer, Reactor> reactors) {
        long sum = 0;
        for (Reactor reactor : reactors.values()) {
            sum += reactor.getTotalHeatAbsorbing();
        }
        return sum;
    }
}
